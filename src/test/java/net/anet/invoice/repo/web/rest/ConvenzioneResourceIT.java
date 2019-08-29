package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.Convenzione;
import net.anet.invoice.repo.repository.ConvenzioneRepository;
import net.anet.invoice.repo.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static net.anet.invoice.repo.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link ConvenzioneResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class ConvenzioneResourceIT {

    private static final String DEFAULT_ID_DOCUMENTO = "AAAAAAAAAA";
    private static final String UPDATED_ID_DOCUMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_D_OA_DATA = "AAAAAAAAAA";
    private static final String UPDATED_D_OA_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_NUM_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_CODICE_COMMESSA_CONVENZIONE = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_COMMESSA_CONVENZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_CODICE_CUP = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_CUP = "BBBBBBBBBB";

    private static final String DEFAULT_CODICE_CIG = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_CIG = "BBBBBBBBBB";

    @Autowired
    private ConvenzioneRepository convenzioneRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restConvenzioneMockMvc;

    private Convenzione convenzione;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConvenzioneResource convenzioneResource = new ConvenzioneResource(convenzioneRepository);
        this.restConvenzioneMockMvc = MockMvcBuilders.standaloneSetup(convenzioneResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Convenzione createEntity(EntityManager em) {
        Convenzione convenzione = new Convenzione()
            .idDocumento(DEFAULT_ID_DOCUMENTO)
            .dOAData(DEFAULT_D_OA_DATA)
            .numItem(DEFAULT_NUM_ITEM)
            .codiceCommessaConvenzione(DEFAULT_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(DEFAULT_CODICE_CUP)
            .codiceCIG(DEFAULT_CODICE_CIG);
        return convenzione;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Convenzione createUpdatedEntity(EntityManager em) {
        Convenzione convenzione = new Convenzione()
            .idDocumento(UPDATED_ID_DOCUMENTO)
            .dOAData(UPDATED_D_OA_DATA)
            .numItem(UPDATED_NUM_ITEM)
            .codiceCommessaConvenzione(UPDATED_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(UPDATED_CODICE_CUP)
            .codiceCIG(UPDATED_CODICE_CIG);
        return convenzione;
    }

    @BeforeEach
    public void initTest() {
        convenzione = createEntity(em);
    }

    @Test
    @Transactional
    public void createConvenzione() throws Exception {
        int databaseSizeBeforeCreate = convenzioneRepository.findAll().size();

        // Create the Convenzione
        restConvenzioneMockMvc.perform(post("/api/convenziones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(convenzione)))
            .andExpect(status().isCreated());

        // Validate the Convenzione in the database
        List<Convenzione> convenzioneList = convenzioneRepository.findAll();
        assertThat(convenzioneList).hasSize(databaseSizeBeforeCreate + 1);
        Convenzione testConvenzione = convenzioneList.get(convenzioneList.size() - 1);
        assertThat(testConvenzione.getIdDocumento()).isEqualTo(DEFAULT_ID_DOCUMENTO);
        assertThat(testConvenzione.getdOAData()).isEqualTo(DEFAULT_D_OA_DATA);
        assertThat(testConvenzione.getNumItem()).isEqualTo(DEFAULT_NUM_ITEM);
        assertThat(testConvenzione.getCodiceCommessaConvenzione()).isEqualTo(DEFAULT_CODICE_COMMESSA_CONVENZIONE);
        assertThat(testConvenzione.getCodiceCUP()).isEqualTo(DEFAULT_CODICE_CUP);
        assertThat(testConvenzione.getCodiceCIG()).isEqualTo(DEFAULT_CODICE_CIG);
    }

    @Test
    @Transactional
    public void createConvenzioneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = convenzioneRepository.findAll().size();

        // Create the Convenzione with an existing ID
        convenzione.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConvenzioneMockMvc.perform(post("/api/convenziones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(convenzione)))
            .andExpect(status().isBadRequest());

        // Validate the Convenzione in the database
        List<Convenzione> convenzioneList = convenzioneRepository.findAll();
        assertThat(convenzioneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllConvenziones() throws Exception {
        // Initialize the database
        convenzioneRepository.saveAndFlush(convenzione);

        // Get all the convenzioneList
        restConvenzioneMockMvc.perform(get("/api/convenziones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(convenzione.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDocumento").value(hasItem(DEFAULT_ID_DOCUMENTO.toString())))
            .andExpect(jsonPath("$.[*].dOAData").value(hasItem(DEFAULT_D_OA_DATA.toString())))
            .andExpect(jsonPath("$.[*].numItem").value(hasItem(DEFAULT_NUM_ITEM.toString())))
            .andExpect(jsonPath("$.[*].codiceCommessaConvenzione").value(hasItem(DEFAULT_CODICE_COMMESSA_CONVENZIONE.toString())))
            .andExpect(jsonPath("$.[*].codiceCUP").value(hasItem(DEFAULT_CODICE_CUP.toString())))
            .andExpect(jsonPath("$.[*].codiceCIG").value(hasItem(DEFAULT_CODICE_CIG.toString())));
    }
    
    @Test
    @Transactional
    public void getConvenzione() throws Exception {
        // Initialize the database
        convenzioneRepository.saveAndFlush(convenzione);

        // Get the convenzione
        restConvenzioneMockMvc.perform(get("/api/convenziones/{id}", convenzione.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(convenzione.getId().intValue()))
            .andExpect(jsonPath("$.idDocumento").value(DEFAULT_ID_DOCUMENTO.toString()))
            .andExpect(jsonPath("$.dOAData").value(DEFAULT_D_OA_DATA.toString()))
            .andExpect(jsonPath("$.numItem").value(DEFAULT_NUM_ITEM.toString()))
            .andExpect(jsonPath("$.codiceCommessaConvenzione").value(DEFAULT_CODICE_COMMESSA_CONVENZIONE.toString()))
            .andExpect(jsonPath("$.codiceCUP").value(DEFAULT_CODICE_CUP.toString()))
            .andExpect(jsonPath("$.codiceCIG").value(DEFAULT_CODICE_CIG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingConvenzione() throws Exception {
        // Get the convenzione
        restConvenzioneMockMvc.perform(get("/api/convenziones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConvenzione() throws Exception {
        // Initialize the database
        convenzioneRepository.saveAndFlush(convenzione);

        int databaseSizeBeforeUpdate = convenzioneRepository.findAll().size();

        // Update the convenzione
        Convenzione updatedConvenzione = convenzioneRepository.findById(convenzione.getId()).get();
        // Disconnect from session so that the updates on updatedConvenzione are not directly saved in db
        em.detach(updatedConvenzione);
        updatedConvenzione
            .idDocumento(UPDATED_ID_DOCUMENTO)
            .dOAData(UPDATED_D_OA_DATA)
            .numItem(UPDATED_NUM_ITEM)
            .codiceCommessaConvenzione(UPDATED_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(UPDATED_CODICE_CUP)
            .codiceCIG(UPDATED_CODICE_CIG);

        restConvenzioneMockMvc.perform(put("/api/convenziones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConvenzione)))
            .andExpect(status().isOk());

        // Validate the Convenzione in the database
        List<Convenzione> convenzioneList = convenzioneRepository.findAll();
        assertThat(convenzioneList).hasSize(databaseSizeBeforeUpdate);
        Convenzione testConvenzione = convenzioneList.get(convenzioneList.size() - 1);
        assertThat(testConvenzione.getIdDocumento()).isEqualTo(UPDATED_ID_DOCUMENTO);
        assertThat(testConvenzione.getdOAData()).isEqualTo(UPDATED_D_OA_DATA);
        assertThat(testConvenzione.getNumItem()).isEqualTo(UPDATED_NUM_ITEM);
        assertThat(testConvenzione.getCodiceCommessaConvenzione()).isEqualTo(UPDATED_CODICE_COMMESSA_CONVENZIONE);
        assertThat(testConvenzione.getCodiceCUP()).isEqualTo(UPDATED_CODICE_CUP);
        assertThat(testConvenzione.getCodiceCIG()).isEqualTo(UPDATED_CODICE_CIG);
    }

    @Test
    @Transactional
    public void updateNonExistingConvenzione() throws Exception {
        int databaseSizeBeforeUpdate = convenzioneRepository.findAll().size();

        // Create the Convenzione

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConvenzioneMockMvc.perform(put("/api/convenziones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(convenzione)))
            .andExpect(status().isBadRequest());

        // Validate the Convenzione in the database
        List<Convenzione> convenzioneList = convenzioneRepository.findAll();
        assertThat(convenzioneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConvenzione() throws Exception {
        // Initialize the database
        convenzioneRepository.saveAndFlush(convenzione);

        int databaseSizeBeforeDelete = convenzioneRepository.findAll().size();

        // Delete the convenzione
        restConvenzioneMockMvc.perform(delete("/api/convenziones/{id}", convenzione.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Convenzione> convenzioneList = convenzioneRepository.findAll();
        assertThat(convenzioneList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Convenzione.class);
        Convenzione convenzione1 = new Convenzione();
        convenzione1.setId(1L);
        Convenzione convenzione2 = new Convenzione();
        convenzione2.setId(convenzione1.getId());
        assertThat(convenzione1).isEqualTo(convenzione2);
        convenzione2.setId(2L);
        assertThat(convenzione1).isNotEqualTo(convenzione2);
        convenzione1.setId(null);
        assertThat(convenzione1).isNotEqualTo(convenzione2);
    }
}
