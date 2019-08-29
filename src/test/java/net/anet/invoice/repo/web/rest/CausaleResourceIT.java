package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.Causale;
import net.anet.invoice.repo.repository.CausaleRepository;
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
 * Integration tests for the {@Link CausaleResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class CausaleResourceIT {

    private static final String DEFAULT_CAUSALE = "AAAAAAAAAA";
    private static final String UPDATED_CAUSALE = "BBBBBBBBBB";

    @Autowired
    private CausaleRepository causaleRepository;

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

    private MockMvc restCausaleMockMvc;

    private Causale causale;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CausaleResource causaleResource = new CausaleResource(causaleRepository);
        this.restCausaleMockMvc = MockMvcBuilders.standaloneSetup(causaleResource)
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
    public static Causale createEntity(EntityManager em) {
        Causale causale = new Causale()
            .causale(DEFAULT_CAUSALE);
        return causale;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Causale createUpdatedEntity(EntityManager em) {
        Causale causale = new Causale()
            .causale(UPDATED_CAUSALE);
        return causale;
    }

    @BeforeEach
    public void initTest() {
        causale = createEntity(em);
    }

    @Test
    @Transactional
    public void createCausale() throws Exception {
        int databaseSizeBeforeCreate = causaleRepository.findAll().size();

        // Create the Causale
        restCausaleMockMvc.perform(post("/api/causales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(causale)))
            .andExpect(status().isCreated());

        // Validate the Causale in the database
        List<Causale> causaleList = causaleRepository.findAll();
        assertThat(causaleList).hasSize(databaseSizeBeforeCreate + 1);
        Causale testCausale = causaleList.get(causaleList.size() - 1);
        assertThat(testCausale.getCausale()).isEqualTo(DEFAULT_CAUSALE);
    }

    @Test
    @Transactional
    public void createCausaleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = causaleRepository.findAll().size();

        // Create the Causale with an existing ID
        causale.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCausaleMockMvc.perform(post("/api/causales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(causale)))
            .andExpect(status().isBadRequest());

        // Validate the Causale in the database
        List<Causale> causaleList = causaleRepository.findAll();
        assertThat(causaleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCausales() throws Exception {
        // Initialize the database
        causaleRepository.saveAndFlush(causale);

        // Get all the causaleList
        restCausaleMockMvc.perform(get("/api/causales?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(causale.getId().intValue())))
            .andExpect(jsonPath("$.[*].causale").value(hasItem(DEFAULT_CAUSALE.toString())));
    }
    
    @Test
    @Transactional
    public void getCausale() throws Exception {
        // Initialize the database
        causaleRepository.saveAndFlush(causale);

        // Get the causale
        restCausaleMockMvc.perform(get("/api/causales/{id}", causale.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(causale.getId().intValue()))
            .andExpect(jsonPath("$.causale").value(DEFAULT_CAUSALE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCausale() throws Exception {
        // Get the causale
        restCausaleMockMvc.perform(get("/api/causales/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCausale() throws Exception {
        // Initialize the database
        causaleRepository.saveAndFlush(causale);

        int databaseSizeBeforeUpdate = causaleRepository.findAll().size();

        // Update the causale
        Causale updatedCausale = causaleRepository.findById(causale.getId()).get();
        // Disconnect from session so that the updates on updatedCausale are not directly saved in db
        em.detach(updatedCausale);
        updatedCausale
            .causale(UPDATED_CAUSALE);

        restCausaleMockMvc.perform(put("/api/causales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCausale)))
            .andExpect(status().isOk());

        // Validate the Causale in the database
        List<Causale> causaleList = causaleRepository.findAll();
        assertThat(causaleList).hasSize(databaseSizeBeforeUpdate);
        Causale testCausale = causaleList.get(causaleList.size() - 1);
        assertThat(testCausale.getCausale()).isEqualTo(UPDATED_CAUSALE);
    }

    @Test
    @Transactional
    public void updateNonExistingCausale() throws Exception {
        int databaseSizeBeforeUpdate = causaleRepository.findAll().size();

        // Create the Causale

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCausaleMockMvc.perform(put("/api/causales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(causale)))
            .andExpect(status().isBadRequest());

        // Validate the Causale in the database
        List<Causale> causaleList = causaleRepository.findAll();
        assertThat(causaleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCausale() throws Exception {
        // Initialize the database
        causaleRepository.saveAndFlush(causale);

        int databaseSizeBeforeDelete = causaleRepository.findAll().size();

        // Delete the causale
        restCausaleMockMvc.perform(delete("/api/causales/{id}", causale.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Causale> causaleList = causaleRepository.findAll();
        assertThat(causaleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Causale.class);
        Causale causale1 = new Causale();
        causale1.setId(1L);
        Causale causale2 = new Causale();
        causale2.setId(causale1.getId());
        assertThat(causale1).isEqualTo(causale2);
        causale2.setId(2L);
        assertThat(causale1).isNotEqualTo(causale2);
        causale1.setId(null);
        assertThat(causale1).isNotEqualTo(causale2);
    }
}
