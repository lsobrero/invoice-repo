package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.InvoiceApp;
import net.anet.invoice.repo.domain.FAHeader;
import net.anet.invoice.repo.repository.FAHeaderRepository;
import net.anet.invoice.repo.service.FAHeaderService;
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
 * Integration tests for the {@Link FAHeaderResource} REST controller.
 */
@SpringBootTest(classes = InvoiceApp.class)
public class FAHeaderResourceIT {

    private static final String DEFAULT_TRASMITTENTE_ID_PAESE = "AA";
    private static final String UPDATED_TRASMITTENTE_ID_PAESE = "BB";

    private static final String DEFAULT_TRASMITTENTE_ID_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_TRASMITTENTE_ID_CODICE = "BBBBBBBBBB";

    private static final String DEFAULT_PROGRESSIVO_INVIO = "AAAAAAAAAA";
    private static final String UPDATED_PROGRESSIVO_INVIO = "BBBBBBBBBB";

    private static final String DEFAULT_FORMATO_TRASMISSIONE = "AAAAA";
    private static final String UPDATED_FORMATO_TRASMISSIONE = "BBBBB";

    private static final String DEFAULT_CODICE_DESTINATARIO = "AAAAAAA";
    private static final String UPDATED_CODICE_DESTINATARIO = "BBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_P_EC_DESTINATARIO = "AAAAAAAAAA";
    private static final String UPDATED_P_EC_DESTINATARIO = "BBBBBBBBBB";

    private static final String DEFAULT_C_PDA_ID_PAESE = "AA";
    private static final String UPDATED_C_PDA_ID_PAESE = "BB";

    private static final String DEFAULT_C_PDA_ID_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_C_PDA_ID_CODICE = "BBBBBBBBBB";

    private static final String DEFAULT_C_PDA_CODICE_FISCALE = "AAAAAAAAAA";
    private static final String UPDATED_C_PDA_CODICE_FISCALE = "BBBBBBBBBB";

    private static final String DEFAULT_C_PA_DENOMINAZIONE = "AAAAAAAAAA";
    private static final String UPDATED_C_PA_DENOMINAZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_C_PA_NOME = "AAAAAAAAAA";
    private static final String UPDATED_C_PA_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_C_PA_COGNOME = "AAAAAAAAAA";
    private static final String UPDATED_C_PA_COGNOME = "BBBBBBBBBB";

    private static final String DEFAULT_C_PA_TITOLO = "AAAAAAAAAA";
    private static final String UPDATED_C_PA_TITOLO = "BBBBBBBBBB";

    private static final String DEFAULT_C_PA_COD_EORI = "AAAAAAAAAA";
    private static final String UPDATED_C_PA_COD_EORI = "BBBBBBBBBB";

    private static final String DEFAULT_C_PA_ALBO_PROFESSIONALE = "AAAAAAAAAA";
    private static final String UPDATED_C_PA_ALBO_PROFESSIONALE = "BBBBBBBBBB";

    private static final String DEFAULT_C_PA_PROVINCIA_ALBO = "AA";
    private static final String UPDATED_C_PA_PROVINCIA_ALBO = "BB";

    private static final String DEFAULT_C_PA_NUMERO_ISCRIZIONE_ALBO = "AAAAAAAAAA";
    private static final String UPDATED_C_PA_NUMERO_ISCRIZIONE_ALBO = "BBBBBBBBBB";

    private static final String DEFAULT_C_PA_DATA_ISCRIZIONE_ALBO = "AAAAAAAAAA";
    private static final String UPDATED_C_PA_DATA_ISCRIZIONE_ALBO = "BBBBBBBBBB";

    private static final String DEFAULT_C_PA_REGIME_FISCALE = "AAAA";
    private static final String UPDATED_C_PA_REGIME_FISCALE = "BBBB";

    private static final String DEFAULT_C_PS_INDIRIZZO = "AAAAAAAAAA";
    private static final String UPDATED_C_PS_INDIRIZZO = "BBBBBBBBBB";

    private static final String DEFAULT_C_PS_NUMERO_CIVICO = "AAAAAAAA";
    private static final String UPDATED_C_PS_NUMERO_CIVICO = "BBBBBBBB";

    private static final String DEFAULT_C_PSCAP = "AAAAA";
    private static final String UPDATED_C_PSCAP = "BBBBB";

    private static final String DEFAULT_C_PS_COMUNE = "AAAAAAAAAA";
    private static final String UPDATED_C_PS_COMUNE = "BBBBBBBBBB";

    private static final String DEFAULT_C_PS_PROVINCIA = "AA";
    private static final String UPDATED_C_PS_PROVINCIA = "BB";

    private static final String DEFAULT_C_PS_NAZIONE = "AA";
    private static final String UPDATED_C_PS_NAZIONE = "BB";

    private static final String DEFAULT_S_O_INDIRIZZO = "AAAAAAAAAA";
    private static final String UPDATED_S_O_INDIRIZZO = "BBBBBBBBBB";

    private static final String DEFAULT_S_O_NUMERO_CIVICO = "AAAAAAAA";
    private static final String UPDATED_S_O_NUMERO_CIVICO = "BBBBBBBB";

    private static final String DEFAULT_S_OCAP = "AAAAA";
    private static final String UPDATED_S_OCAP = "BBBBB";

    private static final String DEFAULT_S_O_COMUNE = "AAAAAAAAAA";
    private static final String UPDATED_S_O_COMUNE = "BBBBBBBBBB";

    private static final String DEFAULT_S_O_PROVINCIA = "AA";
    private static final String UPDATED_S_O_PROVINCIA = "BB";

    private static final String DEFAULT_S_O_NAZIONE = "AA";
    private static final String UPDATED_S_O_NAZIONE = "BB";

    private static final String DEFAULT_S_OIREA_UFFICIO = "AA";
    private static final String UPDATED_S_OIREA_UFFICIO = "BB";

    private static final String DEFAULT_S_OIREA_NUMERO_REA = "AAAAAAAAAA";
    private static final String UPDATED_S_OIREA_NUMERO_REA = "BBBBBBBBBB";

    private static final String DEFAULT_S_OIREA_CAPITALE_SOCIALE = "AAAAAAAAAA";
    private static final String UPDATED_S_OIREA_CAPITALE_SOCIALE = "BBBBBBBBBB";

    private static final String DEFAULT_S_OIREA_SOCIO_UNICO = "AA";
    private static final String UPDATED_S_OIREA_SOCIO_UNICO = "BB";

    private static final String DEFAULT_S_OIREA_STATO_LIQUIDAZIONE = "AA";
    private static final String UPDATED_S_OIREA_STATO_LIQUIDAZIONE = "BB";

    private static final String DEFAULT_S_OC_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_S_OC_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_S_OC_FAX = "AAAAAAAAAA";
    private static final String UPDATED_S_OC_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_S_OC_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_S_OC_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_S_OC_RIFERIMENTO_AMMINISTRAZIONE = "AAAAAAAAAA";
    private static final String UPDATED_S_OC_RIFERIMENTO_AMMINISTRAZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_R_FDA_ID_PAESE = "AA";
    private static final String UPDATED_R_FDA_ID_PAESE = "BB";

    private static final String DEFAULT_R_FDA_ID_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_R_FDA_ID_CODICE = "BBBBBBBBBB";

    private static final String DEFAULT_R_FDA_CODICE_FISCALE = "AAAAAAAAAA";
    private static final String UPDATED_R_FDA_CODICE_FISCALE = "BBBBBBBBBB";

    private static final String DEFAULT_R_FA_DENOMINAZIONE = "AAAAAAAAAA";
    private static final String UPDATED_R_FA_DENOMINAZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_R_FA_NOME = "AAAAAAAAAA";
    private static final String UPDATED_R_FA_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_R_FA_COGNOME = "AAAAAAAAAA";
    private static final String UPDATED_R_FA_COGNOME = "BBBBBBBBBB";

    private static final String DEFAULT_R_FA_TITOLO = "AAAAAAAAAA";
    private static final String UPDATED_R_FA_TITOLO = "BBBBBBBBBB";

    private static final String DEFAULT_R_FA_COD_EORI = "AAAAAAAAAA";
    private static final String UPDATED_R_FA_COD_EORI = "BBBBBBBBBB";

    private static final String DEFAULT_C_CDA_ID_PAESE = "AA";
    private static final String UPDATED_C_CDA_ID_PAESE = "BB";

    private static final String DEFAULT_C_CDA_ID_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_C_CDA_ID_CODICE = "BBBBBBBBBB";

    private static final String DEFAULT_C_CDA_CODICE_FISCALE = "AAAAAAAAAA";
    private static final String UPDATED_C_CDA_CODICE_FISCALE = "BBBBBBBBBB";

    private static final String DEFAULT_C_CA_DENOMINAZIONE = "AAAAAAAAAA";
    private static final String UPDATED_C_CA_DENOMINAZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_C_CA_NOME = "AAAAAAAAAA";
    private static final String UPDATED_C_CA_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_C_CA_COGNOME = "AAAAAAAAAA";
    private static final String UPDATED_C_CA_COGNOME = "BBBBBBBBBB";

    private static final String DEFAULT_C_CA_TITOLO = "AAAAAAAAAA";
    private static final String UPDATED_C_CA_TITOLO = "BBBBBBBBBB";

    private static final String DEFAULT_C_CA_COD_EORI = "AAAAAAAAAA";
    private static final String UPDATED_C_CA_COD_EORI = "BBBBBBBBBB";

    private static final String DEFAULT_C_CS_INDIRIZZO = "AAAAAAAAAA";
    private static final String UPDATED_C_CS_INDIRIZZO = "BBBBBBBBBB";

    private static final String DEFAULT_C_CS_NUMERO_CIVICO = "AAAAAAAA";
    private static final String UPDATED_C_CS_NUMERO_CIVICO = "BBBBBBBB";

    private static final String DEFAULT_C_CSCAP = "AAAAA";
    private static final String UPDATED_C_CSCAP = "BBBBB";

    private static final String DEFAULT_C_CS_COMUNE = "AAAAAAAAAA";
    private static final String UPDATED_C_CS_COMUNE = "BBBBBBBBBB";

    private static final String DEFAULT_C_CS_PROVINCIA = "AA";
    private static final String UPDATED_C_CS_PROVINCIA = "BB";

