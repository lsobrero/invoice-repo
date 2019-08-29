package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.Contratto;
import net.anet.invoice.repo.repository.ContrattoRepository;
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
 * Integration tests for the {@Link ContrattoResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class ContrattoResourceIT {

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
    private ContrattoRepository contrattoRepository;

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

    private MockMvc restContrattoMockMvc;

    private Contratto contratto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContrattoResource contrattoResource = new ContrattoResource(contrattoRepository);
        this.restContrattoMockMvc = MockMvcBuilders.standaloneSetup(contrattoResource)
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
    public static Contratto createEntity(EntityManager em) {
        Contratto contratto = new Contratto()
            .idDocumento(DEFAULT_ID_DOCUMENTO)
            .dOAData(DEFAULT_D_OA_DATA)
            .numItem(DEFAULT_NUM_ITEM)
            .codiceCommessaConvenzione(DEFAULT_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(DEFAULT_CODICE_CUP)
            .codiceCIG(DEFAULT_CODICE_CIG);
        return contratto;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contratto createUpdatedEntity(EntityManager em) {
        Contratto contratto = new Contratto()
            .idDocumento(UPDATED_ID_DOCUMENTO)
            .dOAData(UPDATED_D_OA_DATA)
            .numItem(UPDATED_NUM_ITEM)
            .codiceCommessaConvenzione(UPDATED_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(UPDATED_CODICE_CUP)
            .codiceCIG(UPDATED_CODICE_CIG);
        return contratto;
    }

    @BeforeEach
    public void initTest() {
        contratto = createEntity(em);
    }

    @Test
    @Transactional
    public void createContratto() throws Exception {
        int databaseSizeBeforeCreate = contrattoRepository.findAll().size();

        // Create the Contratto
        restContrattoMockMvc.perform(post("/api/contrattos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratto)))
            .andExpect(status().isCreated());

        // Validate the Contratto in the database
        List<Contratto> contrattoList = contrattoRepository.findAll();
        assertThat(contrattoList).hasSize(databaseSizeBeforeCreate + 1);
        Contratto testContratto = contrattoList.get(contrattoList.size() - 1);
        assertThat(testContratto.getIdDocumento()).isEqualTo(DEFAULT_ID_DOCUMENTO);
        assertThat(testContratto.getdOAData()).isEqualTo(DEFAULT_D_OA_DATA);
        assertThat(testContratto.getNumItem()).isEqualTo(DEFAULT_NUM_ITEM);
        assertThat(testContratto.getCodiceCommessaConvenzione()).isEqualTo(DEFAULT_CODICE_COMMESSA_CONVENZIONE);
        assertThat(testContratto.getCodiceCUP()).isEqualTo(DEFAULT_CODICE_CUP);
        assertThat(testContratto.getCodiceCIG()).isEqualTo(DEFAULT_CODICE_CIG);
    }

    @Test
    @Transactional
    public void createContrattoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contrattoRepository.findAll().size();

        // Create the Contratto with an existing ID
        contratto.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContrattoMockMvc.perform(post("/api/contrattos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratto)))
            .andExpect(status().isBadRequest());

        // Validate the Contratto in the database
        List<Contratto> contrattoList = contrattoRepository.findAll();
        assertThat(contrattoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllContrattos() throws Exception {
        // Initialize the database
        contrattoRepository.saveAndFlush(contratto);

        // Get all the contrattoList
        restContrattoMockMvc.perform(get("/api/contrattos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contratto.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDocumento").value(hasItem(DEFAULT_ID_DOCUMENTO.toString())))
            .andExpect(jsonPath("$.[*].dOAData").value(hasItem(DEFAULT_D_OA_DATA.toString())))
            .andExpect(jsonPath("$.[*].numItem").value(hasItem(DEFAULT_NUM_ITEM.toString())))
            .andExpect(jsonPath("$.[*].codiceCommessaConvenzione").value(hasItem(DEFAULT_CODICE_COMMESSA_CONVENZIONE.toString())))
            .andExpect(jsonPath("$.[*].codiceCUP").value(hasItem(DEFAULT_CODICE_CUP.toString())))
            .andExpect(jsonPath("$.[*].codiceCIG").value(hasItem(DEFAULT_CODICE_CIG.toString())));
    }
    
    @Test
    @Transactional
    public void getContratto() throws Exception {
        // Initialize the database
        contrattoRepository.saveAndFlush(contratto);

        // Get the contratto
        restContrattoMockMvc.perform(get("/api/contrattos/{id}", contratto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contratto.getId().intValue()))
            .andExpect(jsonPath("$.idDocumento").value(DEFAULT_ID_DOCUMENTO.toString()))
            .andExpect(jsonPath("$.dOAData").value(DEFAULT_D_OA_DATA.toString()))
            .andExpect(jsonPath("$.numItem").value(DEFAULT_NUM_ITEM.toString()))
            .andExpect(jsonPath("$.codiceCommessaConvenzione").value(DEFAULT_CODICE_COMMESSA_CONVENZIONE.toString()))
            .andExpect(jsonPath("$.codiceCUP").value(DEFAULT_CODICE_CUP.toString()))
            .andExpect(jsonPath("$.codiceCIG").value(DEFAULT_CODICE_CIG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingContratto() throws Exception {
        // Get the contratto
        restContrattoMockMvc.perform(get("/api/contrattos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContratto() throws Exception {
        // Initialize the database
        contrattoRepository.saveAndFlush(contratto);

        int databaseSizeBeforeUpdate = contrattoRepository.findAll().size();

        // Update the contratto
        Contratto updatedContratto = contrattoRepository.findById(contratto.getId()).get();
        // Disconnect from session so that the updates on updatedContratto are not directly saved in db
        em.detach(updatedContratto);
        updatedContratto
            .idDocumento(UPDATED_ID_DOCUMENTO)
            .dOAData(UPDATED_D_OA_DATA)
            .numItem(UPDATED_NUM_ITEM)
            .codiceCommessaConvenzione(UPDATED_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(UPDATED_CODICE_CUP)
            .codiceCIG(UPDATED_CODICE_CIG);

        restContrattoMockMvc.perform(put("/api/contrattos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedContratto)))
            .andExpect(status().isOk());

        // Validate the Contratto in the database
        List<Contratto> contrattoList = contrattoRepository.findAll();
        assertThat(contrattoList).hasSize(databaseSizeBeforeUpdate);
        Contratto testContratto = contrattoList.get(contrattoList.size() - 1);
        assertThat(testContratto.getIdDocumento()).isEqualTo(UPDATED_ID_DOCUMENTO);
        assertThat(testContratto.getdOAData()).isEqualTo(UPDATED_D_OA_DATA);
        assertThat(testContratto.getNumItem()).isEqualTo(UPDATED_NUM_ITEM);
        assertThat(testContratto.getCodiceCommessaConvenzione()).isEqualTo(UPDATED_CODICE_COMMESSA_CONVENZIONE);
        assertThat(testContratto.getCodiceCUP()).isEqualTo(UPDATED_CODICE_CUP);
        assertThat(testContratto.getCodiceCIG()).isEqualTo(UPDATED_CODICE_CIG);
    }

    @Test
    @Transactional
    public void updateNonExistingContratto() throws Exception {
        int databaseSizeBeforeUpdate = contrattoRepository.findAll().size();

        // Create the Contratto

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContrattoMockMvc.perform(put("/api/contrattos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratto)))
            .andExpect(status().isBadRequest());

        // Validate the Contratto in the database
        List<Contratto> contrattoList = contrattoRepository.findAll();
        assertThat(contrattoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContratto() throws Exception {
        // Initialize the database
        contrattoRepository.saveAndFlush(contratto);

        int databaseSizeBeforeDelete = contrattoRepository.findAll().size();

        // Delete the contratto
        restContrattoMockMvc.perform(delete("/api/contrattos/{id}", contratto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Contratto> contrattoList = contrattoRepository.findAll();
        assertThat(contrattoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contratto.class);
        Contratto contratto1 = new Contratto();
        contratto1.setId(1L);
        Contratto contratto2 = new Contratto();
        contratto2.setId(contratto1.getId());
        assertThat(contratto1).isEqualTo(contratto2);
        contratto2.setId(2L);
        assertThat(contratto1).isNotEqualTo(contratto2);
        contratto1.setId(null);
        assertThat(contratto1).isNotEqualTo(contratto2);
    }
}
