package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.AltriDatiGestionali;
import net.anet.invoice.repo.repository.AltriDatiGestionaliRepository;
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
 * Integration tests for the {@Link AltriDatiGestionaliResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class AltriDatiGestionaliResourceIT {

    private static final String DEFAULT_TIPO_DATO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_DATO = "BBBBBBBBBB";

    private static final String DEFAULT_RIFERIMENTO_TESTO = "AAAAAAAAAA";
    private static final String UPDATED_RIFERIMENTO_TESTO = "BBBBBBBBBB";

    private static final String DEFAULT_RIFERIMENTO_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_RIFERIMENTO_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_RIFERIMENTO_DATA = "AAAAAAAAAA";
    private static final String UPDATED_RIFERIMENTO_DATA = "BBBBBBBBBB";

    @Autowired
    private AltriDatiGestionaliRepository altriDatiGestionaliRepository;

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

    private MockMvc restAltriDatiGestionaliMockMvc;

    private AltriDatiGestionali altriDatiGestionali;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AltriDatiGestionaliResource altriDatiGestionaliResource = new AltriDatiGestionaliResource(altriDatiGestionaliRepository);
        this.restAltriDatiGestionaliMockMvc = MockMvcBuilders.standaloneSetup(altriDatiGestionaliResource)
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
    public static AltriDatiGestionali createEntity(EntityManager em) {
        AltriDatiGestionali altriDatiGestionali = new AltriDatiGestionali()
            .tipoDato(DEFAULT_TIPO_DATO)
            .riferimentoTesto(DEFAULT_RIFERIMENTO_TESTO)
            .riferimentoNumero(DEFAULT_RIFERIMENTO_NUMERO)
            .riferimentoData(DEFAULT_RIFERIMENTO_DATA);
        return altriDatiGestionali;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AltriDatiGestionali createUpdatedEntity(EntityManager em) {
        AltriDatiGestionali altriDatiGestionali = new AltriDatiGestionali()
            .tipoDato(UPDATED_TIPO_DATO)
            .riferimentoTesto(UPDATED_RIFERIMENTO_TESTO)
            .riferimentoNumero(UPDATED_RIFERIMENTO_NUMERO)
            .riferimentoData(UPDATED_RIFERIMENTO_DATA);
        return altriDatiGestionali;
    }

    @BeforeEach
    public void initTest() {
        altriDatiGestionali = createEntity(em);
    }

    @Test
    @Transactional
    public void createAltriDatiGestionali() throws Exception {
        int databaseSizeBeforeCreate = altriDatiGestionaliRepository.findAll().size();

        // Create the AltriDatiGestionali
        restAltriDatiGestionaliMockMvc.perform(post("/api/altri-dati-gestionalis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(altriDatiGestionali)))
            .andExpect(status().isCreated());

        // Validate the AltriDatiGestionali in the database
        List<AltriDatiGestionali> altriDatiGestionaliList = altriDatiGestionaliRepository.findAll();
        assertThat(altriDatiGestionaliList).hasSize(databaseSizeBeforeCreate + 1);
        AltriDatiGestionali testAltriDatiGestionali = altriDatiGestionaliList.get(altriDatiGestionaliList.size() - 1);
        assertThat(testAltriDatiGestionali.getTipoDato()).isEqualTo(DEFAULT_TIPO_DATO);
        assertThat(testAltriDatiGestionali.getRiferimentoTesto()).isEqualTo(DEFAULT_RIFERIMENTO_TESTO);
        assertThat(testAltriDatiGestionali.getRiferimentoNumero()).isEqualTo(DEFAULT_RIFERIMENTO_NUMERO);
        assertThat(testAltriDatiGestionali.getRiferimentoData()).isEqualTo(DEFAULT_RIFERIMENTO_DATA);
    }

    @Test
    @Transactional
    public void createAltriDatiGestionaliWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = altriDatiGestionaliRepository.findAll().size();

        // Create the AltriDatiGestionali with an existing ID
        altriDatiGestionali.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAltriDatiGestionaliMockMvc.perform(post("/api/altri-dati-gestionalis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(altriDatiGestionali)))
            .andExpect(status().isBadRequest());

        // Validate the AltriDatiGestionali in the database
        List<AltriDatiGestionali> altriDatiGestionaliList = altriDatiGestionaliRepository.findAll();
        assertThat(altriDatiGestionaliList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAltriDatiGestionalis() throws Exception {
        // Initialize the database
        altriDatiGestionaliRepository.saveAndFlush(altriDatiGestionali);

        // Get all the altriDatiGestionaliList
        restAltriDatiGestionaliMockMvc.perform(get("/api/altri-dati-gestionalis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(altriDatiGestionali.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoDato").value(hasItem(DEFAULT_TIPO_DATO.toString())))
            .andExpect(jsonPath("$.[*].riferimentoTesto").value(hasItem(DEFAULT_RIFERIMENTO_TESTO.toString())))
            .andExpect(jsonPath("$.[*].riferimentoNumero").value(hasItem(DEFAULT_RIFERIMENTO_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].riferimentoData").value(hasItem(DEFAULT_RIFERIMENTO_DATA.toString())));
    }
    
    @Test
    @Transactional
    public void getAltriDatiGestionali() throws Exception {
        // Initialize the database
        altriDatiGestionaliRepository.saveAndFlush(altriDatiGestionali);

        // Get the altriDatiGestionali
        restAltriDatiGestionaliMockMvc.perform(get("/api/altri-dati-gestionalis/{id}", altriDatiGestionali.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(altriDatiGestionali.getId().intValue()))
            .andExpect(jsonPath("$.tipoDato").value(DEFAULT_TIPO_DATO.toString()))
            .andExpect(jsonPath("$.riferimentoTesto").value(DEFAULT_RIFERIMENTO_TESTO.toString()))
            .andExpect(jsonPath("$.riferimentoNumero").value(DEFAULT_RIFERIMENTO_NUMERO.toString()))
            .andExpect(jsonPath("$.riferimentoData").value(DEFAULT_RIFERIMENTO_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAltriDatiGestionali() throws Exception {
        // Get the altriDatiGestionali
        restAltriDatiGestionaliMockMvc.perform(get("/api/altri-dati-gestionalis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAltriDatiGestionali() throws Exception {
        // Initialize the database
        altriDatiGestionaliRepository.saveAndFlush(altriDatiGestionali);

        int databaseSizeBeforeUpdate = altriDatiGestionaliRepository.findAll().size();

        // Update the altriDatiGestionali
        AltriDatiGestionali updatedAltriDatiGestionali = altriDatiGestionaliRepository.findById(altriDatiGestionali.getId()).get();
        // Disconnect from session so that the updates on updatedAltriDatiGestionali are not directly saved in db
        em.detach(updatedAltriDatiGestionali);
        updatedAltriDatiGestionali
            .tipoDato(UPDATED_TIPO_DATO)
            .riferimentoTesto(UPDATED_RIFERIMENTO_TESTO)
            .riferimentoNumero(UPDATED_RIFERIMENTO_NUMERO)
            .riferimentoData(UPDATED_RIFERIMENTO_DATA);

        restAltriDatiGestionaliMockMvc.perform(put("/api/altri-dati-gestionalis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAltriDatiGestionali)))
            .andExpect(status().isOk());

        // Validate the AltriDatiGestionali in the database
        List<AltriDatiGestionali> altriDatiGestionaliList = altriDatiGestionaliRepository.findAll();
        assertThat(altriDatiGestionaliList).hasSize(databaseSizeBeforeUpdate);
        AltriDatiGestionali testAltriDatiGestionali = altriDatiGestionaliList.get(altriDatiGestionaliList.size() - 1);
        assertThat(testAltriDatiGestionali.getTipoDato()).isEqualTo(UPDATED_TIPO_DATO);
        assertThat(testAltriDatiGestionali.getRiferimentoTesto()).isEqualTo(UPDATED_RIFERIMENTO_TESTO);
        assertThat(testAltriDatiGestionali.getRiferimentoNumero()).isEqualTo(UPDATED_RIFERIMENTO_NUMERO);
        assertThat(testAltriDatiGestionali.getRiferimentoData()).isEqualTo(UPDATED_RIFERIMENTO_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingAltriDatiGestionali() throws Exception {
        int databaseSizeBeforeUpdate = altriDatiGestionaliRepository.findAll().size();

        // Create the AltriDatiGestionali

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAltriDatiGestionaliMockMvc.perform(put("/api/altri-dati-gestionalis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(altriDatiGestionali)))
            .andExpect(status().isBadRequest());

        // Validate the AltriDatiGestionali in the database
        List<AltriDatiGestionali> altriDatiGestionaliList = altriDatiGestionaliRepository.findAll();
        assertThat(altriDatiGestionaliList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAltriDatiGestionali() throws Exception {
        // Initialize the database
        altriDatiGestionaliRepository.saveAndFlush(altriDatiGestionali);

        int databaseSizeBeforeDelete = altriDatiGestionaliRepository.findAll().size();

        // Delete the altriDatiGestionali
        restAltriDatiGestionaliMockMvc.perform(delete("/api/altri-dati-gestionalis/{id}", altriDatiGestionali.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<AltriDatiGestionali> altriDatiGestionaliList = altriDatiGestionaliRepository.findAll();
        assertThat(altriDatiGestionaliList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AltriDatiGestionali.class);
        AltriDatiGestionali altriDatiGestionali1 = new AltriDatiGestionali();
        altriDatiGestionali1.setId(1L);
        AltriDatiGestionali altriDatiGestionali2 = new AltriDatiGestionali();
        altriDatiGestionali2.setId(altriDatiGestionali1.getId());
        assertThat(altriDatiGestionali1).isEqualTo(altriDatiGestionali2);
        altriDatiGestionali2.setId(2L);
        assertThat(altriDatiGestionali1).isNotEqualTo(altriDatiGestionali2);
        altriDatiGestionali1.setId(null);
        assertThat(altriDatiGestionali1).isNotEqualTo(altriDatiGestionali2);
    }
}
