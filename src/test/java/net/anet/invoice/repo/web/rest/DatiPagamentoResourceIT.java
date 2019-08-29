package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.DatiPagamento;
import net.anet.invoice.repo.repository.DatiPagamentoRepository;
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
 * Integration tests for the {@Link DatiPagamentoResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class DatiPagamentoResourceIT {

    private static final String DEFAULT_CONDIZIONI_PAGAMENTO = "AAAA";
    private static final String UPDATED_CONDIZIONI_PAGAMENTO = "BBBB";

    @Autowired
    private DatiPagamentoRepository datiPagamentoRepository;

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

    private MockMvc restDatiPagamentoMockMvc;

    private DatiPagamento datiPagamento;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DatiPagamentoResource datiPagamentoResource = new DatiPagamentoResource(datiPagamentoRepository);
        this.restDatiPagamentoMockMvc = MockMvcBuilders.standaloneSetup(datiPagamentoResource)
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
    public static DatiPagamento createEntity(EntityManager em) {
        DatiPagamento datiPagamento = new DatiPagamento()
            .condizioniPagamento(DEFAULT_CONDIZIONI_PAGAMENTO);
        return datiPagamento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DatiPagamento createUpdatedEntity(EntityManager em) {
        DatiPagamento datiPagamento = new DatiPagamento()
            .condizioniPagamento(UPDATED_CONDIZIONI_PAGAMENTO);
        return datiPagamento;
    }

    @BeforeEach
    public void initTest() {
        datiPagamento = createEntity(em);
    }

    @Test
    @Transactional
    public void createDatiPagamento() throws Exception {
        int databaseSizeBeforeCreate = datiPagamentoRepository.findAll().size();

        // Create the DatiPagamento
        restDatiPagamentoMockMvc.perform(post("/api/dati-pagamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(datiPagamento)))
            .andExpect(status().isCreated());

        // Validate the DatiPagamento in the database
        List<DatiPagamento> datiPagamentoList = datiPagamentoRepository.findAll();
        assertThat(datiPagamentoList).hasSize(databaseSizeBeforeCreate + 1);
        DatiPagamento testDatiPagamento = datiPagamentoList.get(datiPagamentoList.size() - 1);
        assertThat(testDatiPagamento.getCondizioniPagamento()).isEqualTo(DEFAULT_CONDIZIONI_PAGAMENTO);
    }

    @Test
    @Transactional
    public void createDatiPagamentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = datiPagamentoRepository.findAll().size();

        // Create the DatiPagamento with an existing ID
        datiPagamento.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDatiPagamentoMockMvc.perform(post("/api/dati-pagamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(datiPagamento)))
            .andExpect(status().isBadRequest());

        // Validate the DatiPagamento in the database
        List<DatiPagamento> datiPagamentoList = datiPagamentoRepository.findAll();
        assertThat(datiPagamentoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDatiPagamentos() throws Exception {
        // Initialize the database
        datiPagamentoRepository.saveAndFlush(datiPagamento);

        // Get all the datiPagamentoList
        restDatiPagamentoMockMvc.perform(get("/api/dati-pagamentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(datiPagamento.getId().intValue())))
            .andExpect(jsonPath("$.[*].condizioniPagamento").value(hasItem(DEFAULT_CONDIZIONI_PAGAMENTO.toString())));
    }
    
    @Test
    @Transactional
    public void getDatiPagamento() throws Exception {
        // Initialize the database
        datiPagamentoRepository.saveAndFlush(datiPagamento);

        // Get the datiPagamento
        restDatiPagamentoMockMvc.perform(get("/api/dati-pagamentos/{id}", datiPagamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(datiPagamento.getId().intValue()))
            .andExpect(jsonPath("$.condizioniPagamento").value(DEFAULT_CONDIZIONI_PAGAMENTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDatiPagamento() throws Exception {
        // Get the datiPagamento
        restDatiPagamentoMockMvc.perform(get("/api/dati-pagamentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDatiPagamento() throws Exception {
        // Initialize the database
        datiPagamentoRepository.saveAndFlush(datiPagamento);

        int databaseSizeBeforeUpdate = datiPagamentoRepository.findAll().size();

        // Update the datiPagamento
        DatiPagamento updatedDatiPagamento = datiPagamentoRepository.findById(datiPagamento.getId()).get();
        // Disconnect from session so that the updates on updatedDatiPagamento are not directly saved in db
        em.detach(updatedDatiPagamento);
        updatedDatiPagamento
            .condizioniPagamento(UPDATED_CONDIZIONI_PAGAMENTO);

        restDatiPagamentoMockMvc.perform(put("/api/dati-pagamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDatiPagamento)))
            .andExpect(status().isOk());

        // Validate the DatiPagamento in the database
        List<DatiPagamento> datiPagamentoList = datiPagamentoRepository.findAll();
        assertThat(datiPagamentoList).hasSize(databaseSizeBeforeUpdate);
        DatiPagamento testDatiPagamento = datiPagamentoList.get(datiPagamentoList.size() - 1);
        assertThat(testDatiPagamento.getCondizioniPagamento()).isEqualTo(UPDATED_CONDIZIONI_PAGAMENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingDatiPagamento() throws Exception {
        int databaseSizeBeforeUpdate = datiPagamentoRepository.findAll().size();

        // Create the DatiPagamento

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDatiPagamentoMockMvc.perform(put("/api/dati-pagamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(datiPagamento)))
            .andExpect(status().isBadRequest());

        // Validate the DatiPagamento in the database
        List<DatiPagamento> datiPagamentoList = datiPagamentoRepository.findAll();
        assertThat(datiPagamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDatiPagamento() throws Exception {
        // Initialize the database
        datiPagamentoRepository.saveAndFlush(datiPagamento);

        int databaseSizeBeforeDelete = datiPagamentoRepository.findAll().size();

        // Delete the datiPagamento
        restDatiPagamentoMockMvc.perform(delete("/api/dati-pagamentos/{id}", datiPagamento.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<DatiPagamento> datiPagamentoList = datiPagamentoRepository.findAll();
        assertThat(datiPagamentoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DatiPagamento.class);
        DatiPagamento datiPagamento1 = new DatiPagamento();
        datiPagamento1.setId(1L);
        DatiPagamento datiPagamento2 = new DatiPagamento();
        datiPagamento2.setId(datiPagamento1.getId());
        assertThat(datiPagamento1).isEqualTo(datiPagamento2);
        datiPagamento2.setId(2L);
        assertThat(datiPagamento1).isNotEqualTo(datiPagamento2);
        datiPagamento1.setId(null);
        assertThat(datiPagamento1).isNotEqualTo(datiPagamento2);
    }
}
