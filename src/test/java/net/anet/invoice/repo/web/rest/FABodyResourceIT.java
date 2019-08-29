package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.FABody;
import net.anet.invoice.repo.repository.FABodyRepository;
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
 * Integration tests for the {@Link FABodyResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class FABodyResourceIT {

    private static final String DEFAULT_TIPO_DOCUMENTO = "AAAA";
    private static final String UPDATED_TIPO_DOCUMENTO = "BBBB";

    private static final String DEFAULT_DIVISA = "AAA";
    private static final String UPDATED_DIVISA = "BBB";

    private static final String DEFAULT_DATA = "AAAAAAAAAA";
    private static final String UPDATED_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_RITENUTA = "AAAA";
    private static final String UPDATED_TIPO_RITENUTA = "BBBB";

    private static final String DEFAULT_IMPORTO_RITENUTA = "AAAAAAAAAA";
    private static final String UPDATED_IMPORTO_RITENUTA = "BBBBBBBBBB";

    private static final String DEFAULT_ALIQUOTA_RITENUTA = "AAAAAA";
    private static final String UPDATED_ALIQUOTA_RITENUTA = "BBBBBB";

    private static final String DEFAULT_CAUSALE_PAGAMENTO = "AA";
    private static final String UPDATED_CAUSALE_PAGAMENTO = "BB";

    private static final String DEFAULT_BOLLO_VIRTUALE = "AA";
    private static final String UPDATED_BOLLO_VIRTUALE = "BB";

    private static final String DEFAULT_IMPORTO_BOLLO = "AAAAAAAAAA";
    private static final String UPDATED_IMPORTO_BOLLO = "BBBBBBBBBB";

    private static final String DEFAULT_IMPORTO_TOTALE_DOCUMENTO = "AAAAAAAAAA";
    private static final String UPDATED_IMPORTO_TOTALE_DOCUMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_ARROTONDAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_ARROTONDAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_ART_73 = "AA";
    private static final String UPDATED_ART_73 = "BB";

    private static final String DEFAULT_ID_PAESE = "AA";
    private static final String UPDATED_ID_PAESE = "BB";

    private static final String DEFAULT_ID_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_ID_CODICE = "BBBBBBBBBB";

    private static final String DEFAULT_CODICE_FISCALE = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_FISCALE = "BBBBBBBBBB";

    private static final String DEFAULT_DENOMINAZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DENOMINAZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_COGNOME = "AAAAAAAAAA";
    private static final String UPDATED_COGNOME = "BBBBBBBBBB";

    private static final String DEFAULT_TITOLO = "AAAAAAAAAA";
    private static final String UPDATED_TITOLO = "BBBBBBBBBB";

    private static final String DEFAULT_COD_EORI = "AAAAAAAAAA";
    private static final String UPDATED_COD_EORI = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_LICENZA_GUIDA = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_LICENZA_GUIDA = "BBBBBBBBBB";

    private static final String DEFAULT_MEZZO_TRASPORTO = "AAAAAAAAAA";
    private static final String UPDATED_MEZZO_TRASPORTO = "BBBBBBBBBB";

    private static final String DEFAULT_CAUSALE_TRASPORTO = "AAAAAAAAAA";
    private static final String UPDATED_CAUSALE_TRASPORTO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_COLLI = "AAAA";
    private static final String UPDATED_NUMERO_COLLI = "BBBB";

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_UNITA_MISURA_PESO = "AAAAAAAAAA";
    private static final String UPDATED_UNITA_MISURA_PESO = "BBBBBBBBBB";

    private static final String DEFAULT_PESO_LORDO = "AAAAAAA";
    private static final String UPDATED_PESO_LORDO = "BBBBBBB";

    private static final String DEFAULT_PESO_NETTO = "AAAAAAA";
    private static final String UPDATED_PESO_NETTO = "BBBBBBB";

    private static final String DEFAULT_DATA_ORA_RITIRO = "AAAAAAAAAA";
    private static final String UPDATED_DATA_ORA_RITIRO = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_INIZIO_TRASPORTO = "AAAAAAAAAA";
    private static final String UPDATED_DATA_INIZIO_TRASPORTO = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_RESA = "AAA";
    private static final String UPDATED_TIPO_RESA = "BBB";

    private static final String DEFAULT_INDIRIZZO = "AAAAAAAAAA";
    private static final String UPDATED_INDIRIZZO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_CIVICO = "AAAAAAAA";
    private static final String UPDATED_NUMERO_CIVICO = "BBBBBBBB";

    private static final String DEFAULT_C_AP = "AAAAA";
    private static final String UPDATED_C_AP = "BBBBB";

    private static final String DEFAULT_COMUNE = "AAAAAAAAAA";
    private static final String UPDATED_COMUNE = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCIA = "AA";
    private static final String UPDATED_PROVINCIA = "BB";

    private static final String DEFAULT_NAZIONE = "AA";
    private static final String UPDATED_NAZIONE = "BB";

    private static final String DEFAULT_DATA_ORA_CONSEGNA = "AAAAAAAAAA";
    private static final String UPDATED_DATA_ORA_CONSEGNA = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_FATTURA_PRINCIPALE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_FATTURA_PRINCIPALE = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_FATTURA_PRINCIPALE = "AAAAAAAAAA";
    private static final String UPDATED_DATA_FATTURA_PRINCIPALE = "BBBBBBBBBB";

    private static final String DEFAULT_D_V_DATA = "AAAAAAAAAA";
    private static final String UPDATED_D_V_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_D_V_TOTALE_PERCORSO = "AAAAAAAAAA";
    private static final String UPDATED_D_V_TOTALE_PERCORSO = "BBBBBBBBBB";

    @Autowired
    private FABodyRepository fABodyRepository;

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

    private MockMvc restFABodyMockMvc;

    private FABody fABody;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FABodyResource fABodyResource = new FABodyResource(fABodyRepository);
        this.restFABodyMockMvc = MockMvcBuilders.standaloneSetup(fABodyResource)
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
    public static FABody createEntity(EntityManager em) {
        FABody fABody = new FABody()
            .tipoDocumento(DEFAULT_TIPO_DOCUMENTO)
            .divisa(DEFAULT_DIVISA)
            .data(DEFAULT_DATA)
            .numero(DEFAULT_NUMERO)
            .tipoRitenuta(DEFAULT_TIPO_RITENUTA)
            .importoRitenuta(DEFAULT_IMPORTO_RITENUTA)
            .aliquotaRitenuta(DEFAULT_ALIQUOTA_RITENUTA)
            .causalePagamento(DEFAULT_CAUSALE_PAGAMENTO)
            .bolloVirtuale(DEFAULT_BOLLO_VIRTUALE)
            .importoBollo(DEFAULT_IMPORTO_BOLLO)
            .importoTotaleDocumento(DEFAULT_IMPORTO_TOTALE_DOCUMENTO)
            .arrotondamento(DEFAULT_ARROTONDAMENTO)
            .art73(DEFAULT_ART_73)
            .idPaese(DEFAULT_ID_PAESE)
            .idCodice(DEFAULT_ID_CODICE)
            .codiceFiscale(DEFAULT_CODICE_FISCALE)
            .denominazione(DEFAULT_DENOMINAZIONE)
            .nome(DEFAULT_NOME)
            .cognome(DEFAULT_COGNOME)
            .titolo(DEFAULT_TITOLO)
            .codEORI(DEFAULT_COD_EORI)
            .numeroLicenzaGuida(DEFAULT_NUMERO_LICENZA_GUIDA)
            .mezzoTrasporto(DEFAULT_MEZZO_TRASPORTO)
            .causaleTrasporto(DEFAULT_CAUSALE_TRASPORTO)
            .numeroColli(DEFAULT_NUMERO_COLLI)
            .descrizione(DEFAULT_DESCRIZIONE)
            .unitaMisuraPeso(DEFAULT_UNITA_MISURA_PESO)
            .pesoLordo(DEFAULT_PESO_LORDO)
            .pesoNetto(DEFAULT_PESO_NETTO)
            .dataOraRitiro(DEFAULT_DATA_ORA_RITIRO)
            .dataInizioTrasporto(DEFAULT_DATA_INIZIO_TRASPORTO)
            .tipoResa(DEFAULT_TIPO_RESA)
            .indirizzo(DEFAULT_INDIRIZZO)
            .numeroCivico(DEFAULT_NUMERO_CIVICO)
            .cAP(DEFAULT_C_AP)
            .comune(DEFAULT_COMUNE)
            .provincia(DEFAULT_PROVINCIA)
            .nazione(DEFAULT_NAZIONE)
            .dataOraConsegna(DEFAULT_DATA_ORA_CONSEGNA)
            .numeroFatturaPrincipale(DEFAULT_NUMERO_FATTURA_PRINCIPALE)
            .dataFatturaPrincipale(DEFAULT_DATA_FATTURA_PRINCIPALE)
            .dVData(DEFAULT_D_V_DATA)
            .dVTotalePercorso(DEFAULT_D_V_TOTALE_PERCORSO);
        return fABody;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FABody createUpdatedEntity(EntityManager em) {
        FABody fABody = new FABody()
            .tipoDocumento(UPDATED_TIPO_DOCUMENTO)
            .divisa(UPDATED_DIVISA)
            .data(UPDATED_DATA)
            .numero(UPDATED_NUMERO)
            .tipoRitenuta(UPDATED_TIPO_RITENUTA)
            .importoRitenuta(UPDATED_IMPORTO_RITENUTA)
            .aliquotaRitenuta(UPDATED_ALIQUOTA_RITENUTA)
            .causalePagamento(UPDATED_CAUSALE_PAGAMENTO)
            .bolloVirtuale(UPDATED_BOLLO_VIRTUALE)
            .importoBollo(UPDATED_IMPORTO_BOLLO)
            .importoTotaleDocumento(UPDATED_IMPORTO_TOTALE_DOCUMENTO)
            .arrotondamento(UPDATED_ARROTONDAMENTO)
            .art73(UPDATED_ART_73)
            .idPaese(UPDATED_ID_PAESE)
            .idCodice(UPDATED_ID_CODICE)
            .codiceFiscale(UPDATED_CODICE_FISCALE)
            .denominazione(UPDATED_DENOMINAZIONE)
            .nome(UPDATED_NOME)
            .cognome(UPDATED_COGNOME)
            .titolo(UPDATED_TITOLO)
            .codEORI(UPDATED_COD_EORI)
            .numeroLicenzaGuida(UPDATED_NUMERO_LICENZA_GUIDA)
            .mezzoTrasporto(UPDATED_MEZZO_TRASPORTO)
            .causaleTrasporto(UPDATED_CAUSALE_TRASPORTO)
            .numeroColli(UPDATED_NUMERO_COLLI)
            .descrizione(UPDATED_DESCRIZIONE)
            .unitaMisuraPeso(UPDATED_UNITA_MISURA_PESO)
            .pesoLordo(UPDATED_PESO_LORDO)
            .pesoNetto(UPDATED_PESO_NETTO)
            .dataOraRitiro(UPDATED_DATA_ORA_RITIRO)
            .dataInizioTrasporto(UPDATED_DATA_INIZIO_TRASPORTO)
            .tipoResa(UPDATED_TIPO_RESA)
            .indirizzo(UPDATED_INDIRIZZO)
            .numeroCivico(UPDATED_NUMERO_CIVICO)
            .cAP(UPDATED_C_AP)
            .comune(UPDATED_COMUNE)
            .provincia(UPDATED_PROVINCIA)
            .nazione(UPDATED_NAZIONE)
            .dataOraConsegna(UPDATED_DATA_ORA_CONSEGNA)
            .numeroFatturaPrincipale(UPDATED_NUMERO_FATTURA_PRINCIPALE)
            .dataFatturaPrincipale(UPDATED_DATA_FATTURA_PRINCIPALE)
            .dVData(UPDATED_D_V_DATA)
            .dVTotalePercorso(UPDATED_D_V_TOTALE_PERCORSO);
        return fABody;
    }

    @BeforeEach
    public void initTest() {
        fABody = createEntity(em);
    }

    @Test
    @Transactional
    public void createFABody() throws Exception {
        int databaseSizeBeforeCreate = fABodyRepository.findAll().size();

        // Create the FABody
        restFABodyMockMvc.perform(post("/api/fa-bodies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fABody)))
            .andExpect(status().isCreated());

        // Validate the FABody in the database
        List<FABody> fABodyList = fABodyRepository.findAll();
        assertThat(fABodyList).hasSize(databaseSizeBeforeCreate + 1);
        FABody testFABody = fABodyList.get(fABodyList.size() - 1);
        assertThat(testFABody.getTipoDocumento()).isEqualTo(DEFAULT_TIPO_DOCUMENTO);
        assertThat(testFABody.getDivisa()).isEqualTo(DEFAULT_DIVISA);
        assertThat(testFABody.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testFABody.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testFABody.getTipoRitenuta()).isEqualTo(DEFAULT_TIPO_RITENUTA);
        assertThat(testFABody.getImportoRitenuta()).isEqualTo(DEFAULT_IMPORTO_RITENUTA);
        assertThat(testFABody.getAliquotaRitenuta()).isEqualTo(DEFAULT_ALIQUOTA_RITENUTA);
        assertThat(testFABody.getCausalePagamento()).isEqualTo(DEFAULT_CAUSALE_PAGAMENTO);
        assertThat(testFABody.getBolloVirtuale()).isEqualTo(DEFAULT_BOLLO_VIRTUALE);
        assertThat(testFABody.getImportoBollo()).isEqualTo(DEFAULT_IMPORTO_BOLLO);
        assertThat(testFABody.getImportoTotaleDocumento()).isEqualTo(DEFAULT_IMPORTO_TOTALE_DOCUMENTO);
        assertThat(testFABody.getArrotondamento()).isEqualTo(DEFAULT_ARROTONDAMENTO);
        assertThat(testFABody.getArt73()).isEqualTo(DEFAULT_ART_73);
        assertThat(testFABody.getIdPaese()).isEqualTo(DEFAULT_ID_PAESE);
        assertThat(testFABody.getIdCodice()).isEqualTo(DEFAULT_ID_CODICE);
        assertThat(testFABody.getCodiceFiscale()).isEqualTo(DEFAULT_CODICE_FISCALE);
        assertThat(testFABody.getDenominazione()).isEqualTo(DEFAULT_DENOMINAZIONE);
        assertThat(testFABody.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testFABody.getCognome()).isEqualTo(DEFAULT_COGNOME);
        assertThat(testFABody.getTitolo()).isEqualTo(DEFAULT_TITOLO);
        assertThat(testFABody.getCodEORI()).isEqualTo(DEFAULT_COD_EORI);
        assertThat(testFABody.getNumeroLicenzaGuida()).isEqualTo(DEFAULT_NUMERO_LICENZA_GUIDA);
        assertThat(testFABody.getMezzoTrasporto()).isEqualTo(DEFAULT_MEZZO_TRASPORTO);
        assertThat(testFABody.getCausaleTrasporto()).isEqualTo(DEFAULT_CAUSALE_TRASPORTO);
        assertThat(testFABody.getNumeroColli()).isEqualTo(DEFAULT_NUMERO_COLLI);
        assertThat(testFABody.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
        assertThat(testFABody.getUnitaMisuraPeso()).isEqualTo(DEFAULT_UNITA_MISURA_PESO);
        assertThat(testFABody.getPesoLordo()).isEqualTo(DEFAULT_PESO_LORDO);
        assertThat(testFABody.getPesoNetto()).isEqualTo(DEFAULT_PESO_NETTO);
        assertThat(testFABody.getDataOraRitiro()).isEqualTo(DEFAULT_DATA_ORA_RITIRO);
        assertThat(testFABody.getDataInizioTrasporto()).isEqualTo(DEFAULT_DATA_INIZIO_TRASPORTO);
        assertThat(testFABody.getTipoResa()).isEqualTo(DEFAULT_TIPO_RESA);
        assertThat(testFABody.getIndirizzo()).isEqualTo(DEFAULT_INDIRIZZO);
        assertThat(testFABody.getNumeroCivico()).isEqualTo(DEFAULT_NUMERO_CIVICO);
        assertThat(testFABody.getcAP()).isEqualTo(DEFAULT_C_AP);
        assertThat(testFABody.getComune()).isEqualTo(DEFAULT_COMUNE);
        assertThat(testFABody.getProvincia()).isEqualTo(DEFAULT_PROVINCIA);
        assertThat(testFABody.getNazione()).isEqualTo(DEFAULT_NAZIONE);
        assertThat(testFABody.getDataOraConsegna()).isEqualTo(DEFAULT_DATA_ORA_CONSEGNA);
        assertThat(testFABody.getNumeroFatturaPrincipale()).isEqualTo(DEFAULT_NUMERO_FATTURA_PRINCIPALE);
        assertThat(testFABody.getDataFatturaPrincipale()).isEqualTo(DEFAULT_DATA_FATTURA_PRINCIPALE);
        assertThat(testFABody.getdVData()).isEqualTo(DEFAULT_D_V_DATA);
        assertThat(testFABody.getdVTotalePercorso()).isEqualTo(DEFAULT_D_V_TOTALE_PERCORSO);
    }

    @Test
    @Transactional
    public void createFABodyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fABodyRepository.findAll().size();

        // Create the FABody with an existing ID
        fABody.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFABodyMockMvc.perform(post("/api/fa-bodies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fABody)))
            .andExpect(status().isBadRequest());

        // Validate the FABody in the database
        List<FABody> fABodyList = fABodyRepository.findAll();
        assertThat(fABodyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFABodies() throws Exception {
        // Initialize the database
        fABodyRepository.saveAndFlush(fABody);

        // Get all the fABodyList
        restFABodyMockMvc.perform(get("/api/fa-bodies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fABody.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoDocumento").value(hasItem(DEFAULT_TIPO_DOCUMENTO.toString())))
            .andExpect(jsonPath("$.[*].divisa").value(hasItem(DEFAULT_DIVISA.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].tipoRitenuta").value(hasItem(DEFAULT_TIPO_RITENUTA.toString())))
            .andExpect(jsonPath("$.[*].importoRitenuta").value(hasItem(DEFAULT_IMPORTO_RITENUTA.toString())))
            .andExpect(jsonPath("$.[*].aliquotaRitenuta").value(hasItem(DEFAULT_ALIQUOTA_RITENUTA.toString())))
            .andExpect(jsonPath("$.[*].causalePagamento").value(hasItem(DEFAULT_CAUSALE_PAGAMENTO.toString())))
            .andExpect(jsonPath("$.[*].bolloVirtuale").value(hasItem(DEFAULT_BOLLO_VIRTUALE.toString())))
            .andExpect(jsonPath("$.[*].importoBollo").value(hasItem(DEFAULT_IMPORTO_BOLLO.toString())))
            .andExpect(jsonPath("$.[*].importoTotaleDocumento").value(hasItem(DEFAULT_IMPORTO_TOTALE_DOCUMENTO.toString())))
            .andExpect(jsonPath("$.[*].arrotondamento").value(hasItem(DEFAULT_ARROTONDAMENTO.toString())))
            .andExpect(jsonPath("$.[*].art73").value(hasItem(DEFAULT_ART_73.toString())))
            .andExpect(jsonPath("$.[*].idPaese").value(hasItem(DEFAULT_ID_PAESE.toString())))
            .andExpect(jsonPath("$.[*].idCodice").value(hasItem(DEFAULT_ID_CODICE.toString())))
            .andExpect(jsonPath("$.[*].codiceFiscale").value(hasItem(DEFAULT_CODICE_FISCALE.toString())))
            .andExpect(jsonPath("$.[*].denominazione").value(hasItem(DEFAULT_DENOMINAZIONE.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].cognome").value(hasItem(DEFAULT_COGNOME.toString())))
            .andExpect(jsonPath("$.[*].titolo").value(hasItem(DEFAULT_TITOLO.toString())))
            .andExpect(jsonPath("$.[*].codEORI").value(hasItem(DEFAULT_COD_EORI.toString())))
            .andExpect(jsonPath("$.[*].numeroLicenzaGuida").value(hasItem(DEFAULT_NUMERO_LICENZA_GUIDA.toString())))
            .andExpect(jsonPath("$.[*].mezzoTrasporto").value(hasItem(DEFAULT_MEZZO_TRASPORTO.toString())))
            .andExpect(jsonPath("$.[*].causaleTrasporto").value(hasItem(DEFAULT_CAUSALE_TRASPORTO.toString())))
            .andExpect(jsonPath("$.[*].numeroColli").value(hasItem(DEFAULT_NUMERO_COLLI.toString())))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE.toString())))
            .andExpect(jsonPath("$.[*].unitaMisuraPeso").value(hasItem(DEFAULT_UNITA_MISURA_PESO.toString())))
            .andExpect(jsonPath("$.[*].pesoLordo").value(hasItem(DEFAULT_PESO_LORDO.toString())))
            .andExpect(jsonPath("$.[*].pesoNetto").value(hasItem(DEFAULT_PESO_NETTO.toString())))
            .andExpect(jsonPath("$.[*].dataOraRitiro").value(hasItem(DEFAULT_DATA_ORA_RITIRO.toString())))
            .andExpect(jsonPath("$.[*].dataInizioTrasporto").value(hasItem(DEFAULT_DATA_INIZIO_TRASPORTO.toString())))
            .andExpect(jsonPath("$.[*].tipoResa").value(hasItem(DEFAULT_TIPO_RESA.toString())))
            .andExpect(jsonPath("$.[*].indirizzo").value(hasItem(DEFAULT_INDIRIZZO.toString())))
            .andExpect(jsonPath("$.[*].numeroCivico").value(hasItem(DEFAULT_NUMERO_CIVICO.toString())))
            .andExpect(jsonPath("$.[*].cAP").value(hasItem(DEFAULT_C_AP.toString())))
            .andExpect(jsonPath("$.[*].comune").value(hasItem(DEFAULT_COMUNE.toString())))
            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_PROVINCIA.toString())))
            .andExpect(jsonPath("$.[*].nazione").value(hasItem(DEFAULT_NAZIONE.toString())))
            .andExpect(jsonPath("$.[*].dataOraConsegna").value(hasItem(DEFAULT_DATA_ORA_CONSEGNA.toString())))
            .andExpect(jsonPath("$.[*].numeroFatturaPrincipale").value(hasItem(DEFAULT_NUMERO_FATTURA_PRINCIPALE.toString())))
            .andExpect(jsonPath("$.[*].dataFatturaPrincipale").value(hasItem(DEFAULT_DATA_FATTURA_PRINCIPALE.toString())))
            .andExpect(jsonPath("$.[*].dVData").value(hasItem(DEFAULT_D_V_DATA.toString())))
            .andExpect(jsonPath("$.[*].dVTotalePercorso").value(hasItem(DEFAULT_D_V_TOTALE_PERCORSO.toString())));
    }
    
    @Test
    @Transactional
    public void getFABody() throws Exception {
        // Initialize the database
        fABodyRepository.saveAndFlush(fABody);

        // Get the fABody
        restFABodyMockMvc.perform(get("/api/fa-bodies/{id}", fABody.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fABody.getId().intValue()))
            .andExpect(jsonPath("$.tipoDocumento").value(DEFAULT_TIPO_DOCUMENTO.toString()))
            .andExpect(jsonPath("$.divisa").value(DEFAULT_DIVISA.toString()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.tipoRitenuta").value(DEFAULT_TIPO_RITENUTA.toString()))
            .andExpect(jsonPath("$.importoRitenuta").value(DEFAULT_IMPORTO_RITENUTA.toString()))
            .andExpect(jsonPath("$.aliquotaRitenuta").value(DEFAULT_ALIQUOTA_RITENUTA.toString()))
            .andExpect(jsonPath("$.causalePagamento").value(DEFAULT_CAUSALE_PAGAMENTO.toString()))
            .andExpect(jsonPath("$.bolloVirtuale").value(DEFAULT_BOLLO_VIRTUALE.toString()))
            .andExpect(jsonPath("$.importoBollo").value(DEFAULT_IMPORTO_BOLLO.toString()))
            .andExpect(jsonPath("$.importoTotaleDocumento").value(DEFAULT_IMPORTO_TOTALE_DOCUMENTO.toString()))
            .andExpect(jsonPath("$.arrotondamento").value(DEFAULT_ARROTONDAMENTO.toString()))
            .andExpect(jsonPath("$.art73").value(DEFAULT_ART_73.toString()))
            .andExpect(jsonPath("$.idPaese").value(DEFAULT_ID_PAESE.toString()))
            .andExpect(jsonPath("$.idCodice").value(DEFAULT_ID_CODICE.toString()))
            .andExpect(jsonPath("$.codiceFiscale").value(DEFAULT_CODICE_FISCALE.toString()))
            .andExpect(jsonPath("$.denominazione").value(DEFAULT_DENOMINAZIONE.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.cognome").value(DEFAULT_COGNOME.toString()))
            .andExpect(jsonPath("$.titolo").value(DEFAULT_TITOLO.toString()))
            .andExpect(jsonPath("$.codEORI").value(DEFAULT_COD_EORI.toString()))
            .andExpect(jsonPath("$.numeroLicenzaGuida").value(DEFAULT_NUMERO_LICENZA_GUIDA.toString()))
            .andExpect(jsonPath("$.mezzoTrasporto").value(DEFAULT_MEZZO_TRASPORTO.toString()))
            .andExpect(jsonPath("$.causaleTrasporto").value(DEFAULT_CAUSALE_TRASPORTO.toString()))
            .andExpect(jsonPath("$.numeroColli").value(DEFAULT_NUMERO_COLLI.toString()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE.toString()))
            .andExpect(jsonPath("$.unitaMisuraPeso").value(DEFAULT_UNITA_MISURA_PESO.toString()))
            .andExpect(jsonPath("$.pesoLordo").value(DEFAULT_PESO_LORDO.toString()))
            .andExpect(jsonPath("$.pesoNetto").value(DEFAULT_PESO_NETTO.toString()))
            .andExpect(jsonPath("$.dataOraRitiro").value(DEFAULT_DATA_ORA_RITIRO.toString()))
            .andExpect(jsonPath("$.dataInizioTrasporto").value(DEFAULT_DATA_INIZIO_TRASPORTO.toString()))
            .andExpect(jsonPath("$.tipoResa").value(DEFAULT_TIPO_RESA.toString()))
            .andExpect(jsonPath("$.indirizzo").value(DEFAULT_INDIRIZZO.toString()))
            .andExpect(jsonPath("$.numeroCivico").value(DEFAULT_NUMERO_CIVICO.toString()))
            .andExpect(jsonPath("$.cAP").value(DEFAULT_C_AP.toString()))
            .andExpect(jsonPath("$.comune").value(DEFAULT_COMUNE.toString()))
            .andExpect(jsonPath("$.provincia").value(DEFAULT_PROVINCIA.toString()))
            .andExpect(jsonPath("$.nazione").value(DEFAULT_NAZIONE.toString()))
            .andExpect(jsonPath("$.dataOraConsegna").value(DEFAULT_DATA_ORA_CONSEGNA.toString()))
            .andExpect(jsonPath("$.numeroFatturaPrincipale").value(DEFAULT_NUMERO_FATTURA_PRINCIPALE.toString()))
            .andExpect(jsonPath("$.dataFatturaPrincipale").value(DEFAULT_DATA_FATTURA_PRINCIPALE.toString()))
            .andExpect(jsonPath("$.dVData").value(DEFAULT_D_V_DATA.toString()))
            .andExpect(jsonPath("$.dVTotalePercorso").value(DEFAULT_D_V_TOTALE_PERCORSO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFABody() throws Exception {
        // Get the fABody
        restFABodyMockMvc.perform(get("/api/fa-bodies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFABody() throws Exception {
        // Initialize the database
        fABodyRepository.saveAndFlush(fABody);

        int databaseSizeBeforeUpdate = fABodyRepository.findAll().size();

        // Update the fABody
        FABody updatedFABody = fABodyRepository.findById(fABody.getId()).get();
        // Disconnect from session so that the updates on updatedFABody are not directly saved in db
        em.detach(updatedFABody);
        updatedFABody
            .tipoDocumento(UPDATED_TIPO_DOCUMENTO)
            .divisa(UPDATED_DIVISA)
            .data(UPDATED_DATA)
            .numero(UPDATED_NUMERO)
            .tipoRitenuta(UPDATED_TIPO_RITENUTA)
            .importoRitenuta(UPDATED_IMPORTO_RITENUTA)
            .aliquotaRitenuta(UPDATED_ALIQUOTA_RITENUTA)
            .causalePagamento(UPDATED_CAUSALE_PAGAMENTO)
            .bolloVirtuale(UPDATED_BOLLO_VIRTUALE)
            .importoBollo(UPDATED_IMPORTO_BOLLO)
            .importoTotaleDocumento(UPDATED_IMPORTO_TOTALE_DOCUMENTO)
            .arrotondamento(UPDATED_ARROTONDAMENTO)
            .art73(UPDATED_ART_73)
            .idPaese(UPDATED_ID_PAESE)
            .idCodice(UPDATED_ID_CODICE)
            .codiceFiscale(UPDATED_CODICE_FISCALE)
            .denominazione(UPDATED_DENOMINAZIONE)
            .nome(UPDATED_NOME)
            .cognome(UPDATED_COGNOME)
            .titolo(UPDATED_TITOLO)
            .codEORI(UPDATED_COD_EORI)
            .numeroLicenzaGuida(UPDATED_NUMERO_LICENZA_GUIDA)
            .mezzoTrasporto(UPDATED_MEZZO_TRASPORTO)
            .causaleTrasporto(UPDATED_CAUSALE_TRASPORTO)
            .numeroColli(UPDATED_NUMERO_COLLI)
            .descrizione(UPDATED_DESCRIZIONE)
            .unitaMisuraPeso(UPDATED_UNITA_MISURA_PESO)
            .pesoLordo(UPDATED_PESO_LORDO)
            .pesoNetto(UPDATED_PESO_NETTO)
            .dataOraRitiro(UPDATED_DATA_ORA_RITIRO)
            .dataInizioTrasporto(UPDATED_DATA_INIZIO_TRASPORTO)
            .tipoResa(UPDATED_TIPO_RESA)
            .indirizzo(UPDATED_INDIRIZZO)
            .numeroCivico(UPDATED_NUMERO_CIVICO)
            .cAP(UPDATED_C_AP)
            .comune(UPDATED_COMUNE)
            .provincia(UPDATED_PROVINCIA)
            .nazione(UPDATED_NAZIONE)
            .dataOraConsegna(UPDATED_DATA_ORA_CONSEGNA)
            .numeroFatturaPrincipale(UPDATED_NUMERO_FATTURA_PRINCIPALE)
            .dataFatturaPrincipale(UPDATED_DATA_FATTURA_PRINCIPALE)
            .dVData(UPDATED_D_V_DATA)
            .dVTotalePercorso(UPDATED_D_V_TOTALE_PERCORSO);

        restFABodyMockMvc.perform(put("/api/fa-bodies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFABody)))
            .andExpect(status().isOk());

        // Validate the FABody in the database
        List<FABody> fABodyList = fABodyRepository.findAll();
        assertThat(fABodyList).hasSize(databaseSizeBeforeUpdate);
        FABody testFABody = fABodyList.get(fABodyList.size() - 1);
        assertThat(testFABody.getTipoDocumento()).isEqualTo(UPDATED_TIPO_DOCUMENTO);
        assertThat(testFABody.getDivisa()).isEqualTo(UPDATED_DIVISA);
        assertThat(testFABody.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testFABody.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testFABody.getTipoRitenuta()).isEqualTo(UPDATED_TIPO_RITENUTA);
        assertThat(testFABody.getImportoRitenuta()).isEqualTo(UPDATED_IMPORTO_RITENUTA);
        assertThat(testFABody.getAliquotaRitenuta()).isEqualTo(UPDATED_ALIQUOTA_RITENUTA);
        assertThat(testFABody.getCausalePagamento()).isEqualTo(UPDATED_CAUSALE_PAGAMENTO);
        assertThat(testFABody.getBolloVirtuale()).isEqualTo(UPDATED_BOLLO_VIRTUALE);
        assertThat(testFABody.getImportoBollo()).isEqualTo(UPDATED_IMPORTO_BOLLO);
        assertThat(testFABody.getImportoTotaleDocumento()).isEqualTo(UPDATED_IMPORTO_TOTALE_DOCUMENTO);
        assertThat(testFABody.getArrotondamento()).isEqualTo(UPDATED_ARROTONDAMENTO);
        assertThat(testFABody.getArt73()).isEqualTo(UPDATED_ART_73);
        assertThat(testFABody.getIdPaese()).isEqualTo(UPDATED_ID_PAESE);
        assertThat(testFABody.getIdCodice()).isEqualTo(UPDATED_ID_CODICE);
        assertThat(testFABody.getCodiceFiscale()).isEqualTo(UPDATED_CODICE_FISCALE);
        assertThat(testFABody.getDenominazione()).isEqualTo(UPDATED_DENOMINAZIONE);
        assertThat(testFABody.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testFABody.getCognome()).isEqualTo(UPDATED_COGNOME);
        assertThat(testFABody.getTitolo()).isEqualTo(UPDATED_TITOLO);
        assertThat(testFABody.getCodEORI()).isEqualTo(UPDATED_COD_EORI);
        assertThat(testFABody.getNumeroLicenzaGuida()).isEqualTo(UPDATED_NUMERO_LICENZA_GUIDA);
        assertThat(testFABody.getMezzoTrasporto()).isEqualTo(UPDATED_MEZZO_TRASPORTO);
        assertThat(testFABody.getCausaleTrasporto()).isEqualTo(UPDATED_CAUSALE_TRASPORTO);
        assertThat(testFABody.getNumeroColli()).isEqualTo(UPDATED_NUMERO_COLLI);
        assertThat(testFABody.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
        assertThat(testFABody.getUnitaMisuraPeso()).isEqualTo(UPDATED_UNITA_MISURA_PESO);
        assertThat(testFABody.getPesoLordo()).isEqualTo(UPDATED_PESO_LORDO);
        assertThat(testFABody.getPesoNetto()).isEqualTo(UPDATED_PESO_NETTO);
        assertThat(testFABody.getDataOraRitiro()).isEqualTo(UPDATED_DATA_ORA_RITIRO);
        assertThat(testFABody.getDataInizioTrasporto()).isEqualTo(UPDATED_DATA_INIZIO_TRASPORTO);
        assertThat(testFABody.getTipoResa()).isEqualTo(UPDATED_TIPO_RESA);
        assertThat(testFABody.getIndirizzo()).isEqualTo(UPDATED_INDIRIZZO);
        assertThat(testFABody.getNumeroCivico()).isEqualTo(UPDATED_NUMERO_CIVICO);
        assertThat(testFABody.getcAP()).isEqualTo(UPDATED_C_AP);
        assertThat(testFABody.getComune()).isEqualTo(UPDATED_COMUNE);
        assertThat(testFABody.getProvincia()).isEqualTo(UPDATED_PROVINCIA);
        assertThat(testFABody.getNazione()).isEqualTo(UPDATED_NAZIONE);
        assertThat(testFABody.getDataOraConsegna()).isEqualTo(UPDATED_DATA_ORA_CONSEGNA);
        assertThat(testFABody.getNumeroFatturaPrincipale()).isEqualTo(UPDATED_NUMERO_FATTURA_PRINCIPALE);
        assertThat(testFABody.getDataFatturaPrincipale()).isEqualTo(UPDATED_DATA_FATTURA_PRINCIPALE);
        assertThat(testFABody.getdVData()).isEqualTo(UPDATED_D_V_DATA);
        assertThat(testFABody.getdVTotalePercorso()).isEqualTo(UPDATED_D_V_TOTALE_PERCORSO);
    }

    @Test
    @Transactional
    public void updateNonExistingFABody() throws Exception {
        int databaseSizeBeforeUpdate = fABodyRepository.findAll().size();

        // Create the FABody

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFABodyMockMvc.perform(put("/api/fa-bodies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fABody)))
            .andExpect(status().isBadRequest());

        // Validate the FABody in the database
        List<FABody> fABodyList = fABodyRepository.findAll();
        assertThat(fABodyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFABody() throws Exception {
        // Initialize the database
        fABodyRepository.saveAndFlush(fABody);

        int databaseSizeBeforeDelete = fABodyRepository.findAll().size();

        // Delete the fABody
        restFABodyMockMvc.perform(delete("/api/fa-bodies/{id}", fABody.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<FABody> fABodyList = fABodyRepository.findAll();
        assertThat(fABodyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FABody.class);
        FABody fABody1 = new FABody();
        fABody1.setId(1L);
        FABody fABody2 = new FABody();
        fABody2.setId(fABody1.getId());
        assertThat(fABody1).isEqualTo(fABody2);
        fABody2.setId(2L);
        assertThat(fABody1).isNotEqualTo(fABody2);
        fABody1.setId(null);
        assertThat(fABody1).isNotEqualTo(fABody2);
    }
}
