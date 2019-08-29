package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.NumeroLinea;
import net.anet.invoice.repo.repository.NumeroLineaRepository;
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
 * Integration tests for the {@Link NumeroLineaResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class NumeroLineaResourceIT {

    private static final String DEFAULT_RIFERIMENTO_NUMERO_LINEA = "AAAA";
    private static final String UPDATED_RIFERIMENTO_NUMERO_LINEA = "BBBB";

    @Autowired
    private NumeroLineaRepository numeroLineaRepository;

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

    private MockMvc restNumeroLineaMockMvc;

    private NumeroLinea numeroLinea;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NumeroLineaResource numeroLineaResource = new NumeroLineaResource(numeroLineaRepository);
        this.restNumeroLineaMockMvc = MockMvcBuilders.standaloneSetup(numeroLineaResource)
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
    public static NumeroLinea createEntity(EntityManager em) {
        NumeroLinea numeroLinea = new NumeroLinea()
            .riferimentoNumeroLinea(DEFAULT_RIFERIMENTO_NUMERO_LINEA);
        return numeroLinea;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NumeroLinea createUpdatedEntity(EntityManager em) {
        NumeroLinea numeroLinea = new NumeroLinea()
            .riferimentoNumeroLinea(UPDATED_RIFERIMENTO_NUMERO_LINEA);
        return numeroLinea;
    }

    @BeforeEach
    public void initTest() {
        numeroLinea = createEntity(em);
    }

    @Test
    @Transactional
    public void createNumeroLinea() throws Exception {
        int databaseSizeBeforeCreate = numeroLineaRepository.findAll().size();

        // Create the NumeroLinea
        restNumeroLineaMockMvc.perform(post("/api/numero-lineas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeroLinea)))
            .andExpect(status().isCreated());

        // Validate the NumeroLinea in the database
        List<NumeroLinea> numeroLineaList = numeroLineaRepository.findAll();
        assertThat(numeroLineaList).hasSize(databaseSizeBeforeCreate + 1);
        NumeroLinea testNumeroLinea = numeroLineaList.get(numeroLineaList.size() - 1);
        assertThat(testNumeroLinea.getRiferimentoNumeroLinea()).isEqualTo(DEFAULT_RIFERIMENTO_NUMERO_LINEA);
    }

    @Test
    @Transactional
    public void createNumeroLineaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = numeroLineaRepository.findAll().size();

        // Create the NumeroLinea with an existing ID
        numeroLinea.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNumeroLineaMockMvc.perform(post("/api/numero-lineas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeroLinea)))
            .andExpect(status().isBadRequest());

        // Validate the NumeroLinea in the database
        List<NumeroLinea> numeroLineaList = numeroLineaRepository.findAll();
        assertThat(numeroLineaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNumeroLineas() throws Exception {
        // Initialize the database
        numeroLineaRepository.saveAndFlush(numeroLinea);

        // Get all the numeroLineaList
        restNumeroLineaMockMvc.perform(get("/api/numero-lineas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(numeroLinea.getId().intValue())))
            .andExpect(jsonPath("$.[*].riferimentoNumeroLinea").value(hasItem(DEFAULT_RIFERIMENTO_NUMERO_LINEA.toString())));
    }
    
    @Test
    @Transactional
    public void getNumeroLinea() throws Exception {
        // Initialize the database
        numeroLineaRepository.saveAndFlush(numeroLinea);

        // Get the numeroLinea
        restNumeroLineaMockMvc.perform(get("/api/numero-lineas/{id}", numeroLinea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(numeroLinea.getId().intValue()))
            .andExpect(jsonPath("$.riferimentoNumeroLinea").value(DEFAULT_RIFERIMENTO_NUMERO_LINEA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNumeroLinea() throws Exception {
        // Get the numeroLinea
        restNumeroLineaMockMvc.perform(get("/api/numero-lineas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNumeroLinea() throws Exception {
        // Initialize the database
        numeroLineaRepository.saveAndFlush(numeroLinea);

        int databaseSizeBeforeUpdate = numeroLineaRepository.findAll().size();

        // Update the numeroLinea
        NumeroLinea updatedNumeroLinea = numeroLineaRepository.findById(numeroLinea.getId()).get();
        // Disconnect from session so that the updates on updatedNumeroLinea are not directly saved in db
        em.detach(updatedNumeroLinea);
        updatedNumeroLinea
            .riferimentoNumeroLinea(UPDATED_RIFERIMENTO_NUMERO_LINEA);

        restNumeroLineaMockMvc.perform(put("/api/numero-lineas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNumeroLinea)))
            .andExpect(status().isOk());

        // Validate the NumeroLinea in the database
        List<NumeroLinea> numeroLineaList = numeroLineaRepository.findAll();
        assertThat(numeroLineaList).hasSize(databaseSizeBeforeUpdate);
        NumeroLinea testNumeroLinea = numeroLineaList.get(numeroLineaList.size() - 1);
        assertThat(testNumeroLinea.getRiferimentoNumeroLinea()).isEqualTo(UPDATED_RIFERIMENTO_NUMERO_LINEA);
    }

    @Test
    @Transactional
    public void updateNonExistingNumeroLinea() throws Exception {
        int databaseSizeBeforeUpdate = numeroLineaRepository.findAll().size();

        // Create the NumeroLinea

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNumeroLineaMockMvc.perform(put("/api/numero-lineas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeroLinea)))
            .andExpect(status().isBadRequest());

        // Validate the NumeroLinea in the database
        List<NumeroLinea> numeroLineaList = numeroLineaRepository.findAll();
        assertThat(numeroLineaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNumeroLinea() throws Exception {
        // Initialize the database
        numeroLineaRepository.saveAndFlush(numeroLinea);

        int databaseSizeBeforeDelete = numeroLineaRepository.findAll().size();

        // Delete the numeroLinea
        restNumeroLineaMockMvc.perform(delete("/api/numero-lineas/{id}", numeroLinea.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<NumeroLinea> numeroLineaList = numeroLineaRepository.findAll();
        assertThat(numeroLineaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NumeroLinea.class);
        NumeroLinea numeroLinea1 = new NumeroLinea();
        numeroLinea1.setId(1L);
        NumeroLinea numeroLinea2 = new NumeroLinea();
        numeroLinea2.setId(numeroLinea1.getId());
        assertThat(numeroLinea1).isEqualTo(numeroLinea2);
        numeroLinea2.setId(2L);
        assertThat(numeroLinea1).isNotEqualTo(numeroLinea2);
        numeroLinea1.setId(null);
        assertThat(numeroLinea1).isNotEqualTo(numeroLinea2);
    }
}
