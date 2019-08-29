package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.SAL;
import net.anet.invoice.repo.repository.SALRepository;
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
 * Integration tests for the {@Link SALResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class SALResourceIT {

    private static final String DEFAULT_RIFERIMENTO_FASE = "AAA";
    private static final String UPDATED_RIFERIMENTO_FASE = "BBB";

    @Autowired
    private SALRepository sALRepository;

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

    private MockMvc restSALMockMvc;

    private SAL sAL;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SALResource sALResource = new SALResource(sALRepository);
        this.restSALMockMvc = MockMvcBuilders.standaloneSetup(sALResource)
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
    public static SAL createEntity(EntityManager em) {
        SAL sAL = new SAL()
            .riferimentoFase(DEFAULT_RIFERIMENTO_FASE);
        return sAL;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SAL createUpdatedEntity(EntityManager em) {
        SAL sAL = new SAL()
            .riferimentoFase(UPDATED_RIFERIMENTO_FASE);
        return sAL;
    }

    @BeforeEach
    public void initTest() {
        sAL = createEntity(em);
    }

    @Test
    @Transactional
    public void createSAL() throws Exception {
        int databaseSizeBeforeCreate = sALRepository.findAll().size();

        // Create the SAL
        restSALMockMvc.perform(post("/api/sals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sAL)))
            .andExpect(status().isCreated());

        // Validate the SAL in the database
        List<SAL> sALList = sALRepository.findAll();
        assertThat(sALList).hasSize(databaseSizeBeforeCreate + 1);
        SAL testSAL = sALList.get(sALList.size() - 1);
        assertThat(testSAL.getRiferimentoFase()).isEqualTo(DEFAULT_RIFERIMENTO_FASE);
    }

    @Test
    @Transactional
    public void createSALWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sALRepository.findAll().size();

        // Create the SAL with an existing ID
        sAL.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSALMockMvc.perform(post("/api/sals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sAL)))
            .andExpect(status().isBadRequest());

        // Validate the SAL in the database
        List<SAL> sALList = sALRepository.findAll();
        assertThat(sALList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSALS() throws Exception {
        // Initialize the database
        sALRepository.saveAndFlush(sAL);

        // Get all the sALList
        restSALMockMvc.perform(get("/api/sals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sAL.getId().intValue())))
            .andExpect(jsonPath("$.[*].riferimentoFase").value(hasItem(DEFAULT_RIFERIMENTO_FASE.toString())));
    }
    
    @Test
    @Transactional
    public void getSAL() throws Exception {
        // Initialize the database
        sALRepository.saveAndFlush(sAL);

        // Get the sAL
        restSALMockMvc.perform(get("/api/sals/{id}", sAL.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sAL.getId().intValue()))
            .andExpect(jsonPath("$.riferimentoFase").value(DEFAULT_RIFERIMENTO_FASE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSAL() throws Exception {
        // Get the sAL
        restSALMockMvc.perform(get("/api/sals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSAL() throws Exception {
        // Initialize the database
        sALRepository.saveAndFlush(sAL);

        int databaseSizeBeforeUpdate = sALRepository.findAll().size();

        // Update the sAL
        SAL updatedSAL = sALRepository.findById(sAL.getId()).get();
        // Disconnect from session so that the updates on updatedSAL are not directly saved in db
        em.detach(updatedSAL);
        updatedSAL
            .riferimentoFase(UPDATED_RIFERIMENTO_FASE);

        restSALMockMvc.perform(put("/api/sals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSAL)))
            .andExpect(status().isOk());

        // Validate the SAL in the database
        List<SAL> sALList = sALRepository.findAll();
        assertThat(sALList).hasSize(databaseSizeBeforeUpdate);
        SAL testSAL = sALList.get(sALList.size() - 1);
        assertThat(testSAL.getRiferimentoFase()).isEqualTo(UPDATED_RIFERIMENTO_FASE);
    }

    @Test
    @Transactional
    public void updateNonExistingSAL() throws Exception {
        int databaseSizeBeforeUpdate = sALRepository.findAll().size();

        // Create the SAL

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSALMockMvc.perform(put("/api/sals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sAL)))
            .andExpect(status().isBadRequest());

        // Validate the SAL in the database
        List<SAL> sALList = sALRepository.findAll();
        assertThat(sALList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSAL() throws Exception {
        // Initialize the database
        sALRepository.saveAndFlush(sAL);

        int databaseSizeBeforeDelete = sALRepository.findAll().size();

        // Delete the sAL
        restSALMockMvc.perform(delete("/api/sals/{id}", sAL.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<SAL> sALList = sALRepository.findAll();
        assertThat(sALList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SAL.class);
        SAL sAL1 = new SAL();
        sAL1.setId(1L);
        SAL sAL2 = new SAL();
        sAL2.setId(sAL1.getId());
        assertThat(sAL1).isEqualTo(sAL2);
        sAL2.setId(2L);
        assertThat(sAL1).isNotEqualTo(sAL2);
        sAL1.setId(null);
        assertThat(sAL1).isNotEqualTo(sAL2);
    }
}
