package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.FatturaCollegata;
import net.anet.invoice.repo.repository.FatturaCollegataRepository;
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
 * Integration tests for the {@Link FatturaCollegataResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class FatturaCollegataResourceIT {

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
    private FatturaCollegataRepository fatturaCollegataRepository;

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

    private MockMvc restFatturaCollegataMockMvc;

    private FatturaCollegata fatturaCollegata;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FatturaCollegataResource fatturaCollegataResource = new FatturaCollegataResource(fatturaCollegataRepository);
        this.restFatturaCollegataMockMvc = MockMvcBuilders.standaloneSetup(fatturaCollegataResource)
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
    public static FatturaCollegata createEntity(EntityManager em) {
        FatturaCollegata fatturaCollegata = new FatturaCollegata()
            .idDocumento(DEFAULT_ID_DOCUMENTO)
            .dOAData(DEFAULT_D_OA_DATA)
            .numItem(DEFAULT_NUM_ITEM)
            .codiceCommessaConvenzione(DEFAULT_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(DEFAULT_CODICE_CUP)
            .codiceCIG(DEFAULT_CODICE_CIG);
        return fatturaCollegata;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FatturaCollegata createUpdatedEntity(EntityManager em) {
        FatturaCollegata fatturaCollegata = new FatturaCollegata()
            .idDocumento(UPDATED_ID_DOCUMENTO)
            .dOAData(UPDATED_D_OA_DATA)
            .numItem(UPDATED_NUM_ITEM)
            .codiceCommessaConvenzione(UPDATED_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(UPDATED_CODICE_CUP)
            .codiceCIG(UPDATED_CODICE_CIG);
        return fatturaCollegata;
    }

    @BeforeEach
    public void initTest() {
        fatturaCollegata = createEntity(em);
    }

    @Test
    @Transactional
    public void createFatturaCollegata() throws Exception {
        int databaseSizeBeforeCreate = fatturaCollegataRepository.findAll().size();

        // Create the FatturaCollegata
        restFatturaCollegataMockMvc.perform(post("/api/fattura-collegatas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fatturaCollegata)))
            .andExpect(status().isCreated());

        // Validate the FatturaCollegata in the database
        List<FatturaCollegata> fatturaCollegataList = fatturaCollegataRepository.findAll();
        assertThat(fatturaCollegataList).hasSize(databaseSizeBeforeCreate + 1);
        FatturaCollegata testFatturaCollegata = fatturaCollegataList.get(fatturaCollegataList.size() - 1);
        assertThat(testFatturaCollegata.getIdDocumento()).isEqualTo(DEFAULT_ID_DOCUMENTO);
        assertThat(testFatturaCollegata.getdOAData()).isEqualTo(DEFAULT_D_OA_DATA);
        assertThat(testFatturaCollegata.getNumItem()).isEqualTo(DEFAULT_NUM_ITEM);
        assertThat(testFatturaCollegata.getCodiceCommessaConvenzione()).isEqualTo(DEFAULT_CODICE_COMMESSA_CONVENZIONE);
        assertThat(testFatturaCollegata.getCodiceCUP()).isEqualTo(DEFAULT_CODICE_CUP);
        assertThat(testFatturaCollegata.getCodiceCIG()).isEqualTo(DEFAULT_CODICE_CIG);
    }

    @Test
    @Transactional
    public void createFatturaCollegataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fatturaCollegataRepository.findAll().size();

        // Create the FatturaCollegata with an existing ID
        fatturaCollegata.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFatturaCollegataMockMvc.perform(post("/api/fattura-collegatas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fatturaCollegata)))
            .andExpect(status().isBadRequest());

        // Validate the FatturaCollegata in the database
        List<FatturaCollegata> fatturaCollegataList = fatturaCollegataRepository.findAll();
        assertThat(fatturaCollegataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFatturaCollegatas() throws Exception {
        // Initialize the database
        fatturaCollegataRepository.saveAndFlush(fatturaCollegata);

        // Get all the fatturaCollegataList
        restFatturaCollegataMockMvc.perform(get("/api/fattura-collegatas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fatturaCollegata.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDocumento").value(hasItem(DEFAULT_ID_DOCUMENTO.toString())))
            .andExpect(jsonPath("$.[*].dOAData").value(hasItem(DEFAULT_D_OA_DATA.toString())))
            .andExpect(jsonPath("$.[*].numItem").value(hasItem(DEFAULT_NUM_ITEM.toString())))
            .andExpect(jsonPath("$.[*].codiceCommessaConvenzione").value(hasItem(DEFAULT_CODICE_COMMESSA_CONVENZIONE.toString())))
            .andExpect(jsonPath("$.[*].codiceCUP").value(hasItem(DEFAULT_CODICE_CUP.toString())))
            .andExpect(jsonPath("$.[*].codiceCIG").value(hasItem(DEFAULT_CODICE_CIG.toString())));
    }
    
    @Test
    @Transactional
    public void getFatturaCollegata() throws Exception {
        // Initialize the database
        fatturaCollegataRepository.saveAndFlush(fatturaCollegata);

        // Get the fatturaCollegata
        restFatturaCollegataMockMvc.perform(get("/api/fattura-collegatas/{id}", fatturaCollegata.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fatturaCollegata.getId().intValue()))
            .andExpect(jsonPath("$.idDocumento").value(DEFAULT_ID_DOCUMENTO.toString()))
            .andExpect(jsonPath("$.dOAData").value(DEFAULT_D_OA_DATA.toString()))
            .andExpect(jsonPath("$.numItem").value(DEFAULT_NUM_ITEM.toString()))
            .andExpect(jsonPath("$.codiceCommessaConvenzione").value(DEFAULT_CODICE_COMMESSA_CONVENZIONE.toString()))
            .andExpect(jsonPath("$.codiceCUP").value(DEFAULT_CODICE_CUP.toString()))
            .andExpect(jsonPath("$.codiceCIG").value(DEFAULT_CODICE_CIG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFatturaCollegata() throws Exception {
        // Get the fatturaCollegata
        restFatturaCollegataMockMvc.perform(get("/api/fattura-collegatas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFatturaCollegata() throws Exception {
        // Initialize the database
        fatturaCollegataRepository.saveAndFlush(fatturaCollegata);

        int databaseSizeBeforeUpdate = fatturaCollegataRepository.findAll().size();

        // Update the fatturaCollegata
        FatturaCollegata updatedFatturaCollegata = fatturaCollegataRepository.findById(fatturaCollegata.getId()).get();
        // Disconnect from session so that the updates on updatedFatturaCollegata are not directly saved in db
        em.detach(updatedFatturaCollegata);
        updatedFatturaCollegata
            .idDocumento(UPDATED_ID_DOCUMENTO)
            .dOAData(UPDATED_D_OA_DATA)
            .numItem(UPDATED_NUM_ITEM)
            .codiceCommessaConvenzione(UPDATED_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(UPDATED_CODICE_CUP)
            .codiceCIG(UPDATED_CODICE_CIG);

        restFatturaCollegataMockMvc.perform(put("/api/fattura-collegatas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFatturaCollegata)))
            .andExpect(status().isOk());

        // Validate the FatturaCollegata in the database
        List<FatturaCollegata> fatturaCollegataList = fatturaCollegataRepository.findAll();
        assertThat(fatturaCollegataList).hasSize(databaseSizeBeforeUpdate);
        FatturaCollegata testFatturaCollegata = fatturaCollegataList.get(fatturaCollegataList.size() - 1);
        assertThat(testFatturaCollegata.getIdDocumento()).isEqualTo(UPDATED_ID_DOCUMENTO);
        assertThat(testFatturaCollegata.getdOAData()).isEqualTo(UPDATED_D_OA_DATA);
        assertThat(testFatturaCollegata.getNumItem()).isEqualTo(UPDATED_NUM_ITEM);
        assertThat(testFatturaCollegata.getCodiceCommessaConvenzione()).isEqualTo(UPDATED_CODICE_COMMESSA_CONVENZIONE);
        assertThat(testFatturaCollegata.getCodiceCUP()).isEqualTo(UPDATED_CODICE_CUP);
        assertThat(testFatturaCollegata.getCodiceCIG()).isEqualTo(UPDATED_CODICE_CIG);
    }

    @Test
    @Transactional
    public void updateNonExistingFatturaCollegata() throws Exception {
        int databaseSizeBeforeUpdate = fatturaCollegataRepository.findAll().size();

        // Create the FatturaCollegata

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFatturaCollegataMockMvc.perform(put("/api/fattura-collegatas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fatturaCollegata)))
            .andExpect(status().isBadRequest());

        // Validate the FatturaCollegata in the database
        List<FatturaCollegata> fatturaCollegataList = fatturaCollegataRepository.findAll();
        assertThat(fatturaCollegataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFatturaCollegata() throws Exception {
        // Initialize the database
        fatturaCollegataRepository.saveAndFlush(fatturaCollegata);

        int databaseSizeBeforeDelete = fatturaCollegataRepository.findAll().size();

        // Delete the fatturaCollegata
        restFatturaCollegataMockMvc.perform(delete("/api/fattura-collegatas/{id}", fatturaCollegata.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<FatturaCollegata> fatturaCollegataList = fatturaCollegataRepository.findAll();
        assertThat(fatturaCollegataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FatturaCollegata.class);
        FatturaCollegata fatturaCollegata1 = new FatturaCollegata();
        fatturaCollegata1.setId(1L);
        FatturaCollegata fatturaCollegata2 = new FatturaCollegata();
        fatturaCollegata2.setId(fatturaCollegata1.getId());
        assertThat(fatturaCollegata1).isEqualTo(fatturaCollegata2);
        fatturaCollegata2.setId(2L);
        assertThat(fatturaCollegata1).isNotEqualTo(fatturaCollegata2);
        fatturaCollegata1.setId(null);
        assertThat(fatturaCollegata1).isNotEqualTo(fatturaCollegata2);
    }
}
