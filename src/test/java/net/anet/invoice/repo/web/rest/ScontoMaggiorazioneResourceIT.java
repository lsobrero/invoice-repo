package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.ScontoMaggiorazione;
import net.anet.invoice.repo.repository.ScontoMaggiorazioneRepository;
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
 * Integration tests for the {@Link ScontoMaggiorazioneResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class ScontoMaggiorazioneResourceIT {

    private static final String DEFAULT_S_M_TIPO = "AA";
    private static final String UPDATED_S_M_TIPO = "BB";

    private static final String DEFAULT_S_M_PERCENTUALE = "AAAAAA";
    private static final String UPDATED_S_M_PERCENTUALE = "BBBBBB";

    private static final String DEFAULT_S_M_IMPORTO = "AAAAAAAAAA";
    private static final String UPDATED_S_M_IMPORTO = "BBBBBBBBBB";

    @Autowired
    private ScontoMaggiorazioneRepository scontoMaggiorazioneRepository;

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

    private MockMvc restScontoMaggiorazioneMockMvc;

    private ScontoMaggiorazione scontoMaggiorazione;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ScontoMaggiorazioneResource scontoMaggiorazioneResource = new ScontoMaggiorazioneResource(scontoMaggiorazioneRepository);
        this.restScontoMaggiorazioneMockMvc = MockMvcBuilders.standaloneSetup(scontoMaggiorazioneResource)
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
    public static ScontoMaggiorazione createEntity(EntityManager em) {
        ScontoMaggiorazione scontoMaggiorazione = new ScontoMaggiorazione()
            .sMTipo(DEFAULT_S_M_TIPO)
            .sMPercentuale(DEFAULT_S_M_PERCENTUALE)
            .sMImporto(DEFAULT_S_M_IMPORTO);
        return scontoMaggiorazione;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ScontoMaggiorazione createUpdatedEntity(EntityManager em) {
        ScontoMaggiorazione scontoMaggiorazione = new ScontoMaggiorazione()
            .sMTipo(UPDATED_S_M_TIPO)
            .sMPercentuale(UPDATED_S_M_PERCENTUALE)
            .sMImporto(UPDATED_S_M_IMPORTO);
        return scontoMaggiorazione;
    }

    @BeforeEach
    public void initTest() {
        scontoMaggiorazione = createEntity(em);
    }

    @Test
    @Transactional
    public void createScontoMaggiorazione() throws Exception {
        int databaseSizeBeforeCreate = scontoMaggiorazioneRepository.findAll().size();

        // Create the ScontoMaggiorazione
        restScontoMaggiorazioneMockMvc.perform(post("/api/sconto-maggioraziones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scontoMaggiorazione)))
            .andExpect(status().isCreated());

        // Validate the ScontoMaggiorazione in the database
        List<ScontoMaggiorazione> scontoMaggiorazioneList = scontoMaggiorazioneRepository.findAll();
        assertThat(scontoMaggiorazioneList).hasSize(databaseSizeBeforeCreate + 1);
        ScontoMaggiorazione testScontoMaggiorazione = scontoMaggiorazioneList.get(scontoMaggiorazioneList.size() - 1);
        assertThat(testScontoMaggiorazione.getsMTipo()).isEqualTo(DEFAULT_S_M_TIPO);
        assertThat(testScontoMaggiorazione.getsMPercentuale()).isEqualTo(DEFAULT_S_M_PERCENTUALE);
        assertThat(testScontoMaggiorazione.getsMImporto()).isEqualTo(DEFAULT_S_M_IMPORTO);
    }

    @Test
    @Transactional
    public void createScontoMaggiorazioneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = scontoMaggiorazioneRepository.findAll().size();

        // Create the ScontoMaggiorazione with an existing ID
        scontoMaggiorazione.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restScontoMaggiorazioneMockMvc.perform(post("/api/sconto-maggioraziones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scontoMaggiorazione)))
            .andExpect(status().isBadRequest());

        // Validate the ScontoMaggiorazione in the database
        List<ScontoMaggiorazione> scontoMaggiorazioneList = scontoMaggiorazioneRepository.findAll();
        assertThat(scontoMaggiorazioneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllScontoMaggioraziones() throws Exception {
        // Initialize the database
        scontoMaggiorazioneRepository.saveAndFlush(scontoMaggiorazione);

        // Get all the scontoMaggiorazioneList
        restScontoMaggiorazioneMockMvc.perform(get("/api/sconto-maggioraziones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(scontoMaggiorazione.getId().intValue())))
            .andExpect(jsonPath("$.[*].sMTipo").value(hasItem(DEFAULT_S_M_TIPO.toString())))
            .andExpect(jsonPath("$.[*].sMPercentuale").value(hasItem(DEFAULT_S_M_PERCENTUALE.toString())))
            .andExpect(jsonPath("$.[*].sMImporto").value(hasItem(DEFAULT_S_M_IMPORTO.toString())));
    }
    
    @Test
    @Transactional
    public void getScontoMaggiorazione() throws Exception {
        // Initialize the database
        scontoMaggiorazioneRepository.saveAndFlush(scontoMaggiorazione);

        // Get the scontoMaggiorazione
        restScontoMaggiorazioneMockMvc.perform(get("/api/sconto-maggioraziones/{id}", scontoMaggiorazione.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(scontoMaggiorazione.getId().intValue()))
            .andExpect(jsonPath("$.sMTipo").value(DEFAULT_S_M_TIPO.toString()))
            .andExpect(jsonPath("$.sMPercentuale").value(DEFAULT_S_M_PERCENTUALE.toString()))
            .andExpect(jsonPath("$.sMImporto").value(DEFAULT_S_M_IMPORTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingScontoMaggiorazione() throws Exception {
        // Get the scontoMaggiorazione
        restScontoMaggiorazioneMockMvc.perform(get("/api/sconto-maggioraziones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateScontoMaggiorazione() throws Exception {
        // Initialize the database
        scontoMaggiorazioneRepository.saveAndFlush(scontoMaggiorazione);

        int databaseSizeBeforeUpdate = scontoMaggiorazioneRepository.findAll().size();

        // Update the scontoMaggiorazione
        ScontoMaggiorazione updatedScontoMaggiorazione = scontoMaggiorazioneRepository.findById(scontoMaggiorazione.getId()).get();
        // Disconnect from session so that the updates on updatedScontoMaggiorazione are not directly saved in db
        em.detach(updatedScontoMaggiorazione);
        updatedScontoMaggiorazione
            .sMTipo(UPDATED_S_M_TIPO)
            .sMPercentuale(UPDATED_S_M_PERCENTUALE)
            .sMImporto(UPDATED_S_M_IMPORTO);

        restScontoMaggiorazioneMockMvc.perform(put("/api/sconto-maggioraziones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedScontoMaggiorazione)))
            .andExpect(status().isOk());

        // Validate the ScontoMaggiorazione in the database
        List<ScontoMaggiorazione> scontoMaggiorazioneList = scontoMaggiorazioneRepository.findAll();
        assertThat(scontoMaggiorazioneList).hasSize(databaseSizeBeforeUpdate);
        ScontoMaggiorazione testScontoMaggiorazione = scontoMaggiorazioneList.get(scontoMaggiorazioneList.size() - 1);
        assertThat(testScontoMaggiorazione.getsMTipo()).isEqualTo(UPDATED_S_M_TIPO);
        assertThat(testScontoMaggiorazione.getsMPercentuale()).isEqualTo(UPDATED_S_M_PERCENTUALE);
        assertThat(testScontoMaggiorazione.getsMImporto()).isEqualTo(UPDATED_S_M_IMPORTO);
    }

    @Test
    @Transactional
    public void updateNonExistingScontoMaggiorazione() throws Exception {
        int databaseSizeBeforeUpdate = scontoMaggiorazioneRepository.findAll().size();

        // Create the ScontoMaggiorazione

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restScontoMaggiorazioneMockMvc.perform(put("/api/sconto-maggioraziones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scontoMaggiorazione)))
            .andExpect(status().isBadRequest());

        // Validate the ScontoMaggiorazione in the database
        List<ScontoMaggiorazione> scontoMaggiorazioneList = scontoMaggiorazioneRepository.findAll();
        assertThat(scontoMaggiorazioneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteScontoMaggiorazione() throws Exception {
        // Initialize the database
        scontoMaggiorazioneRepository.saveAndFlush(scontoMaggiorazione);

        int databaseSizeBeforeDelete = scontoMaggiorazioneRepository.findAll().size();

        // Delete the scontoMaggiorazione
        restScontoMaggiorazioneMockMvc.perform(delete("/api/sconto-maggioraziones/{id}", scontoMaggiorazione.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ScontoMaggiorazione> scontoMaggiorazioneList = scontoMaggiorazioneRepository.findAll();
        assertThat(scontoMaggiorazioneList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ScontoMaggiorazione.class);
        ScontoMaggiorazione scontoMaggiorazione1 = new ScontoMaggiorazione();
        scontoMaggiorazione1.setId(1L);
        ScontoMaggiorazione scontoMaggiorazione2 = new ScontoMaggiorazione();
        scontoMaggiorazione2.setId(scontoMaggiorazione1.getId());
        assertThat(scontoMaggiorazione1).isEqualTo(scontoMaggiorazione2);
        scontoMaggiorazione2.setId(2L);
        assertThat(scontoMaggiorazione1).isNotEqualTo(scontoMaggiorazione2);
        scontoMaggiorazione1.setId(null);
        assertThat(scontoMaggiorazione1).isNotEqualTo(scontoMaggiorazione2);
    }
}
