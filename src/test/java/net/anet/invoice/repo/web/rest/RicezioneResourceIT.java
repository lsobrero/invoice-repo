package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.Ricezione;
import net.anet.invoice.repo.repository.RicezioneRepository;
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
 * Integration tests for the {@Link RicezioneResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class RicezioneResourceIT {

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
    private RicezioneRepository ricezioneRepository;

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

    private MockMvc restRicezioneMockMvc;

    private Ricezione ricezione;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RicezioneResource ricezioneResource = new RicezioneResource(ricezioneRepository);
        this.restRicezioneMockMvc = MockMvcBuilders.standaloneSetup(ricezioneResource)
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
    public static Ricezione createEntity(EntityManager em) {
        Ricezione ricezione = new Ricezione()
            .idDocumento(DEFAULT_ID_DOCUMENTO)
            .dOAData(DEFAULT_D_OA_DATA)
            .numItem(DEFAULT_NUM_ITEM)
            .codiceCommessaConvenzione(DEFAULT_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(DEFAULT_CODICE_CUP)
            .codiceCIG(DEFAULT_CODICE_CIG);
        return ricezione;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ricezione createUpdatedEntity(EntityManager em) {
        Ricezione ricezione = new Ricezione()
            .idDocumento(UPDATED_ID_DOCUMENTO)
            .dOAData(UPDATED_D_OA_DATA)
            .numItem(UPDATED_NUM_ITEM)
            .codiceCommessaConvenzione(UPDATED_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(UPDATED_CODICE_CUP)
            .codiceCIG(UPDATED_CODICE_CIG);
        return ricezione;
    }

    @BeforeEach
    public void initTest() {
        ricezione = createEntity(em);
    }

    @Test
    @Transactional
    public void createRicezione() throws Exception {
        int databaseSizeBeforeCreate = ricezioneRepository.findAll().size();

        // Create the Ricezione
        restRicezioneMockMvc.perform(post("/api/riceziones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ricezione)))
            .andExpect(status().isCreated());

        // Validate the Ricezione in the database
        List<Ricezione> ricezioneList = ricezioneRepository.findAll();
        assertThat(ricezioneList).hasSize(databaseSizeBeforeCreate + 1);
        Ricezione testRicezione = ricezioneList.get(ricezioneList.size() - 1);
        assertThat(testRicezione.getIdDocumento()).isEqualTo(DEFAULT_ID_DOCUMENTO);
        assertThat(testRicezione.getdOAData()).isEqualTo(DEFAULT_D_OA_DATA);
        assertThat(testRicezione.getNumItem()).isEqualTo(DEFAULT_NUM_ITEM);
        assertThat(testRicezione.getCodiceCommessaConvenzione()).isEqualTo(DEFAULT_CODICE_COMMESSA_CONVENZIONE);
        assertThat(testRicezione.getCodiceCUP()).isEqualTo(DEFAULT_CODICE_CUP);
        assertThat(testRicezione.getCodiceCIG()).isEqualTo(DEFAULT_CODICE_CIG);
    }

    @Test
    @Transactional
    public void createRicezioneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ricezioneRepository.findAll().size();

        // Create the Ricezione with an existing ID
        ricezione.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRicezioneMockMvc.perform(post("/api/riceziones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ricezione)))
            .andExpect(status().isBadRequest());

        // Validate the Ricezione in the database
        List<Ricezione> ricezioneList = ricezioneRepository.findAll();
        assertThat(ricezioneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRiceziones() throws Exception {
        // Initialize the database
        ricezioneRepository.saveAndFlush(ricezione);

        // Get all the ricezioneList
        restRicezioneMockMvc.perform(get("/api/riceziones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ricezione.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDocumento").value(hasItem(DEFAULT_ID_DOCUMENTO.toString())))
            .andExpect(jsonPath("$.[*].dOAData").value(hasItem(DEFAULT_D_OA_DATA.toString())))
            .andExpect(jsonPath("$.[*].numItem").value(hasItem(DEFAULT_NUM_ITEM.toString())))
            .andExpect(jsonPath("$.[*].codiceCommessaConvenzione").value(hasItem(DEFAULT_CODICE_COMMESSA_CONVENZIONE.toString())))
            .andExpect(jsonPath("$.[*].codiceCUP").value(hasItem(DEFAULT_CODICE_CUP.toString())))
            .andExpect(jsonPath("$.[*].codiceCIG").value(hasItem(DEFAULT_CODICE_CIG.toString())));
    }
    
    @Test
    @Transactional
    public void getRicezione() throws Exception {
        // Initialize the database
        ricezioneRepository.saveAndFlush(ricezione);

        // Get the ricezione
        restRicezioneMockMvc.perform(get("/api/riceziones/{id}", ricezione.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ricezione.getId().intValue()))
            .andExpect(jsonPath("$.idDocumento").value(DEFAULT_ID_DOCUMENTO.toString()))
            .andExpect(jsonPath("$.dOAData").value(DEFAULT_D_OA_DATA.toString()))
            .andExpect(jsonPath("$.numItem").value(DEFAULT_NUM_ITEM.toString()))
            .andExpect(jsonPath("$.codiceCommessaConvenzione").value(DEFAULT_CODICE_COMMESSA_CONVENZIONE.toString()))
            .andExpect(jsonPath("$.codiceCUP").value(DEFAULT_CODICE_CUP.toString()))
            .andExpect(jsonPath("$.codiceCIG").value(DEFAULT_CODICE_CIG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRicezione() throws Exception {
        // Get the ricezione
        restRicezioneMockMvc.perform(get("/api/riceziones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRicezione() throws Exception {
        // Initialize the database
        ricezioneRepository.saveAndFlush(ricezione);

        int databaseSizeBeforeUpdate = ricezioneRepository.findAll().size();

        // Update the ricezione
        Ricezione updatedRicezione = ricezioneRepository.findById(ricezione.getId()).get();
        // Disconnect from session so that the updates on updatedRicezione are not directly saved in db
        em.detach(updatedRicezione);
        updatedRicezione
            .idDocumento(UPDATED_ID_DOCUMENTO)
            .dOAData(UPDATED_D_OA_DATA)
            .numItem(UPDATED_NUM_ITEM)
            .codiceCommessaConvenzione(UPDATED_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(UPDATED_CODICE_CUP)
            .codiceCIG(UPDATED_CODICE_CIG);

        restRicezioneMockMvc.perform(put("/api/riceziones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRicezione)))
            .andExpect(status().isOk());

        // Validate the Ricezione in the database
        List<Ricezione> ricezioneList = ricezioneRepository.findAll();
        assertThat(ricezioneList).hasSize(databaseSizeBeforeUpdate);
        Ricezione testRicezione = ricezioneList.get(ricezioneList.size() - 1);
        assertThat(testRicezione.getIdDocumento()).isEqualTo(UPDATED_ID_DOCUMENTO);
        assertThat(testRicezione.getdOAData()).isEqualTo(UPDATED_D_OA_DATA);
        assertThat(testRicezione.getNumItem()).isEqualTo(UPDATED_NUM_ITEM);
        assertThat(testRicezione.getCodiceCommessaConvenzione()).isEqualTo(UPDATED_CODICE_COMMESSA_CONVENZIONE);
        assertThat(testRicezione.getCodiceCUP()).isEqualTo(UPDATED_CODICE_CUP);
        assertThat(testRicezione.getCodiceCIG()).isEqualTo(UPDATED_CODICE_CIG);
    }

    @Test
    @Transactional
    public void updateNonExistingRicezione() throws Exception {
        int databaseSizeBeforeUpdate = ricezioneRepository.findAll().size();

        // Create the Ricezione

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRicezioneMockMvc.perform(put("/api/riceziones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ricezione)))
            .andExpect(status().isBadRequest());

        // Validate the Ricezione in the database
        List<Ricezione> ricezioneList = ricezioneRepository.findAll();
        assertThat(ricezioneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRicezione() throws Exception {
        // Initialize the database
        ricezioneRepository.saveAndFlush(ricezione);

        int databaseSizeBeforeDelete = ricezioneRepository.findAll().size();

        // Delete the ricezione
        restRicezioneMockMvc.perform(delete("/api/riceziones/{id}", ricezione.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Ricezione> ricezioneList = ricezioneRepository.findAll();
        assertThat(ricezioneList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ricezione.class);
        Ricezione ricezione1 = new Ricezione();
        ricezione1.setId(1L);
        Ricezione ricezione2 = new Ricezione();
        ricezione2.setId(ricezione1.getId());
        assertThat(ricezione1).isEqualTo(ricezione2);
        ricezione2.setId(2L);
        assertThat(ricezione1).isNotEqualTo(ricezione2);
        ricezione1.setId(null);
        assertThat(ricezione1).isNotEqualTo(ricezione2);
    }
}