    private static final String DEFAULT_C_CS_NAZIONE = "AA";
    private static final String UPDATED_C_CS_NAZIONE = "BB";

    private static final String DEFAULT_C_CSO_INDIRIZZO = "AAAAAAAAAA";
    private static final String UPDATED_C_CSO_INDIRIZZO = "BBBBBBBBBB";

    private static final String DEFAULT_C_CSO_NUMERO_CIVICO = "AAAAAAAA";
    private static final String UPDATED_C_CSO_NUMERO_CIVICO = "BBBBBBBB";

    private static final String DEFAULT_C_CSOCAP = "AAAAA";
    private static final String UPDATED_C_CSOCAP = "BBBBB";

    private static final String DEFAULT_C_CSO_COMUNE = "AAAAAAAAAA";
    private static final String UPDATED_C_CSO_COMUNE = "BBBBBBBBBB";

    private static final String DEFAULT_C_CSO_PROVINCIA = "AA";
    private static final String UPDATED_C_CSO_PROVINCIA = "BB";

    private static final String DEFAULT_C_CSO_NAZIONE = "AA";
    private static final String UPDATED_C_CSO_NAZIONE = "BB";

    private static final String DEFAULT_C_CRF_ID_PAESE = "AA";
    private static final String UPDATED_C_CRF_ID_PAESE = "BB";

    private static final String DEFAULT_C_CRF_ID_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_C_CRF_ID_CODICE = "BBBBBBBBBB";

    private static final String DEFAULT_C_CRF_DENOMINAZIONE = "AAAAAAAAAA";
    private static final String UPDATED_C_CRF_DENOMINAZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_C_CRF_NOME = "AAAAAAAAAA";
    private static final String UPDATED_C_CRF_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_C_CRF_COGNOME = "AAAAAAAAAA";
    private static final String UPDATED_C_CRF_COGNOME = "BBBBBBBBBB";

    private static final String DEFAULT_T_IDA_ID_PAESE = "AA";
    private static final String UPDATED_T_IDA_ID_PAESE = "BB";

    private static final String DEFAULT_T_IDA_ID_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_T_IDA_ID_CODICE = "BBBBBBBBBB";

    private static final String DEFAULT_T_IDA_CODICE_FISCALE = "AAAAAAAAAA";
    private static final String UPDATED_T_IDA_CODICE_FISCALE = "BBBBBBBBBB";

    private static final String DEFAULT_T_IA_DENOMINAZIONE = "AAAAAAAAAA";
    private static final String UPDATED_T_IA_DENOMINAZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_T_IA_NOME = "AAAAAAAAAA";
    private static final String UPDATED_T_IA_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_T_IA_COGNOME = "AAAAAAAAAA";
    private static final String UPDATED_T_IA_COGNOME = "BBBBBBBBBB";

    private static final String DEFAULT_T_IA_TITOLO = "AAAAAAAAAA";
    private static final String UPDATED_T_IA_TITOLO = "BBBBBBBBBB";

    private static final String DEFAULT_T_IA_COD_EORI = "AAAAAAAAAA";
    private static final String UPDATED_T_IA_COD_EORI = "BBBBBBBBBB";

    private static final String DEFAULT_SOGGETTO_EMITTENTE = "AA";
    private static final String UPDATED_SOGGETTO_EMITTENTE = "BB";

    @Autowired
    private FAHeaderRepository fAHeaderRepository;

    @Autowired
    private FAHeaderService fAHeaderService;

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

    private MockMvc restFAHeaderMockMvc;

