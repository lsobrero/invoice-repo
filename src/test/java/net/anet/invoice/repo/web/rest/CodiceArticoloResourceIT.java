package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.CodiceArticolo;
import net.anet.invoice.repo.repository.CodiceArticoloRepository;
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
 * Integration tests for the {@Link CodiceArticoloResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class CodiceArticoloResourceIT {

    private static final String DEFAULT_CODICE_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_TIPO = "BBBBBBBBBB";

    private static final String DEFAULT_CODICE_VALORE = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_VALORE = "BBBBBBBBBB";

    @Autowired
    private CodiceArticoloRepository codiceArticoloRepository;

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

    private MockMvc restCodiceArticoloMockMvc;

    private CodiceArticolo codiceArticolo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CodiceArticoloResource codiceArticoloResource = new CodiceArticoloResource(codiceArticoloRepository);
        this.restCodiceArticoloMockMvc = MockMvcBuilders.standaloneSetup(codiceArticoloResource)
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
    public static CodiceArticolo createEntity(EntityManager em) {
        CodiceArticolo codiceArticolo = new CodiceArticolo()
            .codiceTipo(DEFAULT_CODICE_TIPO)
            .codiceValore(DEFAULT_CODICE_VALORE);
        return codiceArticolo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CodiceArticolo createUpdatedEntity(EntityManager em) {
        CodiceArticolo codiceArticolo = new CodiceArticolo()
            .codiceTipo(UPDATED_CODICE_TIPO)
            .codiceValore(UPDATED_CODICE_VALORE);
        return codiceArticolo;
    }

    @BeforeEach
    public void initTest() {
        codiceArticolo = createEntity(em);
    }

    @Test
    @Transactional
    public void createCodiceArticolo() throws Exception {
        int databaseSizeBeforeCreate = codiceArticoloRepository.findAll().size();

        // Create the CodiceArticolo
        restCodiceArticoloMockMvc.perform(post("/api/codice-articolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codiceArticolo)))
            .andExpect(status().isCreated());

        // Validate the CodiceArticolo in the database
        List<CodiceArticolo> codiceArticoloList = codiceArticoloRepository.findAll();
        assertThat(codiceArticoloList).hasSize(databaseSizeBeforeCreate + 1);
        CodiceArticolo testCodiceArticolo = codiceArticoloList.get(codiceArticoloList.size() - 1);
        assertThat(testCodiceArticolo.getCodiceTipo()).isEqualTo(DEFAULT_CODICE_TIPO);
        assertThat(testCodiceArticolo.getCodiceValore()).isEqualTo(DEFAULT_CODICE_VALORE);
    }

    @Test
    @Transactional
    public void createCodiceArticoloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = codiceArticoloRepository.findAll().size();

        // Create the CodiceArticolo with an existing ID
        codiceArticolo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCodiceArticoloMockMvc.perform(post("/api/codice-articolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codiceArticolo)))
            .andExpect(status().isBadRequest());

        // Validate the CodiceArticolo in the database
        List<CodiceArticolo> codiceArticoloList = codiceArticoloRepository.findAll();
        assertThat(codiceArticoloList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCodiceArticolos() throws Exception {
        // Initialize the database
        codiceArticoloRepository.saveAndFlush(codiceArticolo);

        // Get all the codiceArticoloList
        restCodiceArticoloMockMvc.perform(get("/api/codice-articolos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(codiceArticolo.getId().intValue())))
            .andExpect(jsonPath("$.[*].codiceTipo").value(hasItem(DEFAULT_CODICE_TIPO.toString())))
            .andExpect(jsonPath("$.[*].codiceValore").value(hasItem(DEFAULT_CODICE_VALORE.toString())));
    }
    
    @Test
    @Transactional
    public void getCodiceArticolo() throws Exception {
        // Initialize the database
        codiceArticoloRepository.saveAndFlush(codiceArticolo);

        // Get the codiceArticolo
        restCodiceArticoloMockMvc.perform(get("/api/codice-articolos/{id}", codiceArticolo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(codiceArticolo.getId().intValue()))
            .andExpect(jsonPath("$.codiceTipo").value(DEFAULT_CODICE_TIPO.toString()))
            .andExpect(jsonPath("$.codiceValore").value(DEFAULT_CODICE_VALORE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCodiceArticolo() throws Exception {
        // Get the codiceArticolo
        restCodiceArticoloMockMvc.perform(get("/api/codice-articolos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCodiceArticolo() throws Exception {
        // Initialize the database
        codiceArticoloRepository.saveAndFlush(codiceArticolo);

        int databaseSizeBeforeUpdate = codiceArticoloRepository.findAll().size();

        // Update the codiceArticolo
        CodiceArticolo updatedCodiceArticolo = codiceArticoloRepository.findById(codiceArticolo.getId()).get();
        // Disconnect from session so that the updates on updatedCodiceArticolo are not directly saved in db
        em.detach(updatedCodiceArticolo);
        updatedCodiceArticolo
            .codiceTipo(UPDATED_CODICE_TIPO)
            .codiceValore(UPDATED_CODICE_VALORE);

        restCodiceArticoloMockMvc.perform(put("/api/codice-articolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCodiceArticolo)))
            .andExpect(status().isOk());

        // Validate the CodiceArticolo in the database
        List<CodiceArticolo> codiceArticoloList = codiceArticoloRepository.findAll();
        assertThat(codiceArticoloList).hasSize(databaseSizeBeforeUpdate);
        CodiceArticolo testCodiceArticolo = codiceArticoloList.get(codiceArticoloList.size() - 1);
        assertThat(testCodiceArticolo.getCodiceTipo()).isEqualTo(UPDATED_CODICE_TIPO);
        assertThat(testCodiceArticolo.getCodiceValore()).isEqualTo(UPDATED_CODICE_VALORE);
    }

    @Test
    @Transactional
    public void updateNonExistingCodiceArticolo() throws Exception {
        int databaseSizeBeforeUpdate = codiceArticoloRepository.findAll().size();

        // Create the CodiceArticolo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCodiceArticoloMockMvc.perform(put("/api/codice-articolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codiceArticolo)))
            .andExpect(status().isBadRequest());

        // Validate the CodiceArticolo in the database
        List<CodiceArticolo> codiceArticoloList = codiceArticoloRepository.findAll();
        assertThat(codiceArticoloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCodiceArticolo() throws Exception {
        // Initialize the database
        codiceArticoloRepository.saveAndFlush(codiceArticolo);

        int databaseSizeBeforeDelete = codiceArticoloRepository.findAll().size();

        // Delete the codiceArticolo
        restCodiceArticoloMockMvc.perform(delete("/api/codice-articolos/{id}", codiceArticolo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<CodiceArticolo> codiceArticoloList = codiceArticoloRepository.findAll();
        assertThat(codiceArticoloList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CodiceArticolo.class);
        CodiceArticolo codiceArticolo1 = new CodiceArticolo();
        codiceArticolo1.setId(1L);
        CodiceArticolo codiceArticolo2 = new CodiceArticolo();
        codiceArticolo2.setId(codiceArticolo1.getId());
        assertThat(codiceArticolo1).isEqualTo(codiceArticolo2);
        codiceArticolo2.setId(2L);
        assertThat(codiceArticolo1).isNotEqualTo(codiceArticolo2);
        codiceArticolo1.setId(null);
        assertThat(codiceArticolo1).isNotEqualTo(codiceArticolo2);
    }
}
