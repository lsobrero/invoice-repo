package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.DettaglioPagamento;
import net.anet.invoice.repo.repository.DettaglioPagamentoRepository;
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
 * Integration tests for the {@Link DettaglioPagamentoResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class DettaglioPagamentoResourceIT {

    private static final String DEFAULT_BENEFICIARIO = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARIO = "BBBBBBBBBB";

    private static final String DEFAULT_MODALITA_PAGAMENTO = "AAAA";
    private static final String UPDATED_MODALITA_PAGAMENTO = "BBBB";

    private static final String DEFAULT_DATA_RIFERIMENTO_TERMINI_PAGAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_DATA_RIFERIMENTO_TERMINI_PAGAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_GIORNI_TERMINI_PAGAMENTO = "AAAAA";
    private static final String UPDATED_GIORNI_TERMINI_PAGAMENTO = "BBBBB";

    private static final String DEFAULT_DATA_SCADENZA_PAGAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_DATA_SCADENZA_PAGAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_IMPORTO_PAGAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_IMPORTO_PAGAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_COD_UFFICIO_POSTALE = "AAAAAAAAAA";
    private static final String UPDATED_COD_UFFICIO_POSTALE = "BBBBBBBBBB";

    private static final String DEFAULT_COGNOME_QUIETANZANTE = "AAAAAAAAAA";
    private static final String UPDATED_COGNOME_QUIETANZANTE = "BBBBBBBBBB";

    private static final String DEFAULT_NOME_QUIETANZANTE = "AAAAAAAAAA";
    private static final String UPDATED_NOME_QUIETANZANTE = "BBBBBBBBBB";

    private static final String DEFAULT_C_F_QUIETANZANTE = "AAAAAAAAAA";
    private static final String UPDATED_C_F_QUIETANZANTE = "BBBBBBBBBB";

    private static final String DEFAULT_TITOLO_QUIETANZANTE = "AAAAAAAAAA";
    private static final String UPDATED_TITOLO_QUIETANZANTE = "BBBBBBBBBB";

    private static final String DEFAULT_ISTITUTO_FINANZIARIO = "AAAAAAAAAA";
    private static final String UPDATED_ISTITUTO_FINANZIARIO = "BBBBBBBBBB";

    private static final String DEFAULT_I_BAN = "AAAAAAAAAA";
    private static final String UPDATED_I_BAN = "BBBBBBBBBB";

    private static final String DEFAULT_A_BI = "AAAAA";
    private static final String UPDATED_A_BI = "BBBBB";

    private static final String DEFAULT_C_AB = "AAAAA";
    private static final String UPDATED_C_AB = "BBBBB";

    private static final String DEFAULT_B_IC = "AAAAAAAAAA";
    private static final String UPDATED_B_IC = "BBBBBBBBBB";

    private static final String DEFAULT_SCONTO_PAGAMENTO_ANTICIPATO = "AAAAAAAAAA";
    private static final String UPDATED_SCONTO_PAGAMENTO_ANTICIPATO = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_LIMITE_PAGAMENTO_ANTICIPATO = "AAAAAAAAAA";
    private static final String UPDATED_DATA_LIMITE_PAGAMENTO_ANTICIPATO = "BBBBBBBBBB";

    private static final String DEFAULT_PENALITA_PAGAMENTI_RITARDATI = "AAAAAAAAAA";
    private static final String UPDATED_PENALITA_PAGAMENTI_RITARDATI = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_DECORRENZA_PENALE = "AAAAAAAAAA";
    private static final String UPDATED_DATA_DECORRENZA_PENALE = "BBBBBBBBBB";

    private static final String DEFAULT_CODICE_PAGAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_PAGAMENTO = "BBBBBBBBBB";

    @Autowired
    private DettaglioPagamentoRepository dettaglioPagamentoRepository;

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

    private MockMvc restDettaglioPagamentoMockMvc;

    private DettaglioPagamento dettaglioPagamento;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DettaglioPagamentoResource dettaglioPagamentoResource = new DettaglioPagamentoResource(dettaglioPagamentoRepository);
        this.restDettaglioPagamentoMockMvc = MockMvcBuilders.standaloneSetup(dettaglioPagamentoResource)
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
    public static DettaglioPagamento createEntity(EntityManager em) {
        DettaglioPagamento dettaglioPagamento = new DettaglioPagamento()
            .beneficiario(DEFAULT_BENEFICIARIO)
            .modalitaPagamento(DEFAULT_MODALITA_PAGAMENTO)
            .dataRiferimentoTerminiPagamento(DEFAULT_DATA_RIFERIMENTO_TERMINI_PAGAMENTO)
            .giorniTerminiPagamento(DEFAULT_GIORNI_TERMINI_PAGAMENTO)
            .dataScadenzaPagamento(DEFAULT_DATA_SCADENZA_PAGAMENTO)
            .importoPagamento(DEFAULT_IMPORTO_PAGAMENTO)
            .codUfficioPostale(DEFAULT_COD_UFFICIO_POSTALE)
            .cognomeQuietanzante(DEFAULT_COGNOME_QUIETANZANTE)
            .nomeQuietanzante(DEFAULT_NOME_QUIETANZANTE)
            .cFQuietanzante(DEFAULT_C_F_QUIETANZANTE)
            .titoloQuietanzante(DEFAULT_TITOLO_QUIETANZANTE)
            .istitutoFinanziario(DEFAULT_ISTITUTO_FINANZIARIO)
            .iBAN(DEFAULT_I_BAN)
            .aBI(DEFAULT_A_BI)
            .cAB(DEFAULT_C_AB)
            .bIC(DEFAULT_B_IC)
            .scontoPagamentoAnticipato(DEFAULT_SCONTO_PAGAMENTO_ANTICIPATO)
            .dataLimitePagamentoAnticipato(DEFAULT_DATA_LIMITE_PAGAMENTO_ANTICIPATO)
            .penalitaPagamentiRitardati(DEFAULT_PENALITA_PAGAMENTI_RITARDATI)
            .dataDecorrenzaPenale(DEFAULT_DATA_DECORRENZA_PENALE)
            .codicePagamento(DEFAULT_CODICE_PAGAMENTO);
        return dettaglioPagamento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DettaglioPagamento createUpdatedEntity(EntityManager em) {
        DettaglioPagamento dettaglioPagamento = new DettaglioPagamento()
            .beneficiario(UPDATED_BENEFICIARIO)
            .modalitaPagamento(UPDATED_MODALITA_PAGAMENTO)
            .dataRiferimentoTerminiPagamento(UPDATED_DATA_RIFERIMENTO_TERMINI_PAGAMENTO)
            .giorniTerminiPagamento(UPDATED_GIORNI_TERMINI_PAGAMENTO)
            .dataScadenzaPagamento(UPDATED_DATA_SCADENZA_PAGAMENTO)
            .importoPagamento(UPDATED_IMPORTO_PAGAMENTO)
            .codUfficioPostale(UPDATED_COD_UFFICIO_POSTALE)
            .cognomeQuietanzante(UPDATED_COGNOME_QUIETANZANTE)
            .nomeQuietanzante(UPDATED_NOME_QUIETANZANTE)
            .cFQuietanzante(UPDATED_C_F_QUIETANZANTE)
            .titoloQuietanzante(UPDATED_TITOLO_QUIETANZANTE)
            .istitutoFinanziario(UPDATED_ISTITUTO_FINANZIARIO)
            .iBAN(UPDATED_I_BAN)
            .aBI(UPDATED_A_BI)
            .cAB(UPDATED_C_AB)
            .bIC(UPDATED_B_IC)
            .scontoPagamentoAnticipato(UPDATED_SCONTO_PAGAMENTO_ANTICIPATO)
            .dataLimitePagamentoAnticipato(UPDATED_DATA_LIMITE_PAGAMENTO_ANTICIPATO)
            .penalitaPagamentiRitardati(UPDATED_PENALITA_PAGAMENTI_RITARDATI)
            .dataDecorrenzaPenale(UPDATED_DATA_DECORRENZA_PENALE)
            .codicePagamento(UPDATED_CODICE_PAGAMENTO);
        return dettaglioPagamento;
    }

    @BeforeEach
    public void initTest() {
        dettaglioPagamento = createEntity(em);
    }

    @Test
    @Transactional
    public void createDettaglioPagamento() throws Exception {
        int databaseSizeBeforeCreate = dettaglioPagamentoRepository.findAll().size();

        // Create the DettaglioPagamento
        restDettaglioPagamentoMockMvc.perform(post("/api/dettaglio-pagamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dettaglioPagamento)))
            .andExpect(status().isCreated());

        // Validate the DettaglioPagamento in the database
        List<DettaglioPagamento> dettaglioPagamentoList = dettaglioPagamentoRepository.findAll();
        assertThat(dettaglioPagamentoList).hasSize(databaseSizeBeforeCreate + 1);
        DettaglioPagamento testDettaglioPagamento = dettaglioPagamentoList.get(dettaglioPagamentoList.size() - 1);
        assertThat(testDettaglioPagamento.getBeneficiario()).isEqualTo(DEFAULT_BENEFICIARIO);
        assertThat(testDettaglioPagamento.getModalitaPagamento()).isEqualTo(DEFAULT_MODALITA_PAGAMENTO);
        assertThat(testDettaglioPagamento.getDataRiferimentoTerminiPagamento()).isEqualTo(DEFAULT_DATA_RIFERIMENTO_TERMINI_PAGAMENTO);
        assertThat(testDettaglioPagamento.getGiorniTerminiPagamento()).isEqualTo(DEFAULT_GIORNI_TERMINI_PAGAMENTO);
        assertThat(testDettaglioPagamento.getDataScadenzaPagamento()).isEqualTo(DEFAULT_DATA_SCADENZA_PAGAMENTO);
        assertThat(testDettaglioPagamento.getImportoPagamento()).isEqualTo(DEFAULT_IMPORTO_PAGAMENTO);
        assertThat(testDettaglioPagamento.getCodUfficioPostale()).isEqualTo(DEFAULT_COD_UFFICIO_POSTALE);
        assertThat(testDettaglioPagamento.getCognomeQuietanzante()).isEqualTo(DEFAULT_COGNOME_QUIETANZANTE);
        assertThat(testDettaglioPagamento.getNomeQuietanzante()).isEqualTo(DEFAULT_NOME_QUIETANZANTE);
        assertThat(testDettaglioPagamento.getcFQuietanzante()).isEqualTo(DEFAULT_C_F_QUIETANZANTE);
        assertThat(testDettaglioPagamento.getTitoloQuietanzante()).isEqualTo(DEFAULT_TITOLO_QUIETANZANTE);
        assertThat(testDettaglioPagamento.getIstitutoFinanziario()).isEqualTo(DEFAULT_ISTITUTO_FINANZIARIO);
        assertThat(testDettaglioPagamento.getiBAN()).isEqualTo(DEFAULT_I_BAN);
        assertThat(testDettaglioPagamento.getaBI()).isEqualTo(DEFAULT_A_BI);
        assertThat(testDettaglioPagamento.getcAB()).isEqualTo(DEFAULT_C_AB);
        assertThat(testDettaglioPagamento.getbIC()).isEqualTo(DEFAULT_B_IC);
        assertThat(testDettaglioPagamento.getScontoPagamentoAnticipato()).isEqualTo(DEFAULT_SCONTO_PAGAMENTO_ANTICIPATO);
        assertThat(testDettaglioPagamento.getDataLimitePagamentoAnticipato()).isEqualTo(DEFAULT_DATA_LIMITE_PAGAMENTO_ANTICIPATO);
        assertThat(testDettaglioPagamento.getPenalitaPagamentiRitardati()).isEqualTo(DEFAULT_PENALITA_PAGAMENTI_RITARDATI);
        assertThat(testDettaglioPagamento.getDataDecorrenzaPenale()).isEqualTo(DEFAULT_DATA_DECORRENZA_PENALE);
        assertThat(testDettaglioPagamento.getCodicePagamento()).isEqualTo(DEFAULT_CODICE_PAGAMENTO);
    }

    @Test
    @Transactional
    public void createDettaglioPagamentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dettaglioPagamentoRepository.findAll().size();

        // Create the DettaglioPagamento with an existing ID
        dettaglioPagamento.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDettaglioPagamentoMockMvc.perform(post("/api/dettaglio-pagamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dettaglioPagamento)))
            .andExpect(status().isBadRequest());

        // Validate the DettaglioPagamento in the database
        List<DettaglioPagamento> dettaglioPagamentoList = dettaglioPagamentoRepository.findAll();
        assertThat(dettaglioPagamentoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDettaglioPagamentos() throws Exception {
        // Initialize the database
        dettaglioPagamentoRepository.saveAndFlush(dettaglioPagamento);

        // Get all the dettaglioPagamentoList
        restDettaglioPagamentoMockMvc.perform(get("/api/dettaglio-pagamentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dettaglioPagamento.getId().intValue())))
            .andExpect(jsonPath("$.[*].beneficiario").value(hasItem(DEFAULT_BENEFICIARIO.toString())))
            .andExpect(jsonPath("$.[*].modalitaPagamento").value(hasItem(DEFAULT_MODALITA_PAGAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dataRiferimentoTerminiPagamento").value(hasItem(DEFAULT_DATA_RIFERIMENTO_TERMINI_PAGAMENTO.toString())))
            .andExpect(jsonPath("$.[*].giorniTerminiPagamento").value(hasItem(DEFAULT_GIORNI_TERMINI_PAGAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dataScadenzaPagamento").value(hasItem(DEFAULT_DATA_SCADENZA_PAGAMENTO.toString())))
            .andExpect(jsonPath("$.[*].importoPagamento").value(hasItem(DEFAULT_IMPORTO_PAGAMENTO.toString())))
            .andExpect(jsonPath("$.[*].codUfficioPostale").value(hasItem(DEFAULT_COD_UFFICIO_POSTALE.toString())))
            .andExpect(jsonPath("$.[*].cognomeQuietanzante").value(hasItem(DEFAULT_COGNOME_QUIETANZANTE.toString())))
            .andExpect(jsonPath("$.[*].nomeQuietanzante").value(hasItem(DEFAULT_NOME_QUIETANZANTE.toString())))
            .andExpect(jsonPath("$.[*].cFQuietanzante").value(hasItem(DEFAULT_C_F_QUIETANZANTE.toString())))
            .andExpect(jsonPath("$.[*].titoloQuietanzante").value(hasItem(DEFAULT_TITOLO_QUIETANZANTE.toString())))
            .andExpect(jsonPath("$.[*].istitutoFinanziario").value(hasItem(DEFAULT_ISTITUTO_FINANZIARIO.toString())))
            .andExpect(jsonPath("$.[*].iBAN").value(hasItem(DEFAULT_I_BAN.toString())))
            .andExpect(jsonPath("$.[*].aBI").value(hasItem(DEFAULT_A_BI.toString())))
            .andExpect(jsonPath("$.[*].cAB").value(hasItem(DEFAULT_C_AB.toString())))
            .andExpect(jsonPath("$.[*].bIC").value(hasItem(DEFAULT_B_IC.toString())))
            .andExpect(jsonPath("$.[*].scontoPagamentoAnticipato").value(hasItem(DEFAULT_SCONTO_PAGAMENTO_ANTICIPATO.toString())))
            .andExpect(jsonPath("$.[*].dataLimitePagamentoAnticipato").value(hasItem(DEFAULT_DATA_LIMITE_PAGAMENTO_ANTICIPATO.toString())))
            .andExpect(jsonPath("$.[*].penalitaPagamentiRitardati").value(hasItem(DEFAULT_PENALITA_PAGAMENTI_RITARDATI.toString())))
            .andExpect(jsonPath("$.[*].dataDecorrenzaPenale").value(hasItem(DEFAULT_DATA_DECORRENZA_PENALE.toString())))
            .andExpect(jsonPath("$.[*].codicePagamento").value(hasItem(DEFAULT_CODICE_PAGAMENTO.toString())));
    }
    
    @Test
    @Transactional
    public void getDettaglioPagamento() throws Exception {
        // Initialize the database
        dettaglioPagamentoRepository.saveAndFlush(dettaglioPagamento);

        // Get the dettaglioPagamento
        restDettaglioPagamentoMockMvc.perform(get("/api/dettaglio-pagamentos/{id}", dettaglioPagamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dettaglioPagamento.getId().intValue()))
            .andExpect(jsonPath("$.beneficiario").value(DEFAULT_BENEFICIARIO.toString()))
            .andExpect(jsonPath("$.modalitaPagamento").value(DEFAULT_MODALITA_PAGAMENTO.toString()))
            .andExpect(jsonPath("$.dataRiferimentoTerminiPagamento").value(DEFAULT_DATA_RIFERIMENTO_TERMINI_PAGAMENTO.toString()))
            .andExpect(jsonPath("$.giorniTerminiPagamento").value(DEFAULT_GIORNI_TERMINI_PAGAMENTO.toString()))
            .andExpect(jsonPath("$.dataScadenzaPagamento").value(DEFAULT_DATA_SCADENZA_PAGAMENTO.toString()))
            .andExpect(jsonPath("$.importoPagamento").value(DEFAULT_IMPORTO_PAGAMENTO.toString()))
            .andExpect(jsonPath("$.codUfficioPostale").value(DEFAULT_COD_UFFICIO_POSTALE.toString()))
            .andExpect(jsonPath("$.cognomeQuietanzante").value(DEFAULT_COGNOME_QUIETANZANTE.toString()))
            .andExpect(jsonPath("$.nomeQuietanzante").value(DEFAULT_NOME_QUIETANZANTE.toString()))
            .andExpect(jsonPath("$.cFQuietanzante").value(DEFAULT_C_F_QUIETANZANTE.toString()))
            .andExpect(jsonPath("$.titoloQuietanzante").value(DEFAULT_TITOLO_QUIETANZANTE.toString()))
            .andExpect(jsonPath("$.istitutoFinanziario").value(DEFAULT_ISTITUTO_FINANZIARIO.toString()))
            .andExpect(jsonPath("$.iBAN").value(DEFAULT_I_BAN.toString()))
            .andExpect(jsonPath("$.aBI").value(DEFAULT_A_BI.toString()))
            .andExpect(jsonPath("$.cAB").value(DEFAULT_C_AB.toString()))
            .andExpect(jsonPath("$.bIC").value(DEFAULT_B_IC.toString()))
            .andExpect(jsonPath("$.scontoPagamentoAnticipato").value(DEFAULT_SCONTO_PAGAMENTO_ANTICIPATO.toString()))
            .andExpect(jsonPath("$.dataLimitePagamentoAnticipato").value(DEFAULT_DATA_LIMITE_PAGAMENTO_ANTICIPATO.toString()))
            .andExpect(jsonPath("$.penalitaPagamentiRitardati").value(DEFAULT_PENALITA_PAGAMENTI_RITARDATI.toString()))
            .andExpect(jsonPath("$.dataDecorrenzaPenale").value(DEFAULT_DATA_DECORRENZA_PENALE.toString()))
            .andExpect(jsonPath("$.codicePagamento").value(DEFAULT_CODICE_PAGAMENTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDettaglioPagamento() throws Exception {
        // Get the dettaglioPagamento
        restDettaglioPagamentoMockMvc.perform(get("/api/dettaglio-pagamentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDettaglioPagamento() throws Exception {
        // Initialize the database
        dettaglioPagamentoRepository.saveAndFlush(dettaglioPagamento);

        int databaseSizeBeforeUpdate = dettaglioPagamentoRepository.findAll().size();

        // Update the dettaglioPagamento
        DettaglioPagamento updatedDettaglioPagamento = dettaglioPagamentoRepository.findById(dettaglioPagamento.getId()).get();
        // Disconnect from session so that the updates on updatedDettaglioPagamento are not directly saved in db
        em.detach(updatedDettaglioPagamento);
        updatedDettaglioPagamento
            .beneficiario(UPDATED_BENEFICIARIO)
            .modalitaPagamento(UPDATED_MODALITA_PAGAMENTO)
            .dataRiferimentoTerminiPagamento(UPDATED_DATA_RIFERIMENTO_TERMINI_PAGAMENTO)
            .giorniTerminiPagamento(UPDATED_GIORNI_TERMINI_PAGAMENTO)
            .dataScadenzaPagamento(UPDATED_DATA_SCADENZA_PAGAMENTO)
            .importoPagamento(UPDATED_IMPORTO_PAGAMENTO)
            .codUfficioPostale(UPDATED_COD_UFFICIO_POSTALE)
            .cognomeQuietanzante(UPDATED_COGNOME_QUIETANZANTE)
            .nomeQuietanzante(UPDATED_NOME_QUIETANZANTE)
            .cFQuietanzante(UPDATED_C_F_QUIETANZANTE)
            .titoloQuietanzante(UPDATED_TITOLO_QUIETANZANTE)
            .istitutoFinanziario(UPDATED_ISTITUTO_FINANZIARIO)
            .iBAN(UPDATED_I_BAN)
            .aBI(UPDATED_A_BI)
            .cAB(UPDATED_C_AB)
            .bIC(UPDATED_B_IC)
            .scontoPagamentoAnticipato(UPDATED_SCONTO_PAGAMENTO_ANTICIPATO)
            .dataLimitePagamentoAnticipato(UPDATED_DATA_LIMITE_PAGAMENTO_ANTICIPATO)
            .penalitaPagamentiRitardati(UPDATED_PENALITA_PAGAMENTI_RITARDATI)
            .dataDecorrenzaPenale(UPDATED_DATA_DECORRENZA_PENALE)
            .codicePagamento(UPDATED_CODICE_PAGAMENTO);

        restDettaglioPagamentoMockMvc.perform(put("/api/dettaglio-pagamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDettaglioPagamento)))
            .andExpect(status().isOk());

        // Validate the DettaglioPagamento in the database
        List<DettaglioPagamento> dettaglioPagamentoList = dettaglioPagamentoRepository.findAll();
        assertThat(dettaglioPagamentoList).hasSize(databaseSizeBeforeUpdate);
        DettaglioPagamento testDettaglioPagamento = dettaglioPagamentoList.get(dettaglioPagamentoList.size() - 1);
        assertThat(testDettaglioPagamento.getBeneficiario()).isEqualTo(UPDATED_BENEFICIARIO);
        assertThat(testDettaglioPagamento.getModalitaPagamento()).isEqualTo(UPDATED_MODALITA_PAGAMENTO);
        assertThat(testDettaglioPagamento.getDataRiferimentoTerminiPagamento()).isEqualTo(UPDATED_DATA_RIFERIMENTO_TERMINI_PAGAMENTO);
        assertThat(testDettaglioPagamento.getGiorniTerminiPagamento()).isEqualTo(UPDATED_GIORNI_TERMINI_PAGAMENTO);
        assertThat(testDettaglioPagamento.getDataScadenzaPagamento()).isEqualTo(UPDATED_DATA_SCADENZA_PAGAMENTO);
        assertThat(testDettaglioPagamento.getImportoPagamento()).isEqualTo(UPDATED_IMPORTO_PAGAMENTO);
        assertThat(testDettaglioPagamento.getCodUfficioPostale()).isEqualTo(UPDATED_COD_UFFICIO_POSTALE);
        assertThat(testDettaglioPagamento.getCognomeQuietanzante()).isEqualTo(UPDATED_COGNOME_QUIETANZANTE);
        assertThat(testDettaglioPagamento.getNomeQuietanzante()).isEqualTo(UPDATED_NOME_QUIETANZANTE);
        assertThat(testDettaglioPagamento.getcFQuietanzante()).isEqualTo(UPDATED_C_F_QUIETANZANTE);
        assertThat(testDettaglioPagamento.getTitoloQuietanzante()).isEqualTo(UPDATED_TITOLO_QUIETANZANTE);
        assertThat(testDettaglioPagamento.getIstitutoFinanziario()).isEqualTo(UPDATED_ISTITUTO_FINANZIARIO);
        assertThat(testDettaglioPagamento.getiBAN()).isEqualTo(UPDATED_I_BAN);
        assertThat(testDettaglioPagamento.getaBI()).isEqualTo(UPDATED_A_BI);
        assertThat(testDettaglioPagamento.getcAB()).isEqualTo(UPDATED_C_AB);
        assertThat(testDettaglioPagamento.getbIC()).isEqualTo(UPDATED_B_IC);
        assertThat(testDettaglioPagamento.getScontoPagamentoAnticipato()).isEqualTo(UPDATED_SCONTO_PAGAMENTO_ANTICIPATO);
        assertThat(testDettaglioPagamento.getDataLimitePagamentoAnticipato()).isEqualTo(UPDATED_DATA_LIMITE_PAGAMENTO_ANTICIPATO);
        assertThat(testDettaglioPagamento.getPenalitaPagamentiRitardati()).isEqualTo(UPDATED_PENALITA_PAGAMENTI_RITARDATI);
        assertThat(testDettaglioPagamento.getDataDecorrenzaPenale()).isEqualTo(UPDATED_DATA_DECORRENZA_PENALE);
        assertThat(testDettaglioPagamento.getCodicePagamento()).isEqualTo(UPDATED_CODICE_PAGAMENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingDettaglioPagamento() throws Exception {
        int databaseSizeBeforeUpdate = dettaglioPagamentoRepository.findAll().size();

        // Create the DettaglioPagamento

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDettaglioPagamentoMockMvc.perform(put("/api/dettaglio-pagamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dettaglioPagamento)))
            .andExpect(status().isBadRequest());

        // Validate the DettaglioPagamento in the database
        List<DettaglioPagamento> dettaglioPagamentoList = dettaglioPagamentoRepository.findAll();
        assertThat(dettaglioPagamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDettaglioPagamento() throws Exception {
        // Initialize the database
        dettaglioPagamentoRepository.saveAndFlush(dettaglioPagamento);

        int databaseSizeBeforeDelete = dettaglioPagamentoRepository.findAll().size();

        // Delete the dettaglioPagamento
        restDettaglioPagamentoMockMvc.perform(delete("/api/dettaglio-pagamentos/{id}", dettaglioPagamento.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<DettaglioPagamento> dettaglioPagamentoList = dettaglioPagamentoRepository.findAll();
        assertThat(dettaglioPagamentoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DettaglioPagamento.class);
        DettaglioPagamento dettaglioPagamento1 = new DettaglioPagamento();
        dettaglioPagamento1.setId(1L);
        DettaglioPagamento dettaglioPagamento2 = new DettaglioPagamento();
        dettaglioPagamento2.setId(dettaglioPagamento1.getId());
        assertThat(dettaglioPagamento1).isEqualTo(dettaglioPagamento2);
        dettaglioPagamento2.setId(2L);
        assertThat(dettaglioPagamento1).isNotEqualTo(dettaglioPagamento2);
        dettaglioPagamento1.setId(null);
        assertThat(dettaglioPagamento1).isNotEqualTo(dettaglioPagamento2);
    }
}
