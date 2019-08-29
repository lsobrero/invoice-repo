package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.CassaPrevidenziale;
import net.anet.invoice.repo.repository.CassaPrevidenzialeRepository;
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
 * Integration tests for the {@Link CassaPrevidenzialeResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class CassaPrevidenzialeResourceIT {

    private static final String DEFAULT_TIPO_CASSA = "AAAA";
    private static final String UPDATED_TIPO_CASSA = "BBBB";

    private static final String DEFAULT_AL_CASSA = "AAAAAA";
    private static final String UPDATED_AL_CASSA = "BBBBBB";

    private static final String DEFAULT_IMPORTO_CONTRIBUTO_CASSA = "AAAAAAAAAA";
    private static final String UPDATED_IMPORTO_CONTRIBUTO_CASSA = "BBBBBBBBBB";

    private static final String DEFAULT_IMPONIBILE_CASSA = "AAAAAAAAAA";
    private static final String UPDATED_IMPONIBILE_CASSA = "BBBBBBBBBB";

    private static final String DEFAULT_ALIQUOTA_IVA = "AAAAAA";
    private static final String UPDATED_ALIQUOTA_IVA = "BBBBBB";

    private static final String DEFAULT_RITENUTA = "AA";
    private static final String UPDATED_RITENUTA = "BB";

    private static final String DEFAULT_NATURA = "AA";
    private static final String UPDATED_NATURA = "BB";

    private static final String DEFAULT_RIFERIMENTO_AMMINISTRAZIONE = "AAAAAAAAAA";
    private static final String UPDATED_RIFERIMENTO_AMMINISTRAZIONE = "BBBBBBBBBB";

    @Autowired
    private CassaPrevidenzialeRepository cassaPrevidenzialeRepository;

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

    private MockMvc restCassaPrevidenzialeMockMvc;

    private CassaPrevidenziale cassaPrevidenziale;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CassaPrevidenzialeResource cassaPrevidenzialeResource = new CassaPrevidenzialeResource(cassaPrevidenzialeRepository);
        this.restCassaPrevidenzialeMockMvc = MockMvcBuilders.standaloneSetup(cassaPrevidenzialeResource)
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
    public static CassaPrevidenziale createEntity(EntityManager em) {
        CassaPrevidenziale cassaPrevidenziale = new CassaPrevidenziale()
            .tipoCassa(DEFAULT_TIPO_CASSA)
            .alCassa(DEFAULT_AL_CASSA)
            .importoContributoCassa(DEFAULT_IMPORTO_CONTRIBUTO_CASSA)
            .imponibileCassa(DEFAULT_IMPONIBILE_CASSA)
            .aliquotaIVA(DEFAULT_ALIQUOTA_IVA)
            .ritenuta(DEFAULT_RITENUTA)
            .natura(DEFAULT_NATURA)
            .riferimentoAmministrazione(DEFAULT_RIFERIMENTO_AMMINISTRAZIONE);
        return cassaPrevidenziale;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CassaPrevidenziale createUpdatedEntity(EntityManager em) {
        CassaPrevidenziale cassaPrevidenziale = new CassaPrevidenziale()
            .tipoCassa(UPDATED_TIPO_CASSA)
            .alCassa(UPDATED_AL_CASSA)
            .importoContributoCassa(UPDATED_IMPORTO_CONTRIBUTO_CASSA)
            .imponibileCassa(UPDATED_IMPONIBILE_CASSA)
            .aliquotaIVA(UPDATED_ALIQUOTA_IVA)
            .ritenuta(UPDATED_RITENUTA)
            .natura(UPDATED_NATURA)
            .riferimentoAmministrazione(UPDATED_RIFERIMENTO_AMMINISTRAZIONE);
        return cassaPrevidenziale;
    }

    @BeforeEach
    public void initTest() {
        cassaPrevidenziale = createEntity(em);
    }

    @Test
    @Transactional
    public void createCassaPrevidenziale() throws Exception {
        int databaseSizeBeforeCreate = cassaPrevidenzialeRepository.findAll().size();

        // Create the CassaPrevidenziale
        restCassaPrevidenzialeMockMvc.perform(post("/api/cassa-previdenziales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cassaPrevidenziale)))
            .andExpect(status().isCreated());

        // Validate the CassaPrevidenziale in the database
        List<CassaPrevidenziale> cassaPrevidenzialeList = cassaPrevidenzialeRepository.findAll();
        assertThat(cassaPrevidenzialeList).hasSize(databaseSizeBeforeCreate + 1);
        CassaPrevidenziale testCassaPrevidenziale = cassaPrevidenzialeList.get(cassaPrevidenzialeList.size() - 1);
        assertThat(testCassaPrevidenziale.getTipoCassa()).isEqualTo(DEFAULT_TIPO_CASSA);
        assertThat(testCassaPrevidenziale.getAlCassa()).isEqualTo(DEFAULT_AL_CASSA);
        assertThat(testCassaPrevidenziale.getImportoContributoCassa()).isEqualTo(DEFAULT_IMPORTO_CONTRIBUTO_CASSA);
        assertThat(testCassaPrevidenziale.getImponibileCassa()).isEqualTo(DEFAULT_IMPONIBILE_CASSA);
        assertThat(testCassaPrevidenziale.getAliquotaIVA()).isEqualTo(DEFAULT_ALIQUOTA_IVA);
        assertThat(testCassaPrevidenziale.getRitenuta()).isEqualTo(DEFAULT_RITENUTA);
        assertThat(testCassaPrevidenziale.getNatura()).isEqualTo(DEFAULT_NATURA);
        assertThat(testCassaPrevidenziale.getRiferimentoAmministrazione()).isEqualTo(DEFAULT_RIFERIMENTO_AMMINISTRAZIONE);
    }

    @Test
    @Transactional
    public void createCassaPrevidenzialeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cassaPrevidenzialeRepository.findAll().size();

        // Create the CassaPrevidenziale with an existing ID
        cassaPrevidenziale.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCassaPrevidenzialeMockMvc.perform(post("/api/cassa-previdenziales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cassaPrevidenziale)))
            .andExpect(status().isBadRequest());

        // Validate the CassaPrevidenziale in the database
        List<CassaPrevidenziale> cassaPrevidenzialeList = cassaPrevidenzialeRepository.findAll();
        assertThat(cassaPrevidenzialeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCassaPrevidenziales() throws Exception {
        // Initialize the database
        cassaPrevidenzialeRepository.saveAndFlush(cassaPrevidenziale);

        // Get all the cassaPrevidenzialeList
        restCassaPrevidenzialeMockMvc.perform(get("/api/cassa-previdenziales?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cassaPrevidenziale.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoCassa").value(hasItem(DEFAULT_TIPO_CASSA.toString())))
            .andExpect(jsonPath("$.[*].alCassa").value(hasItem(DEFAULT_AL_CASSA.toString())))
            .andExpect(jsonPath("$.[*].importoContributoCassa").value(hasItem(DEFAULT_IMPORTO_CONTRIBUTO_CASSA.toString())))
            .andExpect(jsonPath("$.[*].imponibileCassa").value(hasItem(DEFAULT_IMPONIBILE_CASSA.toString())))
            .andExpect(jsonPath("$.[*].aliquotaIVA").value(hasItem(DEFAULT_ALIQUOTA_IVA.toString())))
            .andExpect(jsonPath("$.[*].ritenuta").value(hasItem(DEFAULT_RITENUTA.toString())))
            .andExpect(jsonPath("$.[*].natura").value(hasItem(DEFAULT_NATURA.toString())))
            .andExpect(jsonPath("$.[*].riferimentoAmministrazione").value(hasItem(DEFAULT_RIFERIMENTO_AMMINISTRAZIONE.toString())));
    }
    
    @Test
    @Transactional
    public void getCassaPrevidenziale() throws Exception {
        // Initialize the database
        cassaPrevidenzialeRepository.saveAndFlush(cassaPrevidenziale);

        // Get the cassaPrevidenziale
        restCassaPrevidenzialeMockMvc.perform(get("/api/cassa-previdenziales/{id}", cassaPrevidenziale.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cassaPrevidenziale.getId().intValue()))
            .andExpect(jsonPath("$.tipoCassa").value(DEFAULT_TIPO_CASSA.toString()))
            .andExpect(jsonPath("$.alCassa").value(DEFAULT_AL_CASSA.toString()))
            .andExpect(jsonPath("$.importoContributoCassa").value(DEFAULT_IMPORTO_CONTRIBUTO_CASSA.toString()))
            .andExpect(jsonPath("$.imponibileCassa").value(DEFAULT_IMPONIBILE_CASSA.toString()))
            .andExpect(jsonPath("$.aliquotaIVA").value(DEFAULT_ALIQUOTA_IVA.toString()))
            .andExpect(jsonPath("$.ritenuta").value(DEFAULT_RITENUTA.toString()))
            .andExpect(jsonPath("$.natura").value(DEFAULT_NATURA.toString()))
            .andExpect(jsonPath("$.riferimentoAmministrazione").value(DEFAULT_RIFERIMENTO_AMMINISTRAZIONE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCassaPrevidenziale() throws Exception {
        // Get the cassaPrevidenziale
        restCassaPrevidenzialeMockMvc.perform(get("/api/cassa-previdenziales/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCassaPrevidenziale() throws Exception {
        // Initialize the database
        cassaPrevidenzialeRepository.saveAndFlush(cassaPrevidenziale);

        int databaseSizeBeforeUpdate = cassaPrevidenzialeRepository.findAll().size();

        // Update the cassaPrevidenziale
        CassaPrevidenziale updatedCassaPrevidenziale = cassaPrevidenzialeRepository.findById(cassaPrevidenziale.getId()).get();
        // Disconnect from session so that the updates on updatedCassaPrevidenziale are not directly saved in db
        em.detach(updatedCassaPrevidenziale);
        updatedCassaPrevidenziale
            .tipoCassa(UPDATED_TIPO_CASSA)
            .alCassa(UPDATED_AL_CASSA)
            .importoContributoCassa(UPDATED_IMPORTO_CONTRIBUTO_CASSA)
            .imponibileCassa(UPDATED_IMPONIBILE_CASSA)
            .aliquotaIVA(UPDATED_ALIQUOTA_IVA)
            .ritenuta(UPDATED_RITENUTA)
            .natura(UPDATED_NATURA)
            .riferimentoAmministrazione(UPDATED_RIFERIMENTO_AMMINISTRAZIONE);

        restCassaPrevidenzialeMockMvc.perform(put("/api/cassa-previdenziales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCassaPrevidenziale)))
            .andExpect(status().isOk());

        // Validate the CassaPrevidenziale in the database
        List<CassaPrevidenziale> cassaPrevidenzialeList = cassaPrevidenzialeRepository.findAll();
        assertThat(cassaPrevidenzialeList).hasSize(databaseSizeBeforeUpdate);
        CassaPrevidenziale testCassaPrevidenziale = cassaPrevidenzialeList.get(cassaPrevidenzialeList.size() - 1);
        assertThat(testCassaPrevidenziale.getTipoCassa()).isEqualTo(UPDATED_TIPO_CASSA);
        assertThat(testCassaPrevidenziale.getAlCassa()).isEqualTo(UPDATED_AL_CASSA);
        assertThat(testCassaPrevidenziale.getImportoContributoCassa()).isEqualTo(UPDATED_IMPORTO_CONTRIBUTO_CASSA);
        assertThat(testCassaPrevidenziale.getImponibileCassa()).isEqualTo(UPDATED_IMPONIBILE_CASSA);
        assertThat(testCassaPrevidenziale.getAliquotaIVA()).isEqualTo(UPDATED_ALIQUOTA_IVA);
        assertThat(testCassaPrevidenziale.getRitenuta()).isEqualTo(UPDATED_RITENUTA);
        assertThat(testCassaPrevidenziale.getNatura()).isEqualTo(UPDATED_NATURA);
        assertThat(testCassaPrevidenziale.getRiferimentoAmministrazione()).isEqualTo(UPDATED_RIFERIMENTO_AMMINISTRAZIONE);
    }

    @Test
    @Transactional
    public void updateNonExistingCassaPrevidenziale() throws Exception {
        int databaseSizeBeforeUpdate = cassaPrevidenzialeRepository.findAll().size();

        // Create the CassaPrevidenziale

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCassaPrevidenzialeMockMvc.perform(put("/api/cassa-previdenziales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cassaPrevidenziale)))
            .andExpect(status().isBadRequest());

        // Validate the CassaPrevidenziale in the database
        List<CassaPrevidenziale> cassaPrevidenzialeList = cassaPrevidenzialeRepository.findAll();
        assertThat(cassaPrevidenzialeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCassaPrevidenziale() throws Exception {
        // Initialize the database
        cassaPrevidenzialeRepository.saveAndFlush(cassaPrevidenziale);

        int databaseSizeBeforeDelete = cassaPrevidenzialeRepository.findAll().size();

        // Delete the cassaPrevidenziale
        restCassaPrevidenzialeMockMvc.perform(delete("/api/cassa-previdenziales/{id}", cassaPrevidenziale.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<CassaPrevidenziale> cassaPrevidenzialeList = cassaPrevidenzialeRepository.findAll();
        assertThat(cassaPrevidenzialeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CassaPrevidenziale.class);
        CassaPrevidenziale cassaPrevidenziale1 = new CassaPrevidenziale();
        cassaPrevidenziale1.setId(1L);
        CassaPrevidenziale cassaPrevidenziale2 = new CassaPrevidenziale();
        cassaPrevidenziale2.setId(cassaPrevidenziale1.getId());
        assertThat(cassaPrevidenziale1).isEqualTo(cassaPrevidenziale2);
        cassaPrevidenziale2.setId(2L);
        assertThat(cassaPrevidenziale1).isNotEqualTo(cassaPrevidenziale2);
        cassaPrevidenziale1.setId(null);
        assertThat(cassaPrevidenziale1).isNotEqualTo(cassaPrevidenziale2);
    }
}
