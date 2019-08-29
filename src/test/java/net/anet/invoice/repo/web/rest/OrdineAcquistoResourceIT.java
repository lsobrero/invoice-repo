package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.OrdineAcquisto;
import net.anet.invoice.repo.repository.OrdineAcquistoRepository;
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
 * Integration tests for the {@Link OrdineAcquistoResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class OrdineAcquistoResourceIT {

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
    private OrdineAcquistoRepository ordineAcquistoRepository;

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

    private MockMvc restOrdineAcquistoMockMvc;

    private OrdineAcquisto ordineAcquisto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrdineAcquistoResource ordineAcquistoResource = new OrdineAcquistoResource(ordineAcquistoRepository);
        this.restOrdineAcquistoMockMvc = MockMvcBuilders.standaloneSetup(ordineAcquistoResource)
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
    public static OrdineAcquisto createEntity(EntityManager em) {
        OrdineAcquisto ordineAcquisto = new OrdineAcquisto()
            .idDocumento(DEFAULT_ID_DOCUMENTO)
            .dOAData(DEFAULT_D_OA_DATA)
            .numItem(DEFAULT_NUM_ITEM)
            .codiceCommessaConvenzione(DEFAULT_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(DEFAULT_CODICE_CUP)
            .codiceCIG(DEFAULT_CODICE_CIG);
        return ordineAcquisto;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrdineAcquisto createUpdatedEntity(EntityManager em) {
        OrdineAcquisto ordineAcquisto = new OrdineAcquisto()
            .idDocumento(UPDATED_ID_DOCUMENTO)
            .dOAData(UPDATED_D_OA_DATA)
            .numItem(UPDATED_NUM_ITEM)
            .codiceCommessaConvenzione(UPDATED_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(UPDATED_CODICE_CUP)
            .codiceCIG(UPDATED_CODICE_CIG);
        return ordineAcquisto;
    }

    @BeforeEach
    public void initTest() {
        ordineAcquisto = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrdineAcquisto() throws Exception {
        int databaseSizeBeforeCreate = ordineAcquistoRepository.findAll().size();

        // Create the OrdineAcquisto
        restOrdineAcquistoMockMvc.perform(post("/api/ordine-acquistos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ordineAcquisto)))
            .andExpect(status().isCreated());

        // Validate the OrdineAcquisto in the database
        List<OrdineAcquisto> ordineAcquistoList = ordineAcquistoRepository.findAll();
        assertThat(ordineAcquistoList).hasSize(databaseSizeBeforeCreate + 1);
        OrdineAcquisto testOrdineAcquisto = ordineAcquistoList.get(ordineAcquistoList.size() - 1);
        assertThat(testOrdineAcquisto.getIdDocumento()).isEqualTo(DEFAULT_ID_DOCUMENTO);
        assertThat(testOrdineAcquisto.getdOAData()).isEqualTo(DEFAULT_D_OA_DATA);
        assertThat(testOrdineAcquisto.getNumItem()).isEqualTo(DEFAULT_NUM_ITEM);
        assertThat(testOrdineAcquisto.getCodiceCommessaConvenzione()).isEqualTo(DEFAULT_CODICE_COMMESSA_CONVENZIONE);
        assertThat(testOrdineAcquisto.getCodiceCUP()).isEqualTo(DEFAULT_CODICE_CUP);
        assertThat(testOrdineAcquisto.getCodiceCIG()).isEqualTo(DEFAULT_CODICE_CIG);
    }

    @Test
    @Transactional
    public void createOrdineAcquistoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ordineAcquistoRepository.findAll().size();

        // Create the OrdineAcquisto with an existing ID
        ordineAcquisto.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrdineAcquistoMockMvc.perform(post("/api/ordine-acquistos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ordineAcquisto)))
            .andExpect(status().isBadRequest());

        // Validate the OrdineAcquisto in the database
        List<OrdineAcquisto> ordineAcquistoList = ordineAcquistoRepository.findAll();
        assertThat(ordineAcquistoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrdineAcquistos() throws Exception {
        // Initialize the database
        ordineAcquistoRepository.saveAndFlush(ordineAcquisto);

        // Get all the ordineAcquistoList
        restOrdineAcquistoMockMvc.perform(get("/api/ordine-acquistos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ordineAcquisto.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDocumento").value(hasItem(DEFAULT_ID_DOCUMENTO.toString())))
            .andExpect(jsonPath("$.[*].dOAData").value(hasItem(DEFAULT_D_OA_DATA.toString())))
            .andExpect(jsonPath("$.[*].numItem").value(hasItem(DEFAULT_NUM_ITEM.toString())))
            .andExpect(jsonPath("$.[*].codiceCommessaConvenzione").value(hasItem(DEFAULT_CODICE_COMMESSA_CONVENZIONE.toString())))
            .andExpect(jsonPath("$.[*].codiceCUP").value(hasItem(DEFAULT_CODICE_CUP.toString())))
            .andExpect(jsonPath("$.[*].codiceCIG").value(hasItem(DEFAULT_CODICE_CIG.toString())));
    }
    
    @Test
    @Transactional
    public void getOrdineAcquisto() throws Exception {
        // Initialize the database
        ordineAcquistoRepository.saveAndFlush(ordineAcquisto);

        // Get the ordineAcquisto
        restOrdineAcquistoMockMvc.perform(get("/api/ordine-acquistos/{id}", ordineAcquisto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ordineAcquisto.getId().intValue()))
            .andExpect(jsonPath("$.idDocumento").value(DEFAULT_ID_DOCUMENTO.toString()))
            .andExpect(jsonPath("$.dOAData").value(DEFAULT_D_OA_DATA.toString()))
            .andExpect(jsonPath("$.numItem").value(DEFAULT_NUM_ITEM.toString()))
            .andExpect(jsonPath("$.codiceCommessaConvenzione").value(DEFAULT_CODICE_COMMESSA_CONVENZIONE.toString()))
            .andExpect(jsonPath("$.codiceCUP").value(DEFAULT_CODICE_CUP.toString()))
            .andExpect(jsonPath("$.codiceCIG").value(DEFAULT_CODICE_CIG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrdineAcquisto() throws Exception {
        // Get the ordineAcquisto
        restOrdineAcquistoMockMvc.perform(get("/api/ordine-acquistos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrdineAcquisto() throws Exception {
        // Initialize the database
        ordineAcquistoRepository.saveAndFlush(ordineAcquisto);

        int databaseSizeBeforeUpdate = ordineAcquistoRepository.findAll().size();

        // Update the ordineAcquisto
        OrdineAcquisto updatedOrdineAcquisto = ordineAcquistoRepository.findById(ordineAcquisto.getId()).get();
        // Disconnect from session so that the updates on updatedOrdineAcquisto are not directly saved in db
        em.detach(updatedOrdineAcquisto);
        updatedOrdineAcquisto
            .idDocumento(UPDATED_ID_DOCUMENTO)
            .dOAData(UPDATED_D_OA_DATA)
            .numItem(UPDATED_NUM_ITEM)
            .codiceCommessaConvenzione(UPDATED_CODICE_COMMESSA_CONVENZIONE)
            .codiceCUP(UPDATED_CODICE_CUP)
            .codiceCIG(UPDATED_CODICE_CIG);

        restOrdineAcquistoMockMvc.perform(put("/api/ordine-acquistos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrdineAcquisto)))
            .andExpect(status().isOk());

        // Validate the OrdineAcquisto in the database
        List<OrdineAcquisto> ordineAcquistoList = ordineAcquistoRepository.findAll();
        assertThat(ordineAcquistoList).hasSize(databaseSizeBeforeUpdate);
        OrdineAcquisto testOrdineAcquisto = ordineAcquistoList.get(ordineAcquistoList.size() - 1);
        assertThat(testOrdineAcquisto.getIdDocumento()).isEqualTo(UPDATED_ID_DOCUMENTO);
        assertThat(testOrdineAcquisto.getdOAData()).isEqualTo(UPDATED_D_OA_DATA);
        assertThat(testOrdineAcquisto.getNumItem()).isEqualTo(UPDATED_NUM_ITEM);
        assertThat(testOrdineAcquisto.getCodiceCommessaConvenzione()).isEqualTo(UPDATED_CODICE_COMMESSA_CONVENZIONE);
        assertThat(testOrdineAcquisto.getCodiceCUP()).isEqualTo(UPDATED_CODICE_CUP);
        assertThat(testOrdineAcquisto.getCodiceCIG()).isEqualTo(UPDATED_CODICE_CIG);
    }

    @Test
    @Transactional
    public void updateNonExistingOrdineAcquisto() throws Exception {
        int databaseSizeBeforeUpdate = ordineAcquistoRepository.findAll().size();

        // Create the OrdineAcquisto

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrdineAcquistoMockMvc.perform(put("/api/ordine-acquistos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ordineAcquisto)))
            .andExpect(status().isBadRequest());

        // Validate the OrdineAcquisto in the database
        List<OrdineAcquisto> ordineAcquistoList = ordineAcquistoRepository.findAll();
        assertThat(ordineAcquistoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrdineAcquisto() throws Exception {
        // Initialize the database
        ordineAcquistoRepository.saveAndFlush(ordineAcquisto);

        int databaseSizeBeforeDelete = ordineAcquistoRepository.findAll().size();

        // Delete the ordineAcquisto
        restOrdineAcquistoMockMvc.perform(delete("/api/ordine-acquistos/{id}", ordineAcquisto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<OrdineAcquisto> ordineAcquistoList = ordineAcquistoRepository.findAll();
        assertThat(ordineAcquistoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrdineAcquisto.class);
        OrdineAcquisto ordineAcquisto1 = new OrdineAcquisto();
        ordineAcquisto1.setId(1L);
        OrdineAcquisto ordineAcquisto2 = new OrdineAcquisto();
        ordineAcquisto2.setId(ordineAcquisto1.getId());
        assertThat(ordineAcquisto1).isEqualTo(ordineAcquisto2);
        ordineAcquisto2.setId(2L);
        assertThat(ordineAcquisto1).isNotEqualTo(ordineAcquisto2);
        ordineAcquisto1.setId(null);
        assertThat(ordineAcquisto1).isNotEqualTo(ordineAcquisto2);
    }
}
