package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.DDT;
import net.anet.invoice.repo.repository.DDTRepository;
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
 * Integration tests for the {@Link DDTResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class DDTResourceIT {

    private static final String DEFAULT_NUMERO_DDT = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_DDT = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_DDT = "AAAAAAAAAA";
    private static final String UPDATED_DATA_DDT = "BBBBBBBBBB";

    @Autowired
    private DDTRepository dDTRepository;

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

    private MockMvc restDDTMockMvc;

    private DDT dDT;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DDTResource dDTResource = new DDTResource(dDTRepository);
        this.restDDTMockMvc = MockMvcBuilders.standaloneSetup(dDTResource)
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
    public static DDT createEntity(EntityManager em) {
        DDT dDT = new DDT()
            .numeroDDT(DEFAULT_NUMERO_DDT)
            .dataDDT(DEFAULT_DATA_DDT);
        return dDT;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DDT createUpdatedEntity(EntityManager em) {
        DDT dDT = new DDT()
            .numeroDDT(UPDATED_NUMERO_DDT)
            .dataDDT(UPDATED_DATA_DDT);
        return dDT;
    }

    @BeforeEach
    public void initTest() {
        dDT = createEntity(em);
    }

    @Test
    @Transactional
    public void createDDT() throws Exception {
        int databaseSizeBeforeCreate = dDTRepository.findAll().size();

        // Create the DDT
        restDDTMockMvc.perform(post("/api/ddts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dDT)))
            .andExpect(status().isCreated());

        // Validate the DDT in the database
        List<DDT> dDTList = dDTRepository.findAll();
        assertThat(dDTList).hasSize(databaseSizeBeforeCreate + 1);
        DDT testDDT = dDTList.get(dDTList.size() - 1);
        assertThat(testDDT.getNumeroDDT()).isEqualTo(DEFAULT_NUMERO_DDT);
        assertThat(testDDT.getDataDDT()).isEqualTo(DEFAULT_DATA_DDT);
    }

    @Test
    @Transactional
    public void createDDTWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dDTRepository.findAll().size();

        // Create the DDT with an existing ID
        dDT.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDDTMockMvc.perform(post("/api/ddts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dDT)))
            .andExpect(status().isBadRequest());

        // Validate the DDT in the database
        List<DDT> dDTList = dDTRepository.findAll();
        assertThat(dDTList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDDTS() throws Exception {
        // Initialize the database
        dDTRepository.saveAndFlush(dDT);

        // Get all the dDTList
        restDDTMockMvc.perform(get("/api/ddts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dDT.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroDDT").value(hasItem(DEFAULT_NUMERO_DDT.toString())))
            .andExpect(jsonPath("$.[*].dataDDT").value(hasItem(DEFAULT_DATA_DDT.toString())));
    }
    
    @Test
    @Transactional
    public void getDDT() throws Exception {
        // Initialize the database
        dDTRepository.saveAndFlush(dDT);

        // Get the dDT
        restDDTMockMvc.perform(get("/api/ddts/{id}", dDT.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dDT.getId().intValue()))
            .andExpect(jsonPath("$.numeroDDT").value(DEFAULT_NUMERO_DDT.toString()))
            .andExpect(jsonPath("$.dataDDT").value(DEFAULT_DATA_DDT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDDT() throws Exception {
        // Get the dDT
        restDDTMockMvc.perform(get("/api/ddts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDDT() throws Exception {
        // Initialize the database
        dDTRepository.saveAndFlush(dDT);

        int databaseSizeBeforeUpdate = dDTRepository.findAll().size();

        // Update the dDT
        DDT updatedDDT = dDTRepository.findById(dDT.getId()).get();
        // Disconnect from session so that the updates on updatedDDT are not directly saved in db
        em.detach(updatedDDT);
        updatedDDT
            .numeroDDT(UPDATED_NUMERO_DDT)
            .dataDDT(UPDATED_DATA_DDT);

        restDDTMockMvc.perform(put("/api/ddts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDDT)))
            .andExpect(status().isOk());

        // Validate the DDT in the database
        List<DDT> dDTList = dDTRepository.findAll();
        assertThat(dDTList).hasSize(databaseSizeBeforeUpdate);
        DDT testDDT = dDTList.get(dDTList.size() - 1);
        assertThat(testDDT.getNumeroDDT()).isEqualTo(UPDATED_NUMERO_DDT);
        assertThat(testDDT.getDataDDT()).isEqualTo(UPDATED_DATA_DDT);
    }

    @Test
    @Transactional
    public void updateNonExistingDDT() throws Exception {
        int databaseSizeBeforeUpdate = dDTRepository.findAll().size();

        // Create the DDT

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDDTMockMvc.perform(put("/api/ddts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dDT)))
            .andExpect(status().isBadRequest());

        // Validate the DDT in the database
        List<DDT> dDTList = dDTRepository.findAll();
        assertThat(dDTList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDDT() throws Exception {
        // Initialize the database
        dDTRepository.saveAndFlush(dDT);

        int databaseSizeBeforeDelete = dDTRepository.findAll().size();

        // Delete the dDT
        restDDTMockMvc.perform(delete("/api/ddts/{id}", dDT.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<DDT> dDTList = dDTRepository.findAll();
        assertThat(dDTList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DDT.class);
        DDT dDT1 = new DDT();
        dDT1.setId(1L);
        DDT dDT2 = new DDT();
        dDT2.setId(dDT1.getId());
        assertThat(dDT1).isEqualTo(dDT2);
        dDT2.setId(2L);
        assertThat(dDT1).isNotEqualTo(dDT2);
        dDT1.setId(null);
        assertThat(dDT1).isNotEqualTo(dDT2);
    }
}
