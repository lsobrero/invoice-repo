package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.DettaglioLinea;
import net.anet.invoice.repo.repository.DettaglioLineaRepository;
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
 * Integration tests for the {@Link DettaglioLineaResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class DettaglioLineaResourceIT {

    private static final String DEFAULT_NUMERO_LINEA = "AAAA";
    private static final String UPDATED_NUMERO_LINEA = "BBBB";

    private static final String DEFAULT_TIPO_CESSIONE_PRESTAZIONE = "AA";
    private static final String UPDATED_TIPO_CESSIONE_PRESTAZIONE = "BB";

    private static final String DEFAULT_D_L_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_D_L_DESCRIZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_QUANTITA = "AAAAAAAAAA";
    private static final String UPDATED_QUANTITA = "BBBBBBBBBB";

    private static final String DEFAULT_UNITA_MISURA = "AAAAAAAAAA";
    private static final String UPDATED_UNITA_MISURA = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_INIZIO_PERIODO = "AAAAAAAAAA";
    private static final String UPDATED_DATA_INIZIO_PERIODO = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_FINE_PERIODO = "AAAAAAAAAA";
    private static final String UPDATED_DATA_FINE_PERIODO = "BBBBBBBBBB";

    private static final String DEFAULT_PREZZO_UNITARIO = "AAAAAAAAAA";
    private static final String UPDATED_PREZZO_UNITARIO = "BBBBBBBBBB";

    private static final String DEFAULT_S_M_PREZZO_TOTALE = "AAAAAAAAAA";
    private static final String UPDATED_S_M_PREZZO_TOTALE = "BBBBBBBBBB";

    private static final String DEFAULT_S_M_ALIQUOTA_IVA = "AAAAAA";
    private static final String UPDATED_S_M_ALIQUOTA_IVA = "BBBBBB";

    private static final String DEFAULT_S_M_RITENUTA = "AA";
    private static final String UPDATED_S_M_RITENUTA = "BB";

    private static final String DEFAULT_S_M_NATURA = "AA";
    private static final String UPDATED_S_M_NATURA = "BB";

    private static final String DEFAULT_S_M_RIFERIMENTO_AMMINISTRAZIONE = "AAAAAAAAAA";
    private static final String UPDATED_S_M_RIFERIMENTO_AMMINISTRAZIONE = "BBBBBBBBBB";

    @Autowired
    private DettaglioLineaRepository dettaglioLineaRepository;

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

    private MockMvc restDettaglioLineaMockMvc;

    private DettaglioLinea dettaglioLinea;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DettaglioLineaResource dettaglioLineaResource = new DettaglioLineaResource(dettaglioLineaRepository);
        this.restDettaglioLineaMockMvc = MockMvcBuilders.standaloneSetup(dettaglioLineaResource)
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
    public static DettaglioLinea createEntity(EntityManager em) {
        DettaglioLinea dettaglioLinea = new DettaglioLinea()
            .numeroLinea(DEFAULT_NUMERO_LINEA)
            .tipoCessionePrestazione(DEFAULT_TIPO_CESSIONE_PRESTAZIONE)
            .dLDescrizione(DEFAULT_D_L_DESCRIZIONE)
            .quantita(DEFAULT_QUANTITA)
            .unitaMisura(DEFAULT_UNITA_MISURA)
            .dataInizioPeriodo(DEFAULT_DATA_INIZIO_PERIODO)
            .dataFinePeriodo(DEFAULT_DATA_FINE_PERIODO)
            .prezzoUnitario(DEFAULT_PREZZO_UNITARIO)
            .sMPrezzoTotale(DEFAULT_S_M_PREZZO_TOTALE)
            .sMAliquotaIVA(DEFAULT_S_M_ALIQUOTA_IVA)
            .sMRitenuta(DEFAULT_S_M_RITENUTA)
            .sMNatura(DEFAULT_S_M_NATURA)
            .sMRiferimentoAmministrazione(DEFAULT_S_M_RIFERIMENTO_AMMINISTRAZIONE);
        return dettaglioLinea;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DettaglioLinea createUpdatedEntity(EntityManager em) {
        DettaglioLinea dettaglioLinea = new DettaglioLinea()
            .numeroLinea(UPDATED_NUMERO_LINEA)
            .tipoCessionePrestazione(UPDATED_TIPO_CESSIONE_PRESTAZIONE)
            .dLDescrizione(UPDATED_D_L_DESCRIZIONE)
            .quantita(UPDATED_QUANTITA)
            .unitaMisura(UPDATED_UNITA_MISURA)
            .dataInizioPeriodo(UPDATED_DATA_INIZIO_PERIODO)
            .dataFinePeriodo(UPDATED_DATA_FINE_PERIODO)
            .prezzoUnitario(UPDATED_PREZZO_UNITARIO)
            .sMPrezzoTotale(UPDATED_S_M_PREZZO_TOTALE)
            .sMAliquotaIVA(UPDATED_S_M_ALIQUOTA_IVA)
            .sMRitenuta(UPDATED_S_M_RITENUTA)
            .sMNatura(UPDATED_S_M_NATURA)
            .sMRiferimentoAmministrazione(UPDATED_S_M_RIFERIMENTO_AMMINISTRAZIONE);
        return dettaglioLinea;
    }

    @BeforeEach
    public void initTest() {
        dettaglioLinea = createEntity(em);
    }

    @Test
    @Transactional
    public void createDettaglioLinea() throws Exception {
        int databaseSizeBeforeCreate = dettaglioLineaRepository.findAll().size();

        // Create the DettaglioLinea
        restDettaglioLineaMockMvc.perform(post("/api/dettaglio-lineas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dettaglioLinea)))
            .andExpect(status().isCreated());

        // Validate the DettaglioLinea in the database
        List<DettaglioLinea> dettaglioLineaList = dettaglioLineaRepository.findAll();
        assertThat(dettaglioLineaList).hasSize(databaseSizeBeforeCreate + 1);
        DettaglioLinea testDettaglioLinea = dettaglioLineaList.get(dettaglioLineaList.size() - 1);
        assertThat(testDettaglioLinea.getNumeroLinea()).isEqualTo(DEFAULT_NUMERO_LINEA);
        assertThat(testDettaglioLinea.getTipoCessionePrestazione()).isEqualTo(DEFAULT_TIPO_CESSIONE_PRESTAZIONE);
        assertThat(testDettaglioLinea.getdLDescrizione()).isEqualTo(DEFAULT_D_L_DESCRIZIONE);
        assertThat(testDettaglioLinea.getQuantita()).isEqualTo(DEFAULT_QUANTITA);
        assertThat(testDettaglioLinea.getUnitaMisura()).isEqualTo(DEFAULT_UNITA_MISURA);
        assertThat(testDettaglioLinea.getDataInizioPeriodo()).isEqualTo(DEFAULT_DATA_INIZIO_PERIODO);
        assertThat(testDettaglioLinea.getDataFinePeriodo()).isEqualTo(DEFAULT_DATA_FINE_PERIODO);
        assertThat(testDettaglioLinea.getPrezzoUnitario()).isEqualTo(DEFAULT_PREZZO_UNITARIO);
        assertThat(testDettaglioLinea.getsMPrezzoTotale()).isEqualTo(DEFAULT_S_M_PREZZO_TOTALE);
        assertThat(testDettaglioLinea.getsMAliquotaIVA()).isEqualTo(DEFAULT_S_M_ALIQUOTA_IVA);
        assertThat(testDettaglioLinea.getsMRitenuta()).isEqualTo(DEFAULT_S_M_RITENUTA);
        assertThat(testDettaglioLinea.getsMNatura()).isEqualTo(DEFAULT_S_M_NATURA);
        assertThat(testDettaglioLinea.getsMRiferimentoAmministrazione()).isEqualTo(DEFAULT_S_M_RIFERIMENTO_AMMINISTRAZIONE);
    }

    @Test
    @Transactional
    public void createDettaglioLineaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dettaglioLineaRepository.findAll().size();

        // Create the DettaglioLinea with an existing ID
        dettaglioLinea.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDettaglioLineaMockMvc.perform(post("/api/dettaglio-lineas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dettaglioLinea)))
            .andExpect(status().isBadRequest());

        // Validate the DettaglioLinea in the database
        List<DettaglioLinea> dettaglioLineaList = dettaglioLineaRepository.findAll();
        assertThat(dettaglioLineaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDettaglioLineas() throws Exception {
        // Initialize the database
        dettaglioLineaRepository.saveAndFlush(dettaglioLinea);

        // Get all the dettaglioLineaList
        restDettaglioLineaMockMvc.perform(get("/api/dettaglio-lineas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dettaglioLinea.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroLinea").value(hasItem(DEFAULT_NUMERO_LINEA.toString())))
            .andExpect(jsonPath("$.[*].tipoCessionePrestazione").value(hasItem(DEFAULT_TIPO_CESSIONE_PRESTAZIONE.toString())))
            .andExpect(jsonPath("$.[*].dLDescrizione").value(hasItem(DEFAULT_D_L_DESCRIZIONE.toString())))
            .andExpect(jsonPath("$.[*].quantita").value(hasItem(DEFAULT_QUANTITA.toString())))
            .andExpect(jsonPath("$.[*].unitaMisura").value(hasItem(DEFAULT_UNITA_MISURA.toString())))
            .andExpect(jsonPath("$.[*].dataInizioPeriodo").value(hasItem(DEFAULT_DATA_INIZIO_PERIODO.toString())))
            .andExpect(jsonPath("$.[*].dataFinePeriodo").value(hasItem(DEFAULT_DATA_FINE_PERIODO.toString())))
            .andExpect(jsonPath("$.[*].prezzoUnitario").value(hasItem(DEFAULT_PREZZO_UNITARIO.toString())))
            .andExpect(jsonPath("$.[*].sMPrezzoTotale").value(hasItem(DEFAULT_S_M_PREZZO_TOTALE.toString())))
            .andExpect(jsonPath("$.[*].sMAliquotaIVA").value(hasItem(DEFAULT_S_M_ALIQUOTA_IVA.toString())))
            .andExpect(jsonPath("$.[*].sMRitenuta").value(hasItem(DEFAULT_S_M_RITENUTA.toString())))
            .andExpect(jsonPath("$.[*].sMNatura").value(hasItem(DEFAULT_S_M_NATURA.toString())))
            .andExpect(jsonPath("$.[*].sMRiferimentoAmministrazione").value(hasItem(DEFAULT_S_M_RIFERIMENTO_AMMINISTRAZIONE.toString())));
    }
    
    @Test
    @Transactional
    public void getDettaglioLinea() throws Exception {
        // Initialize the database
        dettaglioLineaRepository.saveAndFlush(dettaglioLinea);

        // Get the dettaglioLinea
        restDettaglioLineaMockMvc.perform(get("/api/dettaglio-lineas/{id}", dettaglioLinea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dettaglioLinea.getId().intValue()))
            .andExpect(jsonPath("$.numeroLinea").value(DEFAULT_NUMERO_LINEA.toString()))
            .andExpect(jsonPath("$.tipoCessionePrestazione").value(DEFAULT_TIPO_CESSIONE_PRESTAZIONE.toString()))
            .andExpect(jsonPath("$.dLDescrizione").value(DEFAULT_D_L_DESCRIZIONE.toString()))
            .andExpect(jsonPath("$.quantita").value(DEFAULT_QUANTITA.toString()))
            .andExpect(jsonPath("$.unitaMisura").value(DEFAULT_UNITA_MISURA.toString()))
            .andExpect(jsonPath("$.dataInizioPeriodo").value(DEFAULT_DATA_INIZIO_PERIODO.toString()))
            .andExpect(jsonPath("$.dataFinePeriodo").value(DEFAULT_DATA_FINE_PERIODO.toString()))
            .andExpect(jsonPath("$.prezzoUnitario").value(DEFAULT_PREZZO_UNITARIO.toString()))
            .andExpect(jsonPath("$.sMPrezzoTotale").value(DEFAULT_S_M_PREZZO_TOTALE.toString()))
            .andExpect(jsonPath("$.sMAliquotaIVA").value(DEFAULT_S_M_ALIQUOTA_IVA.toString()))
            .andExpect(jsonPath("$.sMRitenuta").value(DEFAULT_S_M_RITENUTA.toString()))
            .andExpect(jsonPath("$.sMNatura").value(DEFAULT_S_M_NATURA.toString()))
            .andExpect(jsonPath("$.sMRiferimentoAmministrazione").value(DEFAULT_S_M_RIFERIMENTO_AMMINISTRAZIONE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDettaglioLinea() throws Exception {
        // Get the dettaglioLinea
        restDettaglioLineaMockMvc.perform(get("/api/dettaglio-lineas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDettaglioLinea() throws Exception {
        // Initialize the database
        dettaglioLineaRepository.saveAndFlush(dettaglioLinea);

        int databaseSizeBeforeUpdate = dettaglioLineaRepository.findAll().size();

        // Update the dettaglioLinea
        DettaglioLinea updatedDettaglioLinea = dettaglioLineaRepository.findById(dettaglioLinea.getId()).get();
        // Disconnect from session so that the updates on updatedDettaglioLinea are not directly saved in db
        em.detach(updatedDettaglioLinea);
        updatedDettaglioLinea
            .numeroLinea(UPDATED_NUMERO_LINEA)
            .tipoCessionePrestazione(UPDATED_TIPO_CESSIONE_PRESTAZIONE)
            .dLDescrizione(UPDATED_D_L_DESCRIZIONE)
            .quantita(UPDATED_QUANTITA)
            .unitaMisura(UPDATED_UNITA_MISURA)
            .dataInizioPeriodo(UPDATED_DATA_INIZIO_PERIODO)
            .dataFinePeriodo(UPDATED_DATA_FINE_PERIODO)
            .prezzoUnitario(UPDATED_PREZZO_UNITARIO)
            .sMPrezzoTotale(UPDATED_S_M_PREZZO_TOTALE)
            .sMAliquotaIVA(UPDATED_S_M_ALIQUOTA_IVA)
            .sMRitenuta(UPDATED_S_M_RITENUTA)
            .sMNatura(UPDATED_S_M_NATURA)
            .sMRiferimentoAmministrazione(UPDATED_S_M_RIFERIMENTO_AMMINISTRAZIONE);

        restDettaglioLineaMockMvc.perform(put("/api/dettaglio-lineas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDettaglioLinea)))
            .andExpect(status().isOk());

        // Validate the DettaglioLinea in the database
        List<DettaglioLinea> dettaglioLineaList = dettaglioLineaRepository.findAll();
        assertThat(dettaglioLineaList).hasSize(databaseSizeBeforeUpdate);
        DettaglioLinea testDettaglioLinea = dettaglioLineaList.get(dettaglioLineaList.size() - 1);
        assertThat(testDettaglioLinea.getNumeroLinea()).isEqualTo(UPDATED_NUMERO_LINEA);
        assertThat(testDettaglioLinea.getTipoCessionePrestazione()).isEqualTo(UPDATED_TIPO_CESSIONE_PRESTAZIONE);
        assertThat(testDettaglioLinea.getdLDescrizione()).isEqualTo(UPDATED_D_L_DESCRIZIONE);
        assertThat(testDettaglioLinea.getQuantita()).isEqualTo(UPDATED_QUANTITA);
        assertThat(testDettaglioLinea.getUnitaMisura()).isEqualTo(UPDATED_UNITA_MISURA);
        assertThat(testDettaglioLinea.getDataInizioPeriodo()).isEqualTo(UPDATED_DATA_INIZIO_PERIODO);
        assertThat(testDettaglioLinea.getDataFinePeriodo()).isEqualTo(UPDATED_DATA_FINE_PERIODO);
        assertThat(testDettaglioLinea.getPrezzoUnitario()).isEqualTo(UPDATED_PREZZO_UNITARIO);
        assertThat(testDettaglioLinea.getsMPrezzoTotale()).isEqualTo(UPDATED_S_M_PREZZO_TOTALE);
        assertThat(testDettaglioLinea.getsMAliquotaIVA()).isEqualTo(UPDATED_S_M_ALIQUOTA_IVA);
        assertThat(testDettaglioLinea.getsMRitenuta()).isEqualTo(UPDATED_S_M_RITENUTA);
        assertThat(testDettaglioLinea.getsMNatura()).isEqualTo(UPDATED_S_M_NATURA);
        assertThat(testDettaglioLinea.getsMRiferimentoAmministrazione()).isEqualTo(UPDATED_S_M_RIFERIMENTO_AMMINISTRAZIONE);
    }

    @Test
    @Transactional
    public void updateNonExistingDettaglioLinea() throws Exception {
        int databaseSizeBeforeUpdate = dettaglioLineaRepository.findAll().size();

        // Create the DettaglioLinea

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDettaglioLineaMockMvc.perform(put("/api/dettaglio-lineas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dettaglioLinea)))
            .andExpect(status().isBadRequest());

        // Validate the DettaglioLinea in the database
        List<DettaglioLinea> dettaglioLineaList = dettaglioLineaRepository.findAll();
        assertThat(dettaglioLineaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDettaglioLinea() throws Exception {
        // Initialize the database
        dettaglioLineaRepository.saveAndFlush(dettaglioLinea);

        int databaseSizeBeforeDelete = dettaglioLineaRepository.findAll().size();

        // Delete the dettaglioLinea
        restDettaglioLineaMockMvc.perform(delete("/api/dettaglio-lineas/{id}", dettaglioLinea.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<DettaglioLinea> dettaglioLineaList = dettaglioLineaRepository.findAll();
        assertThat(dettaglioLineaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DettaglioLinea.class);
        DettaglioLinea dettaglioLinea1 = new DettaglioLinea();
        dettaglioLinea1.setId(1L);
        DettaglioLinea dettaglioLinea2 = new DettaglioLinea();
        dettaglioLinea2.setId(dettaglioLinea1.getId());
        assertThat(dettaglioLinea1).isEqualTo(dettaglioLinea2);
        dettaglioLinea2.setId(2L);
        assertThat(dettaglioLinea1).isNotEqualTo(dettaglioLinea2);
        dettaglioLinea1.setId(null);
        assertThat(dettaglioLinea1).isNotEqualTo(dettaglioLinea2);
    }
}