    private FAHeader fAHeader;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FAHeaderResource fAHeaderResource = new FAHeaderResource(fAHeaderService);
        this.restFAHeaderMockMvc = MockMvcBuilders.standaloneSetup(fAHeaderResource)
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
    public static FAHeader createEntity(EntityManager em) {
        FAHeader fAHeader = new FAHeader()
            .trasmittenteIdPaese(DEFAULT_TRASMITTENTE_ID_PAESE)
            .trasmittenteIdCodice(DEFAULT_TRASMITTENTE_ID_CODICE)
            .progressivoInvio(DEFAULT_PROGRESSIVO_INVIO)
            .formatoTrasmissione(DEFAULT_FORMATO_TRASMISSIONE)
            .codiceDestinatario(DEFAULT_CODICE_DESTINATARIO)
            .telefono(DEFAULT_TELEFONO)
            .email(DEFAULT_EMAIL)
            .pECDestinatario(DEFAULT_P_EC_DESTINATARIO)
            .cPDAIdPaese(DEFAULT_C_PDA_ID_PAESE)
            .cPDAIdCodice(DEFAULT_C_PDA_ID_CODICE)
            .cPDACodiceFiscale(DEFAULT_C_PDA_CODICE_FISCALE)
            .cPADenominazione(DEFAULT_C_PA_DENOMINAZIONE)
            .cPANome(DEFAULT_C_PA_NOME)
            .cPACognome(DEFAULT_C_PA_COGNOME)
            .cPATitolo(DEFAULT_C_PA_TITOLO)
            .cPACodEORI(DEFAULT_C_PA_COD_EORI)
            .cPAAlboProfessionale(DEFAULT_C_PA_ALBO_PROFESSIONALE)
            .cPAProvinciaAlbo(DEFAULT_C_PA_PROVINCIA_ALBO)
            .cPANumeroIscrizioneAlbo(DEFAULT_C_PA_NUMERO_ISCRIZIONE_ALBO)
            .cPADataIscrizioneAlbo(DEFAULT_C_PA_DATA_ISCRIZIONE_ALBO)
            .cPARegimeFiscale(DEFAULT_C_PA_REGIME_FISCALE)
            .cPSIndirizzo(DEFAULT_C_PS_INDIRIZZO)
            .cPSNumeroCivico(DEFAULT_C_PS_NUMERO_CIVICO)
            .cPSCAP(DEFAULT_C_PSCAP)
            .cPSComune(DEFAULT_C_PS_COMUNE)
            .cPSProvincia(DEFAULT_C_PS_PROVINCIA)
            .cPSNazione(DEFAULT_C_PS_NAZIONE)
            .sOIndirizzo(DEFAULT_S_O_INDIRIZZO)
            .sONumeroCivico(DEFAULT_S_O_NUMERO_CIVICO)
            .sOCAP(DEFAULT_S_OCAP)
            .sOComune(DEFAULT_S_O_COMUNE)
            .sOProvincia(DEFAULT_S_O_PROVINCIA)
            .sONazione(DEFAULT_S_O_NAZIONE)
            .sOIREAUfficio(DEFAULT_S_OIREA_UFFICIO)
            .sOIREANumeroREA(DEFAULT_S_OIREA_NUMERO_REA)
            .sOIREACapitaleSociale(DEFAULT_S_OIREA_CAPITALE_SOCIALE)
            .sOIREASocioUnico(DEFAULT_S_OIREA_SOCIO_UNICO)
            .sOIREAStatoLiquidazione(DEFAULT_S_OIREA_STATO_LIQUIDAZIONE)
            .sOCTelefono(DEFAULT_S_OC_TELEFONO)
            .sOCFax(DEFAULT_S_OC_FAX)
            .sOCEmail(DEFAULT_S_OC_EMAIL)
            .sOCRiferimentoAmministrazione(DEFAULT_S_OC_RIFERIMENTO_AMMINISTRAZIONE)
            .rFDAIdPaese(DEFAULT_R_FDA_ID_PAESE)
            .rFDAIdCodice(DEFAULT_R_FDA_ID_CODICE)
            .rFDACodiceFiscale(DEFAULT_R_FDA_CODICE_FISCALE)
            .rFADenominazione(DEFAULT_R_FA_DENOMINAZIONE)
            .rFANome(DEFAULT_R_FA_NOME)
            .rFACognome(DEFAULT_R_FA_COGNOME)
            .rFATitolo(DEFAULT_R_FA_TITOLO)
            .rFACodEORI(DEFAULT_R_FA_COD_EORI)
            .cCDAIdPaese(DEFAULT_C_CDA_ID_PAESE)
            .cCDAIdCodice(DEFAULT_C_CDA_ID_CODICE)
            .cCDACodiceFiscale(DEFAULT_C_CDA_CODICE_FISCALE)
            .cCADenominazione(DEFAULT_C_CA_DENOMINAZIONE)
            .cCANome(DEFAULT_C_CA_NOME)
            .cCACognome(DEFAULT_C_CA_COGNOME)
            .cCATitolo(DEFAULT_C_CA_TITOLO)
            .cCACodEORI(DEFAULT_C_CA_COD_EORI)
            .cCSIndirizzo(DEFAULT_C_CS_INDIRIZZO)
            .cCSNumeroCivico(DEFAULT_C_CS_NUMERO_CIVICO)
            .cCSCAP(DEFAULT_C_CSCAP)
            .cCSComune(DEFAULT_C_CS_COMUNE)
            .cCSProvincia(DEFAULT_C_CS_PROVINCIA)
            .cCSNazione(DEFAULT_C_CS_NAZIONE)
            .cCSOIndirizzo(DEFAULT_C_CSO_INDIRIZZO)
            .cCSONumeroCivico(DEFAULT_C_CSO_NUMERO_CIVICO)
            .cCSOCAP(DEFAULT_C_CSOCAP)
            .cCSOComune(DEFAULT_C_CSO_COMUNE)
            .cCSOProvincia(DEFAULT_C_CSO_PROVINCIA)
            .cCSONazione(DEFAULT_C_CSO_NAZIONE)
            .cCRFIdPaese(DEFAULT_C_CRF_ID_PAESE)
            .cCRFIdCodice(DEFAULT_C_CRF_ID_CODICE)
            .cCRFDenominazione(DEFAULT_C_CRF_DENOMINAZIONE)
            .cCRFNome(DEFAULT_C_CRF_NOME)
            .cCRFCognome(DEFAULT_C_CRF_COGNOME)
            .tIDAIdPaese(DEFAULT_T_IDA_ID_PAESE)
            .tIDAIdCodice(DEFAULT_T_IDA_ID_CODICE)
            .tIDACodiceFiscale(DEFAULT_T_IDA_CODICE_FISCALE)
            .tIADenominazione(DEFAULT_T_IA_DENOMINAZIONE)
            .tIANome(DEFAULT_T_IA_NOME)
            .tIACognome(DEFAULT_T_IA_COGNOME)
            .tIATitolo(DEFAULT_T_IA_TITOLO)
            .tIACodEORI(DEFAULT_T_IA_COD_EORI)
            .soggettoEmittente(DEFAULT_SOGGETTO_EMITTENTE);
        return fAHeader;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FAHeader createUpdatedEntity(EntityManager em) {
        FAHeader fAHeader = new FAHeader()
            .trasmittenteIdPaese(UPDATED_TRASMITTENTE_ID_PAESE)
            .trasmittenteIdCodice(UPDATED_TRASMITTENTE_ID_CODICE)
            .progressivoInvio(UPDATED_PROGRESSIVO_INVIO)
            .formatoTrasmissione(UPDATED_FORMATO_TRASMISSIONE)
            .codiceDestinatario(UPDATED_CODICE_DESTINATARIO)
            .telefono(UPDATED_TELEFONO)
            .email(UPDATED_EMAIL)
            .pECDestinatario(UPDATED_P_EC_DESTINATARIO)
            .cPDAIdPaese(UPDATED_C_PDA_ID_PAESE)
            .cPDAIdCodice(UPDATED_C_PDA_ID_CODICE)
            .cPDACodiceFiscale(UPDATED_C_PDA_CODICE_FISCALE)
            .cPADenominazione(UPDATED_C_PA_DENOMINAZIONE)
            .cPANome(UPDATED_C_PA_NOME)
            .cPACognome(UPDATED_C_PA_COGNOME)
            .cPATitolo(UPDATED_C_PA_TITOLO)
            .cPACodEORI(UPDATED_C_PA_COD_EORI)
            .cPAAlboProfessionale(UPDATED_C_PA_ALBO_PROFESSIONALE)
            .cPAProvinciaAlbo(UPDATED_C_PA_PROVINCIA_ALBO)
            .cPANumeroIscrizioneAlbo(UPDATED_C_PA_NUMERO_ISCRIZIONE_ALBO)
            .cPADataIscrizioneAlbo(UPDATED_C_PA_DATA_ISCRIZIONE_ALBO)
            .cPARegimeFiscale(UPDATED_C_PA_REGIME_FISCALE)
            .cPSIndirizzo(UPDATED_C_PS_INDIRIZZO)
            .cPSNumeroCivico(UPDATED_C_PS_NUMERO_CIVICO)
            .cPSCAP(UPDATED_C_PSCAP)
            .cPSComune(UPDATED_C_PS_COMUNE)
            .cPSProvincia(UPDATED_C_PS_PROVINCIA)
            .cPSNazione(UPDATED_C_PS_NAZIONE)
            .sOIndirizzo(UPDATED_S_O_INDIRIZZO)
            .sONumeroCivico(UPDATED_S_O_NUMERO_CIVICO)
            .sOCAP(UPDATED_S_OCAP)
            .sOComune(UPDATED_S_O_COMUNE)
            .sOProvincia(UPDATED_S_O_PROVINCIA)
            .sONazione(UPDATED_S_O_NAZIONE)
            .sOIREAUfficio(UPDATED_S_OIREA_UFFICIO)
            .sOIREANumeroREA(UPDATED_S_OIREA_NUMERO_REA)
            .sOIREACapitaleSociale(UPDATED_S_OIREA_CAPITALE_SOCIALE)
            .sOIREASocioUnico(UPDATED_S_OIREA_SOCIO_UNICO)
            .sOIREAStatoLiquidazione(UPDATED_S_OIREA_STATO_LIQUIDAZIONE)
            .sOCTelefono(UPDATED_S_OC_TELEFONO)
            .sOCFax(UPDATED_S_OC_FAX)
            .sOCEmail(UPDATED_S_OC_EMAIL)
            .sOCRiferimentoAmministrazione(UPDATED_S_OC_RIFERIMENTO_AMMINISTRAZIONE)
            .rFDAIdPaese(UPDATED_R_FDA_ID_PAESE)
            .rFDAIdCodice(UPDATED_R_FDA_ID_CODICE)
            .rFDACodiceFiscale(UPDATED_R_FDA_CODICE_FISCALE)
            .rFADenominazione(UPDATED_R_FA_DENOMINAZIONE)
            .rFANome(UPDATED_R_FA_NOME)
            .rFACognome(UPDATED_R_FA_COGNOME)
            .rFATitolo(UPDATED_R_FA_TITOLO)
            .rFACodEORI(UPDATED_R_FA_COD_EORI)
            .cCDAIdPaese(UPDATED_C_CDA_ID_PAESE)
            .cCDAIdCodice(UPDATED_C_CDA_ID_CODICE)
            .cCDACodiceFiscale(UPDATED_C_CDA_CODICE_FISCALE)
            .cCADenominazione(UPDATED_C_CA_DENOMINAZIONE)
            .cCANome(UPDATED_C_CA_NOME)
            .cCACognome(UPDATED_C_CA_COGNOME)
            .cCATitolo(UPDATED_C_CA_TITOLO)
            .cCACodEORI(UPDATED_C_CA_COD_EORI)
            .cCSIndirizzo(UPDATED_C_CS_INDIRIZZO)
            .cCSNumeroCivico(UPDATED_C_CS_NUMERO_CIVICO)
            .cCSCAP(UPDATED_C_CSCAP)
            .cCSComune(UPDATED_C_CS_COMUNE)
            .cCSProvincia(UPDATED_C_CS_PROVINCIA)
            .cCSNazione(UPDATED_C_CS_NAZIONE)
            .cCSOIndirizzo(UPDATED_C_CSO_INDIRIZZO)
            .cCSONumeroCivico(UPDATED_C_CSO_NUMERO_CIVICO)
            .cCSOCAP(UPDATED_C_CSOCAP)
            .cCSOComune(UPDATED_C_CSO_COMUNE)
            .cCSOProvincia(UPDATED_C_CSO_PROVINCIA)
            .cCSONazione(UPDATED_C_CSO_NAZIONE)
            .cCRFIdPaese(UPDATED_C_CRF_ID_PAESE)
            .cCRFIdCodice(UPDATED_C_CRF_ID_CODICE)
            .cCRFDenominazione(UPDATED_C_CRF_DENOMINAZIONE)
            .cCRFNome(UPDATED_C_CRF_NOME)
            .cCRFCognome(UPDATED_C_CRF_COGNOME)
            .tIDAIdPaese(UPDATED_T_IDA_ID_PAESE)
            .tIDAIdCodice(UPDATED_T_IDA_ID_CODICE)
            .tIDACodiceFiscale(UPDATED_T_IDA_CODICE_FISCALE)
            .tIADenominazione(UPDATED_T_IA_DENOMINAZIONE)
            .tIANome(UPDATED_T_IA_NOME)
            .tIACognome(UPDATED_T_IA_COGNOME)
            .tIATitolo(UPDATED_T_IA_TITOLO)
            .tIACodEORI(UPDATED_T_IA_COD_EORI)
            .soggettoEmittente(UPDATED_SOGGETTO_EMITTENTE);
        return fAHeader;
    }

    @BeforeEach
    public void initTest() {
        fAHeader = createEntity(em);
    }

    @Test
    @Transactional
    public void createFAHeader() throws Exception {
        int databaseSizeBeforeCreate = fAHeaderRepository.findAll().size();

        // Create the FAHeader
        restFAHeaderMockMvc.perform(post("/api/fa-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fAHeader)))
            .andExpect(status().isCreated());

        // Validate the FAHeader in the database
        List<FAHeader> fAHeaderList = fAHeaderRepository.findAll();
        assertThat(fAHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        FAHeader testFAHeader = fAHeaderList.get(fAHeaderList.size() - 1);
        assertThat(testFAHeader.getTrasmittenteIdPaese()).isEqualTo(DEFAULT_TRASMITTENTE_ID_PAESE);
        assertThat(testFAHeader.getTrasmittenteIdCodice()).isEqualTo(DEFAULT_TRASMITTENTE_ID_CODICE);
        assertThat(testFAHeader.getProgressivoInvio()).isEqualTo(DEFAULT_PROGRESSIVO_INVIO);
        assertThat(testFAHeader.getFormatoTrasmissione()).isEqualTo(DEFAULT_FORMATO_TRASMISSIONE);
        assertThat(testFAHeader.getCodiceDestinatario()).isEqualTo(DEFAULT_CODICE_DESTINATARIO);
        assertThat(testFAHeader.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testFAHeader.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testFAHeader.getpECDestinatario()).isEqualTo(DEFAULT_P_EC_DESTINATARIO);
        assertThat(testFAHeader.getcPDAIdPaese()).isEqualTo(DEFAULT_C_PDA_ID_PAESE);
        assertThat(testFAHeader.getcPDAIdCodice()).isEqualTo(DEFAULT_C_PDA_ID_CODICE);
        assertThat(testFAHeader.getcPDACodiceFiscale()).isEqualTo(DEFAULT_C_PDA_CODICE_FISCALE);
        assertThat(testFAHeader.getcPADenominazione()).isEqualTo(DEFAULT_C_PA_DENOMINAZIONE);
        assertThat(testFAHeader.getcPANome()).isEqualTo(DEFAULT_C_PA_NOME);
        assertThat(testFAHeader.getcPACognome()).isEqualTo(DEFAULT_C_PA_COGNOME);
        assertThat(testFAHeader.getcPATitolo()).isEqualTo(DEFAULT_C_PA_TITOLO);
        assertThat(testFAHeader.getcPACodEORI()).isEqualTo(DEFAULT_C_PA_COD_EORI);
        assertThat(testFAHeader.getcPAAlboProfessionale()).isEqualTo(DEFAULT_C_PA_ALBO_PROFESSIONALE);
        assertThat(testFAHeader.getcPAProvinciaAlbo()).isEqualTo(DEFAULT_C_PA_PROVINCIA_ALBO);
        assertThat(testFAHeader.getcPANumeroIscrizioneAlbo()).isEqualTo(DEFAULT_C_PA_NUMERO_ISCRIZIONE_ALBO);
        assertThat(testFAHeader.getcPADataIscrizioneAlbo()).isEqualTo(DEFAULT_C_PA_DATA_ISCRIZIONE_ALBO);
        assertThat(testFAHeader.getcPARegimeFiscale()).isEqualTo(DEFAULT_C_PA_REGIME_FISCALE);
        assertThat(testFAHeader.getcPSIndirizzo()).isEqualTo(DEFAULT_C_PS_INDIRIZZO);
        assertThat(testFAHeader.getcPSNumeroCivico()).isEqualTo(DEFAULT_C_PS_NUMERO_CIVICO);
        assertThat(testFAHeader.getcPSCAP()).isEqualTo(DEFAULT_C_PSCAP);
        assertThat(testFAHeader.getcPSComune()).isEqualTo(DEFAULT_C_PS_COMUNE);
        assertThat(testFAHeader.getcPSProvincia()).isEqualTo(DEFAULT_C_PS_PROVINCIA);
        assertThat(testFAHeader.getcPSNazione()).isEqualTo(DEFAULT_C_PS_NAZIONE);
        assertThat(testFAHeader.getsOIndirizzo()).isEqualTo(DEFAULT_S_O_INDIRIZZO);
        assertThat(testFAHeader.getsONumeroCivico()).isEqualTo(DEFAULT_S_O_NUMERO_CIVICO);
        assertThat(testFAHeader.getsOCAP()).isEqualTo(DEFAULT_S_OCAP);
        assertThat(testFAHeader.getsOComune()).isEqualTo(DEFAULT_S_O_COMUNE);
        assertThat(testFAHeader.getsOProvincia()).isEqualTo(DEFAULT_S_O_PROVINCIA);
        assertThat(testFAHeader.getsONazione()).isEqualTo(DEFAULT_S_O_NAZIONE);
        assertThat(testFAHeader.getsOIREAUfficio()).isEqualTo(DEFAULT_S_OIREA_UFFICIO);
        assertThat(testFAHeader.getsOIREANumeroREA()).isEqualTo(DEFAULT_S_OIREA_NUMERO_REA);
        assertThat(testFAHeader.getsOIREACapitaleSociale()).isEqualTo(DEFAULT_S_OIREA_CAPITALE_SOCIALE);
        assertThat(testFAHeader.getsOIREASocioUnico()).isEqualTo(DEFAULT_S_OIREA_SOCIO_UNICO);
        assertThat(testFAHeader.getsOIREAStatoLiquidazione()).isEqualTo(DEFAULT_S_OIREA_STATO_LIQUIDAZIONE);
        assertThat(testFAHeader.getsOCTelefono()).isEqualTo(DEFAULT_S_OC_TELEFONO);
        assertThat(testFAHeader.getsOCFax()).isEqualTo(DEFAULT_S_OC_FAX);
        assertThat(testFAHeader.getsOCEmail()).isEqualTo(DEFAULT_S_OC_EMAIL);
        assertThat(testFAHeader.getsOCRiferimentoAmministrazione()).isEqualTo(DEFAULT_S_OC_RIFERIMENTO_AMMINISTRAZIONE);
        assertThat(testFAHeader.getrFDAIdPaese()).isEqualTo(DEFAULT_R_FDA_ID_PAESE);
        assertThat(testFAHeader.getrFDAIdCodice()).isEqualTo(DEFAULT_R_FDA_ID_CODICE);
        assertThat(testFAHeader.getrFDACodiceFiscale()).isEqualTo(DEFAULT_R_FDA_CODICE_FISCALE);
        assertThat(testFAHeader.getrFADenominazione()).isEqualTo(DEFAULT_R_FA_DENOMINAZIONE);
        assertThat(testFAHeader.getrFANome()).isEqualTo(DEFAULT_R_FA_NOME);
        assertThat(testFAHeader.getrFACognome()).isEqualTo(DEFAULT_R_FA_COGNOME);
        assertThat(testFAHeader.getrFATitolo()).isEqualTo(DEFAULT_R_FA_TITOLO);
        assertThat(testFAHeader.getrFACodEORI()).isEqualTo(DEFAULT_R_FA_COD_EORI);
        assertThat(testFAHeader.getcCDAIdPaese()).isEqualTo(DEFAULT_C_CDA_ID_PAESE);
        assertThat(testFAHeader.getcCDAIdCodice()).isEqualTo(DEFAULT_C_CDA_ID_CODICE);
        assertThat(testFAHeader.getcCDACodiceFiscale()).isEqualTo(DEFAULT_C_CDA_CODICE_FISCALE);
        assertThat(testFAHeader.getcCADenominazione()).isEqualTo(DEFAULT_C_CA_DENOMINAZIONE);
        assertThat(testFAHeader.getcCANome()).isEqualTo(DEFAULT_C_CA_NOME);
        assertThat(testFAHeader.getcCACognome()).isEqualTo(DEFAULT_C_CA_COGNOME);
        assertThat(testFAHeader.getcCATitolo()).isEqualTo(DEFAULT_C_CA_TITOLO);
        assertThat(testFAHeader.getcCACodEORI()).isEqualTo(DEFAULT_C_CA_COD_EORI);
        assertThat(testFAHeader.getcCSIndirizzo()).isEqualTo(DEFAULT_C_CS_INDIRIZZO);
        assertThat(testFAHeader.getcCSNumeroCivico()).isEqualTo(DEFAULT_C_CS_NUMERO_CIVICO);
        assertThat(testFAHeader.getcCSCAP()).isEqualTo(DEFAULT_C_CSCAP);
        assertThat(testFAHeader.getcCSComune()).isEqualTo(DEFAULT_C_CS_COMUNE);
        assertThat(testFAHeader.getcCSProvincia()).isEqualTo(DEFAULT_C_CS_PROVINCIA);
        assertThat(testFAHeader.getcCSNazione()).isEqualTo(DEFAULT_C_CS_NAZIONE);
        assertThat(testFAHeader.getcCSOIndirizzo()).isEqualTo(DEFAULT_C_CSO_INDIRIZZO);
        assertThat(testFAHeader.getcCSONumeroCivico()).isEqualTo(DEFAULT_C_CSO_NUMERO_CIVICO);
        assertThat(testFAHeader.getcCSOCAP()).isEqualTo(DEFAULT_C_CSOCAP);
        assertThat(testFAHeader.getcCSOComune()).isEqualTo(DEFAULT_C_CSO_COMUNE);
        assertThat(testFAHeader.getcCSOProvincia()).isEqualTo(DEFAULT_C_CSO_PROVINCIA);
        assertThat(testFAHeader.getcCSONazione()).isEqualTo(DEFAULT_C_CSO_NAZIONE);
        assertThat(testFAHeader.getcCRFIdPaese()).isEqualTo(DEFAULT_C_CRF_ID_PAESE);
        assertThat(testFAHeader.getcCRFIdCodice()).isEqualTo(DEFAULT_C_CRF_ID_CODICE);
        assertThat(testFAHeader.getcCRFDenominazione()).isEqualTo(DEFAULT_C_CRF_DENOMINAZIONE);
        assertThat(testFAHeader.getcCRFNome()).isEqualTo(DEFAULT_C_CRF_NOME);
        assertThat(testFAHeader.getcCRFCognome()).isEqualTo(DEFAULT_C_CRF_COGNOME);
        assertThat(testFAHeader.gettIDAIdPaese()).isEqualTo(DEFAULT_T_IDA_ID_PAESE);
        assertThat(testFAHeader.gettIDAIdCodice()).isEqualTo(DEFAULT_T_IDA_ID_CODICE);
        assertThat(testFAHeader.gettIDACodiceFiscale()).isEqualTo(DEFAULT_T_IDA_CODICE_FISCALE);
        assertThat(testFAHeader.gettIADenominazione()).isEqualTo(DEFAULT_T_IA_DENOMINAZIONE);
        assertThat(testFAHeader.gettIANome()).isEqualTo(DEFAULT_T_IA_NOME);
        assertThat(testFAHeader.gettIACognome()).isEqualTo(DEFAULT_T_IA_COGNOME);
        assertThat(testFAHeader.gettIATitolo()).isEqualTo(DEFAULT_T_IA_TITOLO);
        assertThat(testFAHeader.gettIACodEORI()).isEqualTo(DEFAULT_T_IA_COD_EORI);
        assertThat(testFAHeader.getSoggettoEmittente()).isEqualTo(DEFAULT_SOGGETTO_EMITTENTE);
    }

    @Test
    @Transactional
    public void createFAHeaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fAHeaderRepository.findAll().size();

        // Create the FAHeader with an existing ID
        fAHeader.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFAHeaderMockMvc.perform(post("/api/fa-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fAHeader)))
            .andExpect(status().isBadRequest());

        // Validate the FAHeader in the database
        List<FAHeader> fAHeaderList = fAHeaderRepository.findAll();
        assertThat(fAHeaderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFAHeaders() throws Exception {
        // Initialize the database
        fAHeaderRepository.saveAndFlush(fAHeader);

        // Get all the fAHeaderList
        restFAHeaderMockMvc.perform(get("/api/fa-headers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fAHeader.getId().intValue())))
            .andExpect(jsonPath("$.[*].trasmittenteIdPaese").value(hasItem(DEFAULT_TRASMITTENTE_ID_PAESE.toString())))
            .andExpect(jsonPath("$.[*].trasmittenteIdCodice").value(hasItem(DEFAULT_TRASMITTENTE_ID_CODICE.toString())))
            .andExpect(jsonPath("$.[*].progressivoInvio").value(hasItem(DEFAULT_PROGRESSIVO_INVIO.toString())))
            .andExpect(jsonPath("$.[*].formatoTrasmissione").value(hasItem(DEFAULT_FORMATO_TRASMISSIONE.toString())))
            .andExpect(jsonPath("$.[*].codiceDestinatario").value(hasItem(DEFAULT_CODICE_DESTINATARIO.toString())))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].pECDestinatario").value(hasItem(DEFAULT_P_EC_DESTINATARIO.toString())))
            .andExpect(jsonPath("$.[*].cPDAIdPaese").value(hasItem(DEFAULT_C_PDA_ID_PAESE.toString())))
            .andExpect(jsonPath("$.[*].cPDAIdCodice").value(hasItem(DEFAULT_C_PDA_ID_CODICE.toString())))
            .andExpect(jsonPath("$.[*].cPDACodiceFiscale").value(hasItem(DEFAULT_C_PDA_CODICE_FISCALE.toString())))
            .andExpect(jsonPath("$.[*].cPADenominazione").value(hasItem(DEFAULT_C_PA_DENOMINAZIONE.toString())))
            .andExpect(jsonPath("$.[*].cPANome").value(hasItem(DEFAULT_C_PA_NOME.toString())))
            .andExpect(jsonPath("$.[*].cPACognome").value(hasItem(DEFAULT_C_PA_COGNOME.toString())))
            .andExpect(jsonPath("$.[*].cPATitolo").value(hasItem(DEFAULT_C_PA_TITOLO.toString())))
            .andExpect(jsonPath("$.[*].cPACodEORI").value(hasItem(DEFAULT_C_PA_COD_EORI.toString())))
            .andExpect(jsonPath("$.[*].cPAAlboProfessionale").value(hasItem(DEFAULT_C_PA_ALBO_PROFESSIONALE.toString())))
            .andExpect(jsonPath("$.[*].cPAProvinciaAlbo").value(hasItem(DEFAULT_C_PA_PROVINCIA_ALBO.toString())))
            .andExpect(jsonPath("$.[*].cPANumeroIscrizioneAlbo").value(hasItem(DEFAULT_C_PA_NUMERO_ISCRIZIONE_ALBO.toString())))
            .andExpect(jsonPath("$.[*].cPADataIscrizioneAlbo").value(hasItem(DEFAULT_C_PA_DATA_ISCRIZIONE_ALBO.toString())))
            .andExpect(jsonPath("$.[*].cPARegimeFiscale").value(hasItem(DEFAULT_C_PA_REGIME_FISCALE.toString())))
            .andExpect(jsonPath("$.[*].cPSIndirizzo").value(hasItem(DEFAULT_C_PS_INDIRIZZO.toString())))
            .andExpect(jsonPath("$.[*].cPSNumeroCivico").value(hasItem(DEFAULT_C_PS_NUMERO_CIVICO.toString())))
            .andExpect(jsonPath("$.[*].cPSCAP").value(hasItem(DEFAULT_C_PSCAP.toString())))
            .andExpect(jsonPath("$.[*].cPSComune").value(hasItem(DEFAULT_C_PS_COMUNE.toString())))
            .andExpect(jsonPath("$.[*].cPSProvincia").value(hasItem(DEFAULT_C_PS_PROVINCIA.toString())))
            .andExpect(jsonPath("$.[*].cPSNazione").value(hasItem(DEFAULT_C_PS_NAZIONE.toString())))
            .andExpect(jsonPath("$.[*].sOIndirizzo").value(hasItem(DEFAULT_S_O_INDIRIZZO.toString())))
            .andExpect(jsonPath("$.[*].sONumeroCivico").value(hasItem(DEFAULT_S_O_NUMERO_CIVICO.toString())))
            .andExpect(jsonPath("$.[*].sOCAP").value(hasItem(DEFAULT_S_OCAP.toString())))
            .andExpect(jsonPath("$.[*].sOComune").value(hasItem(DEFAULT_S_O_COMUNE.toString())))
            .andExpect(jsonPath("$.[*].sOProvincia").value(hasItem(DEFAULT_S_O_PROVINCIA.toString())))
            .andExpect(jsonPath("$.[*].sONazione").value(hasItem(DEFAULT_S_O_NAZIONE.toString())))
            .andExpect(jsonPath("$.[*].sOIREAUfficio").value(hasItem(DEFAULT_S_OIREA_UFFICIO.toString())))
            .andExpect(jsonPath("$.[*].sOIREANumeroREA").value(hasItem(DEFAULT_S_OIREA_NUMERO_REA.toString())))
            .andExpect(jsonPath("$.[*].sOIREACapitaleSociale").value(hasItem(DEFAULT_S_OIREA_CAPITALE_SOCIALE.toString())))
            .andExpect(jsonPath("$.[*].sOIREASocioUnico").value(hasItem(DEFAULT_S_OIREA_SOCIO_UNICO.toString())))
            .andExpect(jsonPath("$.[*].sOIREAStatoLiquidazione").value(hasItem(DEFAULT_S_OIREA_STATO_LIQUIDAZIONE.toString())))
            .andExpect(jsonPath("$.[*].sOCTelefono").value(hasItem(DEFAULT_S_OC_TELEFONO.toString())))
            .andExpect(jsonPath("$.[*].sOCFax").value(hasItem(DEFAULT_S_OC_FAX.toString())))
            .andExpect(jsonPath("$.[*].sOCEmail").value(hasItem(DEFAULT_S_OC_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].sOCRiferimentoAmministrazione").value(hasItem(DEFAULT_S_OC_RIFERIMENTO_AMMINISTRAZIONE.toString())))
            .andExpect(jsonPath("$.[*].rFDAIdPaese").value(hasItem(DEFAULT_R_FDA_ID_PAESE.toString())))
            .andExpect(jsonPath("$.[*].rFDAIdCodice").value(hasItem(DEFAULT_R_FDA_ID_CODICE.toString())))
            .andExpect(jsonPath("$.[*].rFDACodiceFiscale").value(hasItem(DEFAULT_R_FDA_CODICE_FISCALE.toString())))
            .andExpect(jsonPath("$.[*].rFADenominazione").value(hasItem(DEFAULT_R_FA_DENOMINAZIONE.toString())))
            .andExpect(jsonPath("$.[*].rFANome").value(hasItem(DEFAULT_R_FA_NOME.toString())))
            .andExpect(jsonPath("$.[*].rFACognome").value(hasItem(DEFAULT_R_FA_COGNOME.toString())))
            .andExpect(jsonPath("$.[*].rFATitolo").value(hasItem(DEFAULT_R_FA_TITOLO.toString())))
            .andExpect(jsonPath("$.[*].rFACodEORI").value(hasItem(DEFAULT_R_FA_COD_EORI.toString())))
            .andExpect(jsonPath("$.[*].cCDAIdPaese").value(hasItem(DEFAULT_C_CDA_ID_PAESE.toString())))
            .andExpect(jsonPath("$.[*].cCDAIdCodice").value(hasItem(DEFAULT_C_CDA_ID_CODICE.toString())))
            .andExpect(jsonPath("$.[*].cCDACodiceFiscale").value(hasItem(DEFAULT_C_CDA_CODICE_FISCALE.toString())))
            .andExpect(jsonPath("$.[*].cCADenominazione").value(hasItem(DEFAULT_C_CA_DENOMINAZIONE.toString())))
            .andExpect(jsonPath("$.[*].cCANome").value(hasItem(DEFAULT_C_CA_NOME.toString())))
            .andExpect(jsonPath("$.[*].cCACognome").value(hasItem(DEFAULT_C_CA_COGNOME.toString())))
            .andExpect(jsonPath("$.[*].cCATitolo").value(hasItem(DEFAULT_C_CA_TITOLO.toString())))
            .andExpect(jsonPath("$.[*].cCACodEORI").value(hasItem(DEFAULT_C_CA_COD_EORI.toString())))
            .andExpect(jsonPath("$.[*].cCSIndirizzo").value(hasItem(DEFAULT_C_CS_INDIRIZZO.toString())))
            .andExpect(jsonPath("$.[*].cCSNumeroCivico").value(hasItem(DEFAULT_C_CS_NUMERO_CIVICO.toString())))
            .andExpect(jsonPath("$.[*].cCSCAP").value(hasItem(DEFAULT_C_CSCAP.toString())))
            .andExpect(jsonPath("$.[*].cCSComune").value(hasItem(DEFAULT_C_CS_COMUNE.toString())))
            .andExpect(jsonPath("$.[*].cCSProvincia").value(hasItem(DEFAULT_C_CS_PROVINCIA.toString())))
            .andExpect(jsonPath("$.[*].cCSNazione").value(hasItem(DEFAULT_C_CS_NAZIONE.toString())))
            .andExpect(jsonPath("$.[*].cCSOIndirizzo").value(hasItem(DEFAULT_C_CSO_INDIRIZZO.toString())))
            .andExpect(jsonPath("$.[*].cCSONumeroCivico").value(hasItem(DEFAULT_C_CSO_NUMERO_CIVICO.toString())))
            .andExpect(jsonPath("$.[*].cCSOCAP").value(hasItem(DEFAULT_C_CSOCAP.toString())))
            .andExpect(jsonPath("$.[*].cCSOComune").value(hasItem(DEFAULT_C_CSO_COMUNE.toString())))
            .andExpect(jsonPath("$.[*].cCSOProvincia").value(hasItem(DEFAULT_C_CSO_PROVINCIA.toString())))
            .andExpect(jsonPath("$.[*].cCSONazione").value(hasItem(DEFAULT_C_CSO_NAZIONE.toString())))
            .andExpect(jsonPath("$.[*].cCRFIdPaese").value(hasItem(DEFAULT_C_CRF_ID_PAESE.toString())))
            .andExpect(jsonPath("$.[*].cCRFIdCodice").value(hasItem(DEFAULT_C_CRF_ID_CODICE.toString())))
            .andExpect(jsonPath("$.[*].cCRFDenominazione").value(hasItem(DEFAULT_C_CRF_DENOMINAZIONE.toString())))
            .andExpect(jsonPath("$.[*].cCRFNome").value(hasItem(DEFAULT_C_CRF_NOME.toString())))
            .andExpect(jsonPath("$.[*].cCRFCognome").value(hasItem(DEFAULT_C_CRF_COGNOME.toString())))
            .andExpect(jsonPath("$.[*].tIDAIdPaese").value(hasItem(DEFAULT_T_IDA_ID_PAESE.toString())))
            .andExpect(jsonPath("$.[*].tIDAIdCodice").value(hasItem(DEFAULT_T_IDA_ID_CODICE.toString())))
            .andExpect(jsonPath("$.[*].tIDACodiceFiscale").value(hasItem(DEFAULT_T_IDA_CODICE_FISCALE.toString())))
            .andExpect(jsonPath("$.[*].tIADenominazione").value(hasItem(DEFAULT_T_IA_DENOMINAZIONE.toString())))
            .andExpect(jsonPath("$.[*].tIANome").value(hasItem(DEFAULT_T_IA_NOME.toString())))
            .andExpect(jsonPath("$.[*].tIACognome").value(hasItem(DEFAULT_T_IA_COGNOME.toString())))
            .andExpect(jsonPath("$.[*].tIATitolo").value(hasItem(DEFAULT_T_IA_TITOLO.toString())))
            .andExpect(jsonPath("$.[*].tIACodEORI").value(hasItem(DEFAULT_T_IA_COD_EORI.toString())))
            .andExpect(jsonPath("$.[*].soggettoEmittente").value(hasItem(DEFAULT_SOGGETTO_EMITTENTE.toString())));
    }
    
    @Test
    @Transactional
    public void getFAHeader() throws Exception {
        // Initialize the database
        fAHeaderRepository.saveAndFlush(fAHeader);

        // Get the fAHeader
        restFAHeaderMockMvc.perform(get("/api/fa-headers/{id}", fAHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fAHeader.getId().intValue()))
            .andExpect(jsonPath("$.trasmittenteIdPaese").value(DEFAULT_TRASMITTENTE_ID_PAESE.toString()))
            .andExpect(jsonPath("$.trasmittenteIdCodice").value(DEFAULT_TRASMITTENTE_ID_CODICE.toString()))
            .andExpect(jsonPath("$.progressivoInvio").value(DEFAULT_PROGRESSIVO_INVIO.toString()))
            .andExpect(jsonPath("$.formatoTrasmissione").value(DEFAULT_FORMATO_TRASMISSIONE.toString()))
            .andExpect(jsonPath("$.codiceDestinatario").value(DEFAULT_CODICE_DESTINATARIO.toString()))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.pECDestinatario").value(DEFAULT_P_EC_DESTINATARIO.toString()))
            .andExpect(jsonPath("$.cPDAIdPaese").value(DEFAULT_C_PDA_ID_PAESE.toString()))
            .andExpect(jsonPath("$.cPDAIdCodice").value(DEFAULT_C_PDA_ID_CODICE.toString()))
            .andExpect(jsonPath("$.cPDACodiceFiscale").value(DEFAULT_C_PDA_CODICE_FISCALE.toString()))
            .andExpect(jsonPath("$.cPADenominazione").value(DEFAULT_C_PA_DENOMINAZIONE.toString()))
            .andExpect(jsonPath("$.cPANome").value(DEFAULT_C_PA_NOME.toString()))
            .andExpect(jsonPath("$.cPACognome").value(DEFAULT_C_PA_COGNOME.toString()))
            .andExpect(jsonPath("$.cPATitolo").value(DEFAULT_C_PA_TITOLO.toString()))
            .andExpect(jsonPath("$.cPACodEORI").value(DEFAULT_C_PA_COD_EORI.toString()))
            .andExpect(jsonPath("$.cPAAlboProfessionale").value(DEFAULT_C_PA_ALBO_PROFESSIONALE.toString()))
            .andExpect(jsonPath("$.cPAProvinciaAlbo").value(DEFAULT_C_PA_PROVINCIA_ALBO.toString()))
            .andExpect(jsonPath("$.cPANumeroIscrizioneAlbo").value(DEFAULT_C_PA_NUMERO_ISCRIZIONE_ALBO.toString()))
            .andExpect(jsonPath("$.cPADataIscrizioneAlbo").value(DEFAULT_C_PA_DATA_ISCRIZIONE_ALBO.toString()))
            .andExpect(jsonPath("$.cPARegimeFiscale").value(DEFAULT_C_PA_REGIME_FISCALE.toString()))
            .andExpect(jsonPath("$.cPSIndirizzo").value(DEFAULT_C_PS_INDIRIZZO.toString()))
            .andExpect(jsonPath("$.cPSNumeroCivico").value(DEFAULT_C_PS_NUMERO_CIVICO.toString()))
            .andExpect(jsonPath("$.cPSCAP").value(DEFAULT_C_PSCAP.toString()))
            .andExpect(jsonPath("$.cPSComune").value(DEFAULT_C_PS_COMUNE.toString()))
            .andExpect(jsonPath("$.cPSProvincia").value(DEFAULT_C_PS_PROVINCIA.toString()))
            .andExpect(jsonPath("$.cPSNazione").value(DEFAULT_C_PS_NAZIONE.toString()))
            .andExpect(jsonPath("$.sOIndirizzo").value(DEFAULT_S_O_INDIRIZZO.toString()))
            .andExpect(jsonPath("$.sONumeroCivico").value(DEFAULT_S_O_NUMERO_CIVICO.toString()))
            .andExpect(jsonPath("$.sOCAP").value(DEFAULT_S_OCAP.toString()))
            .andExpect(jsonPath("$.sOComune").value(DEFAULT_S_O_COMUNE.toString()))
            .andExpect(jsonPath("$.sOProvincia").value(DEFAULT_S_O_PROVINCIA.toString()))
            .andExpect(jsonPath("$.sONazione").value(DEFAULT_S_O_NAZIONE.toString()))
            .andExpect(jsonPath("$.sOIREAUfficio").value(DEFAULT_S_OIREA_UFFICIO.toString()))
            .andExpect(jsonPath("$.sOIREANumeroREA").value(DEFAULT_S_OIREA_NUMERO_REA.toString()))
            .andExpect(jsonPath("$.sOIREACapitaleSociale").value(DEFAULT_S_OIREA_CAPITALE_SOCIALE.toString()))
            .andExpect(jsonPath("$.sOIREASocioUnico").value(DEFAULT_S_OIREA_SOCIO_UNICO.toString()))
            .andExpect(jsonPath("$.sOIREAStatoLiquidazione").value(DEFAULT_S_OIREA_STATO_LIQUIDAZIONE.toString()))
            .andExpect(jsonPath("$.sOCTelefono").value(DEFAULT_S_OC_TELEFONO.toString()))
            .andExpect(jsonPath("$.sOCFax").value(DEFAULT_S_OC_FAX.toString()))
            .andExpect(jsonPath("$.sOCEmail").value(DEFAULT_S_OC_EMAIL.toString()))
            .andExpect(jsonPath("$.sOCRiferimentoAmministrazione").value(DEFAULT_S_OC_RIFERIMENTO_AMMINISTRAZIONE.toString()))
            .andExpect(jsonPath("$.rFDAIdPaese").value(DEFAULT_R_FDA_ID_PAESE.toString()))
            .andExpect(jsonPath("$.rFDAIdCodice").value(DEFAULT_R_FDA_ID_CODICE.toString()))
            .andExpect(jsonPath("$.rFDACodiceFiscale").value(DEFAULT_R_FDA_CODICE_FISCALE.toString()))
            .andExpect(jsonPath("$.rFADenominazione").value(DEFAULT_R_FA_DENOMINAZIONE.toString()))
            .andExpect(jsonPath("$.rFANome").value(DEFAULT_R_FA_NOME.toString()))
            .andExpect(jsonPath("$.rFACognome").value(DEFAULT_R_FA_COGNOME.toString()))
            .andExpect(jsonPath("$.rFATitolo").value(DEFAULT_R_FA_TITOLO.toString()))
            .andExpect(jsonPath("$.rFACodEORI").value(DEFAULT_R_FA_COD_EORI.toString()))
            .andExpect(jsonPath("$.cCDAIdPaese").value(DEFAULT_C_CDA_ID_PAESE.toString()))
            .andExpect(jsonPath("$.cCDAIdCodice").value(DEFAULT_C_CDA_ID_CODICE.toString()))
            .andExpect(jsonPath("$.cCDACodiceFiscale").value(DEFAULT_C_CDA_CODICE_FISCALE.toString()))
            .andExpect(jsonPath("$.cCADenominazione").value(DEFAULT_C_CA_DENOMINAZIONE.toString()))
            .andExpect(jsonPath("$.cCANome").value(DEFAULT_C_CA_NOME.toString()))
            .andExpect(jsonPath("$.cCACognome").value(DEFAULT_C_CA_COGNOME.toString()))
            .andExpect(jsonPath("$.cCATitolo").value(DEFAULT_C_CA_TITOLO.toString()))
            .andExpect(jsonPath("$.cCACodEORI").value(DEFAULT_C_CA_COD_EORI.toString()))
            .andExpect(jsonPath("$.cCSIndirizzo").value(DEFAULT_C_CS_INDIRIZZO.toString()))
            .andExpect(jsonPath("$.cCSNumeroCivico").value(DEFAULT_C_CS_NUMERO_CIVICO.toString()))
            .andExpect(jsonPath("$.cCSCAP").value(DEFAULT_C_CSCAP.toString()))
            .andExpect(jsonPath("$.cCSComune").value(DEFAULT_C_CS_COMUNE.toString()))
            .andExpect(jsonPath("$.cCSProvincia").value(DEFAULT_C_CS_PROVINCIA.toString()))
            .andExpect(jsonPath("$.cCSNazione").value(DEFAULT_C_CS_NAZIONE.toString()))
            .andExpect(jsonPath("$.cCSOIndirizzo").value(DEFAULT_C_CSO_INDIRIZZO.toString()))
            .andExpect(jsonPath("$.cCSONumeroCivico").value(DEFAULT_C_CSO_NUMERO_CIVICO.toString()))
            .andExpect(jsonPath("$.cCSOCAP").value(DEFAULT_C_CSOCAP.toString()))
            .andExpect(jsonPath("$.cCSOComune").value(DEFAULT_C_CSO_COMUNE.toString()))
            .andExpect(jsonPath("$.cCSOProvincia").value(DEFAULT_C_CSO_PROVINCIA.toString()))
            .andExpect(jsonPath("$.cCSONazione").value(DEFAULT_C_CSO_NAZIONE.toString()))
            .andExpect(jsonPath("$.cCRFIdPaese").value(DEFAULT_C_CRF_ID_PAESE.toString()))
            .andExpect(jsonPath("$.cCRFIdCodice").value(DEFAULT_C_CRF_ID_CODICE.toString()))
            .andExpect(jsonPath("$.cCRFDenominazione").value(DEFAULT_C_CRF_DENOMINAZIONE.toString()))
            .andExpect(jsonPath("$.cCRFNome").value(DEFAULT_C_CRF_NOME.toString()))
            .andExpect(jsonPath("$.cCRFCognome").value(DEFAULT_C_CRF_COGNOME.toString()))
            .andExpect(jsonPath("$.tIDAIdPaese").value(DEFAULT_T_IDA_ID_PAESE.toString()))
            .andExpect(jsonPath("$.tIDAIdCodice").value(DEFAULT_T_IDA_ID_CODICE.toString()))
            .andExpect(jsonPath("$.tIDACodiceFiscale").value(DEFAULT_T_IDA_CODICE_FISCALE.toString()))
            .andExpect(jsonPath("$.tIADenominazione").value(DEFAULT_T_IA_DENOMINAZIONE.toString()))
            .andExpect(jsonPath("$.tIANome").value(DEFAULT_T_IA_NOME.toString()))
            .andExpect(jsonPath("$.tIACognome").value(DEFAULT_T_IA_COGNOME.toString()))
            .andExpect(jsonPath("$.tIATitolo").value(DEFAULT_T_IA_TITOLO.toString()))
            .andExpect(jsonPath("$.tIACodEORI").value(DEFAULT_T_IA_COD_EORI.toString()))
            .andExpect(jsonPath("$.soggettoEmittente").value(DEFAULT_SOGGETTO_EMITTENTE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFAHeader() throws Exception {
        // Get the fAHeader
        restFAHeaderMockMvc.perform(get("/api/fa-headers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFAHeader() throws Exception {
        // Initialize the database
        fAHeaderService.save(fAHeader);

        int databaseSizeBeforeUpdate = fAHeaderRepository.findAll().size();

        // Update the fAHeader
        FAHeader updatedFAHeader = fAHeaderRepository.findById(fAHeader.getId()).get();
        // Disconnect from session so that the updates on updatedFAHeader are not directly saved in db
        em.detach(updatedFAHeader);
        updatedFAHeader
            .trasmittenteIdPaese(UPDATED_TRASMITTENTE_ID_PAESE)
            .trasmittenteIdCodice(UPDATED_TRASMITTENTE_ID_CODICE)
            .progressivoInvio(UPDATED_PROGRESSIVO_INVIO)
            .formatoTrasmissione(UPDATED_FORMATO_TRASMISSIONE)
            .codiceDestinatario(UPDATED_CODICE_DESTINATARIO)
            .telefono(UPDATED_TELEFONO)
            .email(UPDATED_EMAIL)
            .pECDestinatario(UPDATED_P_EC_DESTINATARIO)
            .cPDAIdPaese(UPDATED_C_PDA_ID_PAESE)
            .cPDAIdCodice(UPDATED_C_PDA_ID_CODICE)
            .cPDACodiceFiscale(UPDATED_C_PDA_CODICE_FISCALE)
            .cPADenominazione(UPDATED_C_PA_DENOMINAZIONE)
            .cPANome(UPDATED_C_PA_NOME)
            .cPACognome(UPDATED_C_PA_COGNOME)
            .cPATitolo(UPDATED_C_PA_TITOLO)
            .cPACodEORI(UPDATED_C_PA_COD_EORI)
            .cPAAlboProfessionale(UPDATED_C_PA_ALBO_PROFESSIONALE)
            .cPAProvinciaAlbo(UPDATED_C_PA_PROVINCIA_ALBO)
            .cPANumeroIscrizioneAlbo(UPDATED_C_PA_NUMERO_ISCRIZIONE_ALBO)
            .cPADataIscrizioneAlbo(UPDATED_C_PA_DATA_ISCRIZIONE_ALBO)
            .cPARegimeFiscale(UPDATED_C_PA_REGIME_FISCALE)
            .cPSIndirizzo(UPDATED_C_PS_INDIRIZZO)
            .cPSNumeroCivico(UPDATED_C_PS_NUMERO_CIVICO)
            .cPSCAP(UPDATED_C_PSCAP)
            .cPSComune(UPDATED_C_PS_COMUNE)
            .cPSProvincia(UPDATED_C_PS_PROVINCIA)
            .cPSNazione(UPDATED_C_PS_NAZIONE)
            .sOIndirizzo(UPDATED_S_O_INDIRIZZO)
            .sONumeroCivico(UPDATED_S_O_NUMERO_CIVICO)
            .sOCAP(UPDATED_S_OCAP)
            .sOComune(UPDATED_S_O_COMUNE)
            .sOProvincia(UPDATED_S_O_PROVINCIA)
            .sONazione(UPDATED_S_O_NAZIONE)
            .sOIREAUfficio(UPDATED_S_OIREA_UFFICIO)
            .sOIREANumeroREA(UPDATED_S_OIREA_NUMERO_REA)
            .sOIREACapitaleSociale(UPDATED_S_OIREA_CAPITALE_SOCIALE)
            .sOIREASocioUnico(UPDATED_S_OIREA_SOCIO_UNICO)
            .sOIREAStatoLiquidazione(UPDATED_S_OIREA_STATO_LIQUIDAZIONE)
            .sOCTelefono(UPDATED_S_OC_TELEFONO)
            .sOCFax(UPDATED_S_OC_FAX)
            .sOCEmail(UPDATED_S_OC_EMAIL)
            .sOCRiferimentoAmministrazione(UPDATED_S_OC_RIFERIMENTO_AMMINISTRAZIONE)
            .rFDAIdPaese(UPDATED_R_FDA_ID_PAESE)
            .rFDAIdCodice(UPDATED_R_FDA_ID_CODICE)
            .rFDACodiceFiscale(UPDATED_R_FDA_CODICE_FISCALE)
            .rFADenominazione(UPDATED_R_FA_DENOMINAZIONE)
            .rFANome(UPDATED_R_FA_NOME)
            .rFACognome(UPDATED_R_FA_COGNOME)
            .rFATitolo(UPDATED_R_FA_TITOLO)
            .rFACodEORI(UPDATED_R_FA_COD_EORI)
            .cCDAIdPaese(UPDATED_C_CDA_ID_PAESE)
            .cCDAIdCodice(UPDATED_C_CDA_ID_CODICE)
            .cCDACodiceFiscale(UPDATED_C_CDA_CODICE_FISCALE)
            .cCADenominazione(UPDATED_C_CA_DENOMINAZIONE)
            .cCANome(UPDATED_C_CA_NOME)
            .cCACognome(UPDATED_C_CA_COGNOME)
            .cCATitolo(UPDATED_C_CA_TITOLO)
            .cCACodEORI(UPDATED_C_CA_COD_EORI)
            .cCSIndirizzo(UPDATED_C_CS_INDIRIZZO)
            .cCSNumeroCivico(UPDATED_C_CS_NUMERO_CIVICO)
            .cCSCAP(UPDATED_C_CSCAP)
            .cCSComune(UPDATED_C_CS_COMUNE)
            .cCSProvincia(UPDATED_C_CS_PROVINCIA)
            .cCSNazione(UPDATED_C_CS_NAZIONE)
            .cCSOIndirizzo(UPDATED_C_CSO_INDIRIZZO)
            .cCSONumeroCivico(UPDATED_C_CSO_NUMERO_CIVICO)
            .cCSOCAP(UPDATED_C_CSOCAP)
            .cCSOComune(UPDATED_C_CSO_COMUNE)
            .cCSOProvincia(UPDATED_C_CSO_PROVINCIA)
            .cCSONazione(UPDATED_C_CSO_NAZIONE)
            .cCRFIdPaese(UPDATED_C_CRF_ID_PAESE)
            .cCRFIdCodice(UPDATED_C_CRF_ID_CODICE)
            .cCRFDenominazione(UPDATED_C_CRF_DENOMINAZIONE)
            .cCRFNome(UPDATED_C_CRF_NOME)
            .cCRFCognome(UPDATED_C_CRF_COGNOME)
            .tIDAIdPaese(UPDATED_T_IDA_ID_PAESE)
            .tIDAIdCodice(UPDATED_T_IDA_ID_CODICE)
            .tIDACodiceFiscale(UPDATED_T_IDA_CODICE_FISCALE)
            .tIADenominazione(UPDATED_T_IA_DENOMINAZIONE)
            .tIANome(UPDATED_T_IA_NOME)
            .tIACognome(UPDATED_T_IA_COGNOME)
            .tIATitolo(UPDATED_T_IA_TITOLO)
            .tIACodEORI(UPDATED_T_IA_COD_EORI)
            .soggettoEmittente(UPDATED_SOGGETTO_EMITTENTE);

        restFAHeaderMockMvc.perform(put("/api/fa-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFAHeader)))
            .andExpect(status().isOk());

        // Validate the FAHeader in the database
        List<FAHeader> fAHeaderList = fAHeaderRepository.findAll();
        assertThat(fAHeaderList).hasSize(databaseSizeBeforeUpdate);
        FAHeader testFAHeader = fAHeaderList.get(fAHeaderList.size() - 1);
        assertThat(testFAHeader.getTrasmittenteIdPaese()).isEqualTo(UPDATED_TRASMITTENTE_ID_PAESE);
        assertThat(testFAHeader.getTrasmittenteIdCodice()).isEqualTo(UPDATED_TRASMITTENTE_ID_CODICE);
        assertThat(testFAHeader.getProgressivoInvio()).isEqualTo(UPDATED_PROGRESSIVO_INVIO);
        assertThat(testFAHeader.getFormatoTrasmissione()).isEqualTo(UPDATED_FORMATO_TRASMISSIONE);
        assertThat(testFAHeader.getCodiceDestinatario()).isEqualTo(UPDATED_CODICE_DESTINATARIO);
        assertThat(testFAHeader.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testFAHeader.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testFAHeader.getpECDestinatario()).isEqualTo(UPDATED_P_EC_DESTINATARIO);
        assertThat(testFAHeader.getcPDAIdPaese()).isEqualTo(UPDATED_C_PDA_ID_PAESE);
        assertThat(testFAHeader.getcPDAIdCodice()).isEqualTo(UPDATED_C_PDA_ID_CODICE);
        assertThat(testFAHeader.getcPDACodiceFiscale()).isEqualTo(UPDATED_C_PDA_CODICE_FISCALE);
        assertThat(testFAHeader.getcPADenominazione()).isEqualTo(UPDATED_C_PA_DENOMINAZIONE);
        assertThat(testFAHeader.getcPANome()).isEqualTo(UPDATED_C_PA_NOME);
        assertThat(testFAHeader.getcPACognome()).isEqualTo(UPDATED_C_PA_COGNOME);
        assertThat(testFAHeader.getcPATitolo()).isEqualTo(UPDATED_C_PA_TITOLO);
        assertThat(testFAHeader.getcPACodEORI()).isEqualTo(UPDATED_C_PA_COD_EORI);
        assertThat(testFAHeader.getcPAAlboProfessionale()).isEqualTo(UPDATED_C_PA_ALBO_PROFESSIONALE);
        assertThat(testFAHeader.getcPAProvinciaAlbo()).isEqualTo(UPDATED_C_PA_PROVINCIA_ALBO);
        assertThat(testFAHeader.getcPANumeroIscrizioneAlbo()).isEqualTo(UPDATED_C_PA_NUMERO_ISCRIZIONE_ALBO);
        assertThat(testFAHeader.getcPADataIscrizioneAlbo()).isEqualTo(UPDATED_C_PA_DATA_ISCRIZIONE_ALBO);
        assertThat(testFAHeader.getcPARegimeFiscale()).isEqualTo(UPDATED_C_PA_REGIME_FISCALE);
        assertThat(testFAHeader.getcPSIndirizzo()).isEqualTo(UPDATED_C_PS_INDIRIZZO);
        assertThat(testFAHeader.getcPSNumeroCivico()).isEqualTo(UPDATED_C_PS_NUMERO_CIVICO);
        assertThat(testFAHeader.getcPSCAP()).isEqualTo(UPDATED_C_PSCAP);
        assertThat(testFAHeader.getcPSComune()).isEqualTo(UPDATED_C_PS_COMUNE);
        assertThat(testFAHeader.getcPSProvincia()).isEqualTo(UPDATED_C_PS_PROVINCIA);
        assertThat(testFAHeader.getcPSNazione()).isEqualTo(UPDATED_C_PS_NAZIONE);
        assertThat(testFAHeader.getsOIndirizzo()).isEqualTo(UPDATED_S_O_INDIRIZZO);
        assertThat(testFAHeader.getsONumeroCivico()).isEqualTo(UPDATED_S_O_NUMERO_CIVICO);
        assertThat(testFAHeader.getsOCAP()).isEqualTo(UPDATED_S_OCAP);
        assertThat(testFAHeader.getsOComune()).isEqualTo(UPDATED_S_O_COMUNE);
        assertThat(testFAHeader.getsOProvincia()).isEqualTo(UPDATED_S_O_PROVINCIA);
        assertThat(testFAHeader.getsONazione()).isEqualTo(UPDATED_S_O_NAZIONE);
        assertThat(testFAHeader.getsOIREAUfficio()).isEqualTo(UPDATED_S_OIREA_UFFICIO);
        assertThat(testFAHeader.getsOIREANumeroREA()).isEqualTo(UPDATED_S_OIREA_NUMERO_REA);
        assertThat(testFAHeader.getsOIREACapitaleSociale()).isEqualTo(UPDATED_S_OIREA_CAPITALE_SOCIALE);
        assertThat(testFAHeader.getsOIREASocioUnico()).isEqualTo(UPDATED_S_OIREA_SOCIO_UNICO);
        assertThat(testFAHeader.getsOIREAStatoLiquidazione()).isEqualTo(UPDATED_S_OIREA_STATO_LIQUIDAZIONE);
        assertThat(testFAHeader.getsOCTelefono()).isEqualTo(UPDATED_S_OC_TELEFONO);
        assertThat(testFAHeader.getsOCFax()).isEqualTo(UPDATED_S_OC_FAX);
        assertThat(testFAHeader.getsOCEmail()).isEqualTo(UPDATED_S_OC_EMAIL);
        assertThat(testFAHeader.getsOCRiferimentoAmministrazione()).isEqualTo(UPDATED_S_OC_RIFERIMENTO_AMMINISTRAZIONE);
        assertThat(testFAHeader.getrFDAIdPaese()).isEqualTo(UPDATED_R_FDA_ID_PAESE);
        assertThat(testFAHeader.getrFDAIdCodice()).isEqualTo(UPDATED_R_FDA_ID_CODICE);
        assertThat(testFAHeader.getrFDACodiceFiscale()).isEqualTo(UPDATED_R_FDA_CODICE_FISCALE);
        assertThat(testFAHeader.getrFADenominazione()).isEqualTo(UPDATED_R_FA_DENOMINAZIONE);
        assertThat(testFAHeader.getrFANome()).isEqualTo(UPDATED_R_FA_NOME);
        assertThat(testFAHeader.getrFACognome()).isEqualTo(UPDATED_R_FA_COGNOME);
        assertThat(testFAHeader.getrFATitolo()).isEqualTo(UPDATED_R_FA_TITOLO);
        assertThat(testFAHeader.getrFACodEORI()).isEqualTo(UPDATED_R_FA_COD_EORI);
        assertThat(testFAHeader.getcCDAIdPaese()).isEqualTo(UPDATED_C_CDA_ID_PAESE);
        assertThat(testFAHeader.getcCDAIdCodice()).isEqualTo(UPDATED_C_CDA_ID_CODICE);
        assertThat(testFAHeader.getcCDACodiceFiscale()).isEqualTo(UPDATED_C_CDA_CODICE_FISCALE);
        assertThat(testFAHeader.getcCADenominazione()).isEqualTo(UPDATED_C_CA_DENOMINAZIONE);
        assertThat(testFAHeader.getcCANome()).isEqualTo(UPDATED_C_CA_NOME);
        assertThat(testFAHeader.getcCACognome()).isEqualTo(UPDATED_C_CA_COGNOME);
        assertThat(testFAHeader.getcCATitolo()).isEqualTo(UPDATED_C_CA_TITOLO);
        assertThat(testFAHeader.getcCACodEORI()).isEqualTo(UPDATED_C_CA_COD_EORI);
        assertThat(testFAHeader.getcCSIndirizzo()).isEqualTo(UPDATED_C_CS_INDIRIZZO);
        assertThat(testFAHeader.getcCSNumeroCivico()).isEqualTo(UPDATED_C_CS_NUMERO_CIVICO);
        assertThat(testFAHeader.getcCSCAP()).isEqualTo(UPDATED_C_CSCAP);
        assertThat(testFAHeader.getcCSComune()).isEqualTo(UPDATED_C_CS_COMUNE);
        assertThat(testFAHeader.getcCSProvincia()).isEqualTo(UPDATED_C_CS_PROVINCIA);
        assertThat(testFAHeader.getcCSNazione()).isEqualTo(UPDATED_C_CS_NAZIONE);
        assertThat(testFAHeader.getcCSOIndirizzo()).isEqualTo(UPDATED_C_CSO_INDIRIZZO);
        assertThat(testFAHeader.getcCSONumeroCivico()).isEqualTo(UPDATED_C_CSO_NUMERO_CIVICO);
        assertThat(testFAHeader.getcCSOCAP()).isEqualTo(UPDATED_C_CSOCAP);
        assertThat(testFAHeader.getcCSOComune()).isEqualTo(UPDATED_C_CSO_COMUNE);
        assertThat(testFAHeader.getcCSOProvincia()).isEqualTo(UPDATED_C_CSO_PROVINCIA);
        assertThat(testFAHeader.getcCSONazione()).isEqualTo(UPDATED_C_CSO_NAZIONE);
        assertThat(testFAHeader.getcCRFIdPaese()).isEqualTo(UPDATED_C_CRF_ID_PAESE);
        assertThat(testFAHeader.getcCRFIdCodice()).isEqualTo(UPDATED_C_CRF_ID_CODICE);
        assertThat(testFAHeader.getcCRFDenominazione()).isEqualTo(UPDATED_C_CRF_DENOMINAZIONE);
        assertThat(testFAHeader.getcCRFNome()).isEqualTo(UPDATED_C_CRF_NOME);
        assertThat(testFAHeader.getcCRFCognome()).isEqualTo(UPDATED_C_CRF_COGNOME);
        assertThat(testFAHeader.gettIDAIdPaese()).isEqualTo(UPDATED_T_IDA_ID_PAESE);
        assertThat(testFAHeader.gettIDAIdCodice()).isEqualTo(UPDATED_T_IDA_ID_CODICE);
        assertThat(testFAHeader.gettIDACodiceFiscale()).isEqualTo(UPDATED_T_IDA_CODICE_FISCALE);
        assertThat(testFAHeader.gettIADenominazione()).isEqualTo(UPDATED_T_IA_DENOMINAZIONE);
        assertThat(testFAHeader.gettIANome()).isEqualTo(UPDATED_T_IA_NOME);
        assertThat(testFAHeader.gettIACognome()).isEqualTo(UPDATED_T_IA_COGNOME);
        assertThat(testFAHeader.gettIATitolo()).isEqualTo(UPDATED_T_IA_TITOLO);
        assertThat(testFAHeader.gettIACodEORI()).isEqualTo(UPDATED_T_IA_COD_EORI);
        assertThat(testFAHeader.getSoggettoEmittente()).isEqualTo(UPDATED_SOGGETTO_EMITTENTE);
    }

    @Test
    @Transactional
    public void updateNonExistingFAHeader() throws Exception {
        int databaseSizeBeforeUpdate = fAHeaderRepository.findAll().size();

        // Create the FAHeader

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFAHeaderMockMvc.perform(put("/api/fa-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fAHeader)))
            .andExpect(status().isBadRequest());

        // Validate the FAHeader in the database
        List<FAHeader> fAHeaderList = fAHeaderRepository.findAll();
        assertThat(fAHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFAHeader() throws Exception {
        // Initialize the database
        fAHeaderService.save(fAHeader);

        int databaseSizeBeforeDelete = fAHeaderRepository.findAll().size();

        // Delete the fAHeader
        restFAHeaderMockMvc.perform(delete("/api/fa-headers/{id}", fAHeader.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<FAHeader> fAHeaderList = fAHeaderRepository.findAll();
        assertThat(fAHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FAHeader.class);
        FAHeader fAHeader1 = new FAHeader();
        fAHeader1.setId(1L);
        FAHeader fAHeader2 = new FAHeader();
        fAHeader2.setId(fAHeader1.getId());
        assertThat(fAHeader1).isEqualTo(fAHeader2);
        fAHeader2.setId(2L);
        assertThat(fAHeader1).isNotEqualTo(fAHeader2);
        fAHeader1.setId(null);
        assertThat(fAHeader1).isNotEqualTo(fAHeader2);
    }
}
