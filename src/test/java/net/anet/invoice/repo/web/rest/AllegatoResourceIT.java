package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.Allegato;
import net.anet.invoice.repo.repository.AllegatoRepository;
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
 * Integration tests for the {@Link AllegatoResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class AllegatoResourceIT {

    private static final String DEFAULT_NOME_ATTACHMENT = "AAAAAAAAAA";
    private static final String UPDATED_NOME_ATTACHMENT = "BBBBBBBBBB";

    private static final String DEFAULT_ALGORITMO_COMPRESSIONE = "AAAAAAAAAA";
    private static final String UPDATED_ALGORITMO_COMPRESSIONE = "BBBBBBBBBB";

    private static final String DEFAULT_FORMATO_ATTACHMENT = "AAAAAAAAAA";
    private static final String UPDATED_FORMATO_ATTACHMENT = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIZIONE_ATTACHMENT = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE_ATTACHMENT = "BBBBBBBBBB";

    private static final String DEFAULT_ATTACHMENT = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHMENT = "BBBBBBBBBB";

    @Autowired
    private AllegatoRepository allegatoRepository;

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

    private MockMvc restAllegatoMockMvc;

    private Allegato allegato;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AllegatoResource allegatoResource = new AllegatoResource(allegatoRepository);
        this.restAllegatoMockMvc = MockMvcBuilders.standaloneSetup(allegatoResource)
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
    public static Allegato createEntity(EntityManager em) {
        Allegato allegato = new Allegato()
            .nomeAttachment(DEFAULT_NOME_ATTACHMENT)
            .algoritmoCompressione(DEFAULT_ALGORITMO_COMPRESSIONE)
            .formatoAttachment(DEFAULT_FORMATO_ATTACHMENT)
            .descrizioneAttachment(DEFAULT_DESCRIZIONE_ATTACHMENT)
            .attachment(DEFAULT_ATTACHMENT);
        return allegato;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Allegato createUpdatedEntity(EntityManager em) {
        Allegato allegato = new Allegato()
            .nomeAttachment(UPDATED_NOME_ATTACHMENT)
            .algoritmoCompressione(UPDATED_ALGORITMO_COMPRESSIONE)
            .formatoAttachment(UPDATED_FORMATO_ATTACHMENT)
            .descrizioneAttachment(UPDATED_DESCRIZIONE_ATTACHMENT)
            .attachment(UPDATED_ATTACHMENT);
        return allegato;
    }

    @BeforeEach
    public void initTest() {
        allegato = createEntity(em);
    }

    @Test
    @Transactional
    public void createAllegato() throws Exception {
        int databaseSizeBeforeCreate = allegatoRepository.findAll().size();

        // Create the Allegato
        restAllegatoMockMvc.perform(post("/api/allegatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(allegato)))
            .andExpect(status().isCreated());

        // Validate the Allegato in the database
        List<Allegato> allegatoList = allegatoRepository.findAll();
        assertThat(allegatoList).hasSize(databaseSizeBeforeCreate + 1);
        Allegato testAllegato = allegatoList.get(allegatoList.size() - 1);
        assertThat(testAllegato.getNomeAttachment()).isEqualTo(DEFAULT_NOME_ATTACHMENT);
        assertThat(testAllegato.getAlgoritmoCompressione()).isEqualTo(DEFAULT_ALGORITMO_COMPRESSIONE);
        assertThat(testAllegato.getFormatoAttachment()).isEqualTo(DEFAULT_FORMATO_ATTACHMENT);
        assertThat(testAllegato.getDescrizioneAttachment()).isEqualTo(DEFAULT_DESCRIZIONE_ATTACHMENT);
        assertThat(testAllegato.getAttachment()).isEqualTo(DEFAULT_ATTACHMENT);
    }

    @Test
    @Transactional
    public void createAllegatoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = allegatoRepository.findAll().size();

        // Create the Allegato with an existing ID
        allegato.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAllegatoMockMvc.perform(post("/api/allegatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(allegato)))
            .andExpect(status().isBadRequest());

        // Validate the Allegato in the database
        List<Allegato> allegatoList = allegatoRepository.findAll();
        assertThat(allegatoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAllegatoes() throws Exception {
        // Initialize the database
        allegatoRepository.saveAndFlush(allegato);

        // Get all the allegatoList
        restAllegatoMockMvc.perform(get("/api/allegatoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(allegato.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomeAttachment").value(hasItem(DEFAULT_NOME_ATTACHMENT.toString())))
            .andExpect(jsonPath("$.[*].algoritmoCompressione").value(hasItem(DEFAULT_ALGORITMO_COMPRESSIONE.toString())))
            .andExpect(jsonPath("$.[*].formatoAttachment").value(hasItem(DEFAULT_FORMATO_ATTACHMENT.toString())))
            .andExpect(jsonPath("$.[*].descrizioneAttachment").value(hasItem(DEFAULT_DESCRIZIONE_ATTACHMENT.toString())))
            .andExpect(jsonPath("$.[*].attachment").value(hasItem(DEFAULT_ATTACHMENT.toString())));
    }
    
    @Test
    @Transactional
    public void getAllegato() throws Exception {
        // Initialize the database
        allegatoRepository.saveAndFlush(allegato);

        // Get the allegato
        restAllegatoMockMvc.perform(get("/api/allegatoes/{id}", allegato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(allegato.getId().intValue()))
            .andExpect(jsonPath("$.nomeAttachment").value(DEFAULT_NOME_ATTACHMENT.toString()))
            .andExpect(jsonPath("$.algoritmoCompressione").value(DEFAULT_ALGORITMO_COMPRESSIONE.toString()))
            .andExpect(jsonPath("$.formatoAttachment").value(DEFAULT_FORMATO_ATTACHMENT.toString()))
            .andExpect(jsonPath("$.descrizioneAttachment").value(DEFAULT_DESCRIZIONE_ATTACHMENT.toString()))
            .andExpect(jsonPath("$.attachment").value(DEFAULT_ATTACHMENT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAllegato() throws Exception {
        // Get the allegato
        restAllegatoMockMvc.perform(get("/api/allegatoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAllegato() throws Exception {
        // Initialize the database
        allegatoRepository.saveAndFlush(allegato);

        int databaseSizeBeforeUpdate = allegatoRepository.findAll().size();

        // Update the allegato
        Allegato updatedAllegato = allegatoRepository.findById(allegato.getId()).get();
        // Disconnect from session so that the updates on updatedAllegato are not directly saved in db
        em.detach(updatedAllegato);
        updatedAllegato
            .nomeAttachment(UPDATED_NOME_ATTACHMENT)
            .algoritmoCompressione(UPDATED_ALGORITMO_COMPRESSIONE)
            .formatoAttachment(UPDATED_FORMATO_ATTACHMENT)
            .descrizioneAttachment(UPDATED_DESCRIZIONE_ATTACHMENT)
            .attachment(UPDATED_ATTACHMENT);

        restAllegatoMockMvc.perform(put("/api/allegatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAllegato)))
            .andExpect(status().isOk());

        // Validate the Allegato in the database
        List<Allegato> allegatoList = allegatoRepository.findAll();
        assertThat(allegatoList).hasSize(databaseSizeBeforeUpdate);
        Allegato testAllegato = allegatoList.get(allegatoList.size() - 1);
        assertThat(testAllegato.getNomeAttachment()).isEqualTo(UPDATED_NOME_ATTACHMENT);
        assertThat(testAllegato.getAlgoritmoCompressione()).isEqualTo(UPDATED_ALGORITMO_COMPRESSIONE);
        assertThat(testAllegato.getFormatoAttachment()).isEqualTo(UPDATED_FORMATO_ATTACHMENT);
        assertThat(testAllegato.getDescrizioneAttachment()).isEqualTo(UPDATED_DESCRIZIONE_ATTACHMENT);
        assertThat(testAllegato.getAttachment()).isEqualTo(UPDATED_ATTACHMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingAllegato() throws Exception {
        int databaseSizeBeforeUpdate = allegatoRepository.findAll().size();

        // Create the Allegato

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAllegatoMockMvc.perform(put("/api/allegatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(allegato)))
            .andExpect(status().isBadRequest());

        // Validate the Allegato in the database
        List<Allegato> allegatoList = allegatoRepository.findAll();
        assertThat(allegatoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAllegato() throws Exception {
        // Initialize the database
        allegatoRepository.saveAndFlush(allegato);

        int databaseSizeBeforeDelete = allegatoRepository.findAll().size();

        // Delete the allegato
        restAllegatoMockMvc.perform(delete("/api/allegatoes/{id}", allegato.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Allegato> allegatoList = allegatoRepository.findAll();
        assertThat(allegatoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Allegato.class);
        Allegato allegato1 = new Allegato();
        allegato1.setId(1L);
        Allegato allegato2 = new Allegato();
        allegato2.setId(allegato1.getId());
        assertThat(allegato1).isEqualTo(allegato2);
        allegato2.setId(2L);
        assertThat(allegato1).isNotEqualTo(allegato2);
        allegato1.setId(null);
        assertThat(allegato1).isNotEqualTo(allegato2);
    }
}
