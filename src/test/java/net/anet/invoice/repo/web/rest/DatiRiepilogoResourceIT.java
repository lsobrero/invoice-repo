package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.DatiRiepilogo;
import net.anet.invoice.repo.repository.DatiRiepilogoRepository;
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
 * Integration tests for the {@Link DatiRiepilogoResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class DatiRiepilogoResourceIT {

    private static final String DEFAULT_D_R_ALIQUOTA_IVA = "AAAAAA";
    private static final String UPDATED_D_R_ALIQUOTA_IVA = "BBBBBB";

    private static final String DEFAULT_D_R_NATURA = "AA";
    private static final String UPDATED_D_R_NATURA = "BB";

    private static final String DEFAULT_D_R_SPESE_ACCESSORIE = "AAAAAAAAAA";
    private static final String UPDATED_D_R_SPESE_ACCESSORIE = "BBBBBBBBBB";

    private static final String DEFAULT_D_R_ARROTONDAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_D_R_ARROTONDAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_D_R_IMPONIBILE_IMPORTO = "AAAAAAAAAA";
    private static final String UPDATED_D_R_IMPONIBILE_IMPORTO = "BBBBBBBBBB";

    private static final String DEFAULT_D_R_IMPOSTA = "AAAAAAAAAA";
    private static final String UPDATED_D_R_IMPOSTA = "BBBBBBBBBB";

    private static final String DEFAULT_D_R_ESIGIBILITA_IVA = "A";
    private static final String UPDATED_D_R_ESIGIBILITA_IVA = "B";

    private static final String DEFAULT_D_R_RIFERIMENTO_NORMATIVO = "AAAAAAAAAA";
    private static final String UPDATED_D_R_RIFERIMENTO_NORMATIVO = "BBBBBBBBBB";

    @Autowired
    private DatiRiepilogoRepository datiRiepilogoRepository;

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

    private MockMvc restDatiRiepilogoMockMvc;

    private DatiRiepilogo datiRiepilogo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DatiRiepilogoResource datiRiepilogoResource = new DatiRiepilogoResource(datiRiepilogoRepository);
        this.restDatiRiepilogoMockMvc = MockMvcBuilders.standaloneSetup(datiRiepilogoResource)
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
    public static DatiRiepilogo createEntity(EntityManager em) {
        DatiRiepilogo datiRiepilogo = new DatiRiepilogo()
            .dRAliquotaIVA(DEFAULT_D_R_ALIQUOTA_IVA)
            .dRNatura(DEFAULT_D_R_NATURA)
            .dRSpeseAccessorie(DEFAULT_D_R_SPESE_ACCESSORIE)
            .dRArrotondamento(DEFAULT_D_R_ARROTONDAMENTO)
            .dRImponibileImporto(DEFAULT_D_R_IMPONIBILE_IMPORTO)
            .dRImposta(DEFAULT_D_R_IMPOSTA)
            .dREsigibilitaIVA(DEFAULT_D_R_ESIGIBILITA_IVA)
            .dRRiferimentoNormativo(DEFAULT_D_R_RIFERIMENTO_NORMATIVO);
        return datiRiepilogo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DatiRiepilogo createUpdatedEntity(EntityManager em) {
        DatiRiepilogo datiRiepilogo = new DatiRiepilogo()
            .dRAliquotaIVA(UPDATED_D_R_ALIQUOTA_IVA)
            .dRNatura(UPDATED_D_R_NATURA)
            .dRSpeseAccessorie(UPDATED_D_R_SPESE_ACCESSORIE)
            .dRArrotondamento(UPDATED_D_R_ARROTONDAMENTO)
            .dRImponibileImporto(UPDATED_D_R_IMPONIBILE_IMPORTO)
            .dRImposta(UPDATED_D_R_IMPOSTA)
            .dREsigibilitaIVA(UPDATED_D_R_ESIGIBILITA_IVA)
            .dRRiferimentoNormativo(UPDATED_D_R_RIFERIMENTO_NORMATIVO);
        return datiRiepilogo;
    }

    @BeforeEach
    public void initTest() {
        datiRiepilogo = createEntity(em);
    }

    @Test
    @Transactional
    public void createDatiRiepilogo() throws Exception {
        int databaseSizeBeforeCreate = datiRiepilogoRepository.findAll().size();

        // Create the DatiRiepilogo
        restDatiRiepilogoMockMvc.perform(post("/api/dati-riepilogos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(datiRiepilogo)))
            .andExpect(status().isCreated());

        // Validate the DatiRiepilogo in the database
        List<DatiRiepilogo> datiRiepilogoList = datiRiepilogoRepository.findAll();
        assertThat(datiRiepilogoList).hasSize(databaseSizeBeforeCreate + 1);
        DatiRiepilogo testDatiRiepilogo = datiRiepilogoList.get(datiRiepilogoList.size() - 1);
        assertThat(testDatiRiepilogo.getdRAliquotaIVA()).isEqualTo(DEFAULT_D_R_ALIQUOTA_IVA);
        assertThat(testDatiRiepilogo.getdRNatura()).isEqualTo(DEFAULT_D_R_NATURA);
        assertThat(testDatiRiepilogo.getdRSpeseAccessorie()).isEqualTo(DEFAULT_D_R_SPESE_ACCESSORIE);
        assertThat(testDatiRiepilogo.getdRArrotondamento()).isEqualTo(DEFAULT_D_R_ARROTONDAMENTO);
        assertThat(testDatiRiepilogo.getdRImponibileImporto()).isEqualTo(DEFAULT_D_R_IMPONIBILE_IMPORTO);
        assertThat(testDatiRiepilogo.getdRImposta()).isEqualTo(DEFAULT_D_R_IMPOSTA);
        assertThat(testDatiRiepilogo.getdREsigibilitaIVA()).isEqualTo(DEFAULT_D_R_ESIGIBILITA_IVA);
        assertThat(testDatiRiepilogo.getdRRiferimentoNormativo()).isEqualTo(DEFAULT_D_R_RIFERIMENTO_NORMATIVO);
    }

    @Test
    @Transactional
    public void createDatiRiepilogoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = datiRiepilogoRepository.findAll().size();

        // Create the DatiRiepilogo with an existing ID
        datiRiepilogo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDatiRiepilogoMockMvc.perform(post("/api/dati-riepilogos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(datiRiepilogo)))
            .andExpect(status().isBadRequest());

        // Validate the DatiRiepilogo in the database
        List<DatiRiepilogo> datiRiepilogoList = datiRiepilogoRepository.findAll();
        assertThat(datiRiepilogoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDatiRiepilogos() throws Exception {
        // Initialize the database
        datiRiepilogoRepository.saveAndFlush(datiRiepilogo);

        // Get all the datiRiepilogoList
        restDatiRiepilogoMockMvc.perform(get("/api/dati-riepilogos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(datiRiepilogo.getId().intValue())))
            .andExpect(jsonPath("$.[*].dRAliquotaIVA").value(hasItem(DEFAULT_D_R_ALIQUOTA_IVA.toString())))
            .andExpect(jsonPath("$.[*].dRNatura").value(hasItem(DEFAULT_D_R_NATURA.toString())))
            .andExpect(jsonPath("$.[*].dRSpeseAccessorie").value(hasItem(DEFAULT_D_R_SPESE_ACCESSORIE.toString())))
            .andExpect(jsonPath("$.[*].dRArrotondamento").value(hasItem(DEFAULT_D_R_ARROTONDAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dRImponibileImporto").value(hasItem(DEFAULT_D_R_IMPONIBILE_IMPORTO.toString())))
            .andExpect(jsonPath("$.[*].dRImposta").value(hasItem(DEFAULT_D_R_IMPOSTA.toString())))
            .andExpect(jsonPath("$.[*].dREsigibilitaIVA").value(hasItem(DEFAULT_D_R_ESIGIBILITA_IVA.toString())))
            .andExpect(jsonPath("$.[*].dRRiferimentoNormativo").value(hasItem(DEFAULT_D_R_RIFERIMENTO_NORMATIVO.toString())));
    }
    
    @Test
    @Transactional
    public void getDatiRiepilogo() throws Exception {
        // Initialize the database
        datiRiepilogoRepository.saveAndFlush(datiRiepilogo);

        // Get the datiRiepilogo
        restDatiRiepilogoMockMvc.perform(get("/api/dati-riepilogos/{id}", datiRiepilogo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(datiRiepilogo.getId().intValue()))
            .andExpect(jsonPath("$.dRAliquotaIVA").value(DEFAULT_D_R_ALIQUOTA_IVA.toString()))
            .andExpect(jsonPath("$.dRNatura").value(DEFAULT_D_R_NATURA.toString()))
            .andExpect(jsonPath("$.dRSpeseAccessorie").value(DEFAULT_D_R_SPESE_ACCESSORIE.toString()))
            .andExpect(jsonPath("$.dRArrotondamento").value(DEFAULT_D_R_ARROTONDAMENTO.toString()))
            .andExpect(jsonPath("$.dRImponibileImporto").value(DEFAULT_D_R_IMPONIBILE_IMPORTO.toString()))
            .andExpect(jsonPath("$.dRImposta").value(DEFAULT_D_R_IMPOSTA.toString()))
            .andExpect(jsonPath("$.dREsigibilitaIVA").value(DEFAULT_D_R_ESIGIBILITA_IVA.toString()))
            .andExpect(jsonPath("$.dRRiferimentoNormativo").value(DEFAULT_D_R_RIFERIMENTO_NORMATIVO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDatiRiepilogo() throws Exception {
        // Get the datiRiepilogo
        restDatiRiepilogoMockMvc.perform(get("/api/dati-riepilogos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDatiRiepilogo() throws Exception {
        // Initialize the database
        datiRiepilogoRepository.saveAndFlush(datiRiepilogo);

        int databaseSizeBeforeUpdate = datiRiepilogoRepository.findAll().size();

        // Update the datiRiepilogo
        DatiRiepilogo updatedDatiRiepilogo = datiRiepilogoRepository.findById(datiRiepilogo.getId()).get();
        // Disconnect from session so that the updates on updatedDatiRiepilogo are not directly saved in db
        em.detach(updatedDatiRiepilogo);
        updatedDatiRiepilogo
            .dRAliquotaIVA(UPDATED_D_R_ALIQUOTA_IVA)
            .dRNatura(UPDATED_D_R_NATURA)
            .dRSpeseAccessorie(UPDATED_D_R_SPESE_ACCESSORIE)
            .dRArrotondamento(UPDATED_D_R_ARROTONDAMENTO)
            .dRImponibileImporto(UPDATED_D_R_IMPONIBILE_IMPORTO)
            .dRImposta(UPDATED_D_R_IMPOSTA)
            .dREsigibilitaIVA(UPDATED_D_R_ESIGIBILITA_IVA)
            .dRRiferimentoNormativo(UPDATED_D_R_RIFERIMENTO_NORMATIVO);

        restDatiRiepilogoMockMvc.perform(put("/api/dati-riepilogos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDatiRiepilogo)))
            .andExpect(status().isOk());

        // Validate the DatiRiepilogo in the database
        List<DatiRiepilogo> datiRiepilogoList = datiRiepilogoRepository.findAll();
        assertThat(datiRiepilogoList).hasSize(databaseSizeBeforeUpdate);
        DatiRiepilogo testDatiRiepilogo = datiRiepilogoList.get(datiRiepilogoList.size() - 1);
        assertThat(testDatiRiepilogo.getdRAliquotaIVA()).isEqualTo(UPDATED_D_R_ALIQUOTA_IVA);
        assertThat(testDatiRiepilogo.getdRNatura()).isEqualTo(UPDATED_D_R_NATURA);
        assertThat(testDatiRiepilogo.getdRSpeseAccessorie()).isEqualTo(UPDATED_D_R_SPESE_ACCESSORIE);
        assertThat(testDatiRiepilogo.getdRArrotondamento()).isEqualTo(UPDATED_D_R_ARROTONDAMENTO);
        assertThat(testDatiRiepilogo.getdRImponibileImporto()).isEqualTo(UPDATED_D_R_IMPONIBILE_IMPORTO);
        assertThat(testDatiRiepilogo.getdRImposta()).isEqualTo(UPDATED_D_R_IMPOSTA);
        assertThat(testDatiRiepilogo.getdREsigibilitaIVA()).isEqualTo(UPDATED_D_R_ESIGIBILITA_IVA);
        assertThat(testDatiRiepilogo.getdRRiferimentoNormativo()).isEqualTo(UPDATED_D_R_RIFERIMENTO_NORMATIVO);
    }

    @Test
    @Transactional
    public void updateNonExistingDatiRiepilogo() throws Exception {
        int databaseSizeBeforeUpdate = datiRiepilogoRepository.findAll().size();

        // Create the DatiRiepilogo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDatiRiepilogoMockMvc.perform(put("/api/dati-riepilogos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(datiRiepilogo)))
            .andExpect(status().isBadRequest());

        // Validate the DatiRiepilogo in the database
        List<DatiRiepilogo> datiRiepilogoList = datiRiepilogoRepository.findAll();
        assertThat(datiRiepilogoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDatiRiepilogo() throws Exception {
        // Initialize the database
        datiRiepilogoRepository.saveAndFlush(datiRiepilogo);

        int databaseSizeBeforeDelete = datiRiepilogoRepository.findAll().size();

        // Delete the datiRiepilogo
        restDatiRiepilogoMockMvc.perform(delete("/api/dati-riepilogos/{id}", datiRiepilogo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<DatiRiepilogo> datiRiepilogoList = datiRiepilogoRepository.findAll();
        assertThat(datiRiepilogoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DatiRiepilogo.class);
        DatiRiepilogo datiRiepilogo1 = new DatiRiepilogo();
        datiRiepilogo1.setId(1L);
        DatiRiepilogo datiRiepilogo2 = new DatiRiepilogo();
        datiRiepilogo2.setId(datiRiepilogo1.getId());
        assertThat(datiRiepilogo1).isEqualTo(datiRiepilogo2);
        datiRiepilogo2.setId(2L);
        assertThat(datiRiepilogo1).isNotEqualTo(datiRiepilogo2);
        datiRiepilogo1.setId(null);
        assertThat(datiRiepilogo1).isNotEqualTo(datiRiepilogo2);
    }
}
