package net.anet.invoice.repo.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A FAHeader.
 */
@Entity
@Table(name = "fa_header")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FAHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 2)
    @Column(name = "trasmittente_id_paese", length = 2)
    private String trasmittenteIdPaese;

    @Size(max = 28)
    @Column(name = "trasmittente_id_codice", length = 28)
    private String trasmittenteIdCodice;

    @Size(max = 10)
    @Column(name = "progressivo_invio", length = 10)
    private String progressivoInvio;

    @Size(max = 5)
    @Column(name = "formato_trasmissione", length = 5)
    private String formatoTrasmissione;

    @Size(max = 7)
    @Column(name = "codice_destinatario", length = 7)
    private String codiceDestinatario;

    @Size(max = 12)
    @Column(name = "telefono", length = 12)
    private String telefono;

    @Size(max = 250)
    @Column(name = "email", length = 250)
    private String email;

    @Size(max = 250)
    @Column(name = "p_ec_destinatario", length = 250)
    private String pECDestinatario;

    @Size(max = 2)
    @Column(name = "c_pda_id_paese", length = 2)
    private String cPDAIdPaese;

    @Size(max = 28)
    @Column(name = "c_pda_id_codice", length = 28)
    private String cPDAIdCodice;

    @Size(max = 16)
    @Column(name = "c_pda_codice_fiscale", length = 16)
    private String cPDACodiceFiscale;

    @Size(max = 250)
    @Column(name = "c_pa_denominazione", length = 250)
    private String cPADenominazione;

    @Size(max = 100)
    @Column(name = "c_pa_nome", length = 100)
    private String cPANome;

    @Size(max = 100)
    @Column(name = "c_pa_cognome", length = 100)
    private String cPACognome;

    @Size(max = 10)
    @Column(name = "c_pa_titolo", length = 10)
    private String cPATitolo;

    @Size(max = 17)
    @Column(name = "c_pa_cod_eori", length = 17)
    private String cPACodEORI;

    @Size(max = 60)
    @Column(name = "c_pa_albo_professionale", length = 60)
    private String cPAAlboProfessionale;

    @Size(max = 2)
    @Column(name = "c_pa_provincia_albo", length = 2)
    private String cPAProvinciaAlbo;

    @Size(max = 60)
    @Column(name = "c_pa_numero_iscrizione_albo", length = 60)
    private String cPANumeroIscrizioneAlbo;

    @Size(max = 10)
    @Column(name = "c_pa_data_iscrizione_albo", length = 10)
    private String cPADataIscrizioneAlbo;

    @Size(max = 4)
    @Column(name = "c_pa_regime_fiscale", length = 4)
    private String cPARegimeFiscale;

    @Size(max = 60)
    @Column(name = "c_ps_indirizzo", length = 60)
    private String cPSIndirizzo;

    @Size(max = 8)
    @Column(name = "c_ps_numero_civico", length = 8)
    private String cPSNumeroCivico;

    @Size(max = 5)
    @Column(name = "c_pscap", length = 5)
    private String cPSCAP;

    @Size(max = 60)
    @Column(name = "c_ps_comune", length = 60)
    private String cPSComune;

    @Size(max = 2)
    @Column(name = "c_ps_provincia", length = 2)
    private String cPSProvincia;

    @Size(max = 2)
    @Column(name = "c_ps_nazione", length = 2)
    private String cPSNazione;

    @Size(max = 60)
    @Column(name = "s_o_indirizzo", length = 60)
    private String sOIndirizzo;

    @Size(max = 8)
    @Column(name = "s_o_numero_civico", length = 8)
    private String sONumeroCivico;

    @Size(max = 5)
    @Column(name = "s_ocap", length = 5)
    private String sOCAP;

    @Size(max = 60)
    @Column(name = "s_o_comune", length = 60)
    private String sOComune;

    @Size(max = 2)
    @Column(name = "s_o_provincia", length = 2)
    private String sOProvincia;

    @Size(max = 2)
    @Column(name = "s_o_nazione", length = 2)
    private String sONazione;

    @Size(max = 2)
    @Column(name = "s_oirea_ufficio", length = 2)
    private String sOIREAUfficio;

    @Size(max = 20)
    @Column(name = "s_oirea_numero_rea", length = 20)
    private String sOIREANumeroREA;

    @Size(max = 15)
    @Column(name = "s_oirea_capitale_sociale", length = 15)
    private String sOIREACapitaleSociale;

    @Size(max = 2)
    @Column(name = "s_oirea_socio_unico", length = 2)
    private String sOIREASocioUnico;

    @Size(max = 2)
    @Column(name = "s_oirea_stato_liquidazione", length = 2)
    private String sOIREAStatoLiquidazione;

    @Size(max = 12)
    @Column(name = "s_oc_telefono", length = 12)
    private String sOCTelefono;

    @Size(max = 12)
    @Column(name = "s_oc_fax", length = 12)
    private String sOCFax;

    @Size(max = 250)
    @Column(name = "s_oc_email", length = 250)
    private String sOCEmail;

    @Size(max = 20)
    @Column(name = "s_oc_riferimento_amministrazione", length = 20)
    private String sOCRiferimentoAmministrazione;

    @Size(max = 2)
    @Column(name = "r_fda_id_paese", length = 2)
    private String rFDAIdPaese;

    @Size(max = 20)
    @Column(name = "r_fda_id_codice", length = 20)
    private String rFDAIdCodice;

    @Size(max = 16)
    @Column(name = "r_fda_codice_fiscale", length = 16)
    private String rFDACodiceFiscale;

    @Size(max = 80)
    @Column(name = "r_fa_denominazione", length = 80)
    private String rFADenominazione;

    @Size(max = 60)
    @Column(name = "r_fa_nome", length = 60)
    private String rFANome;

    @Size(max = 60)
    @Column(name = "r_fa_cognome", length = 60)
    private String rFACognome;

    @Size(max = 10)
    @Column(name = "r_fa_titolo", length = 10)
    private String rFATitolo;

    @Size(max = 17)
    @Column(name = "r_fa_cod_eori", length = 17)
    private String rFACodEORI;

    @Size(max = 2)
    @Column(name = "c_cda_id_paese", length = 2)
    private String cCDAIdPaese;

    @Size(max = 28)
    @Column(name = "c_cda_id_codice", length = 28)
    private String cCDAIdCodice;

    @Size(max = 16)
    @Column(name = "c_cda_codice_fiscale", length = 16)
    private String cCDACodiceFiscale;

    @Size(max = 80)
    @Column(name = "c_ca_denominazione", length = 80)
    private String cCADenominazione;

    @Size(max = 60)
    @Column(name = "c_ca_nome", length = 60)
    private String cCANome;

    @Size(max = 60)
    @Column(name = "c_ca_cognome", length = 60)
    private String cCACognome;

    @Size(max = 10)
    @Column(name = "c_ca_titolo", length = 10)
    private String cCATitolo;

    @Size(max = 17)
    @Column(name = "c_ca_cod_eori", length = 17)
    private String cCACodEORI;

    @Size(max = 60)
    @Column(name = "c_cs_indirizzo", length = 60)
    private String cCSIndirizzo;

    @Size(max = 8)
    @Column(name = "c_cs_numero_civico", length = 8)
    private String cCSNumeroCivico;

    @Size(max = 5)
    @Column(name = "c_cscap", length = 5)
    private String cCSCAP;

    @Size(max = 60)
    @Column(name = "c_cs_comune", length = 60)
    private String cCSComune;

    @Size(max = 2)
    @Column(name = "c_cs_provincia", length = 2)
    private String cCSProvincia;

    @Size(max = 2)
    @Column(name = "c_cs_nazione", length = 2)
    private String cCSNazione;

    @Size(max = 60)
    @Column(name = "c_cso_indirizzo", length = 60)
    private String cCSOIndirizzo;

    @Size(max = 8)
    @Column(name = "c_cso_numero_civico", length = 8)
    private String cCSONumeroCivico;

    @Size(max = 5)
    @Column(name = "c_csocap", length = 5)
    private String cCSOCAP;

    @Size(max = 60)
    @Column(name = "c_cso_comune", length = 60)
    private String cCSOComune;

    @Size(max = 2)
    @Column(name = "c_cso_provincia", length = 2)
    private String cCSOProvincia;

    @Size(max = 2)
    @Column(name = "c_cso_nazione", length = 2)
    private String cCSONazione;

    @Size(max = 2)
    @Column(name = "c_crf_id_paese", length = 2)
    private String cCRFIdPaese;

    @Size(max = 28)
    @Column(name = "c_crf_id_codice", length = 28)
    private String cCRFIdCodice;

    @Size(max = 80)
    @Column(name = "c_crf_denominazione", length = 80)
    private String cCRFDenominazione;

    @Size(max = 60)
    @Column(name = "c_crf_nome", length = 60)
    private String cCRFNome;

    @Size(max = 60)
    @Column(name = "c_crf_cognome", length = 60)
    private String cCRFCognome;

    @Size(max = 2)
    @Column(name = "t_ida_id_paese", length = 10)
    private String tIDAIdPaese;

    @Size(max = 28)
    @Column(name = "t_ida_id_codice", length = 28)
    private String tIDAIdCodice;

    @Size(max = 16)
    @Column(name = "t_ida_codice_fiscale", length = 16)
    private String tIDACodiceFiscale;

    @Size(max = 80)
    @Column(name = "t_ia_denominazione", length = 80)
    private String tIADenominazione;

    @Size(max = 60)
    @Column(name = "t_ia_nome", length = 60)
    private String tIANome;

    @Size(max = 60)
    @Column(name = "t_ia_cognome", length = 60)
    private String tIACognome;

    @Size(max = 10)
    @Column(name = "t_ia_titolo", length = 10)
    private String tIATitolo;

    @Size(max = 17)
    @Column(name = "t_ia_cod_eori", length = 17)
    private String tIACodEORI;

    @Size(max = 2)
    @Column(name = "soggetto_emittente", length = 2)
    private String soggettoEmittente;

    @OneToMany(mappedBy = "fAHeader" , cascade = {CascadeType.ALL} , orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FABody> fABodies = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrasmittenteIdPaese() {
        return trasmittenteIdPaese;
    }

    public FAHeader trasmittenteIdPaese(String trasmittenteIdPaese) {
        this.trasmittenteIdPaese = trasmittenteIdPaese;
        return this;
    }

    public void setTrasmittenteIdPaese(String trasmittenteIdPaese) {
        this.trasmittenteIdPaese = trasmittenteIdPaese;
    }

    public String getTrasmittenteIdCodice() {
        return trasmittenteIdCodice;
    }

    public FAHeader trasmittenteIdCodice(String trasmittenteIdCodice) {
        this.trasmittenteIdCodice = trasmittenteIdCodice;
        return this;
    }

    public void setTrasmittenteIdCodice(String trasmittenteIdCodice) {
        this.trasmittenteIdCodice = trasmittenteIdCodice;
    }

    public String getProgressivoInvio() {
        return progressivoInvio;
    }

    public FAHeader progressivoInvio(String progressivoInvio) {
        this.progressivoInvio = progressivoInvio;
        return this;
    }

    public void setProgressivoInvio(String progressivoInvio) {
        this.progressivoInvio = progressivoInvio;
    }

    public String getFormatoTrasmissione() {
        return formatoTrasmissione;
    }

    public FAHeader formatoTrasmissione(String formatoTrasmissione) {
        this.formatoTrasmissione = formatoTrasmissione;
        return this;
    }

    public void setFormatoTrasmissione(String formatoTrasmissione) {
        this.formatoTrasmissione = formatoTrasmissione;
    }

    public String getCodiceDestinatario() {
        return codiceDestinatario;
    }

    public FAHeader codiceDestinatario(String codiceDestinatario) {
        this.codiceDestinatario = codiceDestinatario;
        return this;
    }

    public void setCodiceDestinatario(String codiceDestinatario) {
        this.codiceDestinatario = codiceDestinatario;
    }

    public String getTelefono() {
        return telefono;
    }

    public FAHeader telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public FAHeader email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getpECDestinatario() {
        return pECDestinatario;
    }

    public FAHeader pECDestinatario(String pECDestinatario) {
        this.pECDestinatario = pECDestinatario;
        return this;
    }

    public void setpECDestinatario(String pECDestinatario) {
        this.pECDestinatario = pECDestinatario;
    }

    public String getcPDAIdPaese() {
        return cPDAIdPaese;
    }

    public FAHeader cPDAIdPaese(String cPDAIdPaese) {
        this.cPDAIdPaese = cPDAIdPaese;
        return this;
    }

    public void setcPDAIdPaese(String cPDAIdPaese) {
        this.cPDAIdPaese = cPDAIdPaese;
    }

    public String getcPDAIdCodice() {
        return cPDAIdCodice;
    }

    public FAHeader cPDAIdCodice(String cPDAIdCodice) {
        this.cPDAIdCodice = cPDAIdCodice;
        return this;
    }

    public void setcPDAIdCodice(String cPDAIdCodice) {
        this.cPDAIdCodice = cPDAIdCodice;
    }

    public String getcPDACodiceFiscale() {
        return cPDACodiceFiscale;
    }

    public FAHeader cPDACodiceFiscale(String cPDACodiceFiscale) {
        this.cPDACodiceFiscale = cPDACodiceFiscale;
        return this;
    }

    public void setcPDACodiceFiscale(String cPDACodiceFiscale) {
        this.cPDACodiceFiscale = cPDACodiceFiscale;
    }

    public String getcPADenominazione() {
        return cPADenominazione;
    }

    public FAHeader cPADenominazione(String cPADenominazione) {
        this.cPADenominazione = cPADenominazione;
        return this;
    }

    public void setcPADenominazione(String cPADenominazione) {
        this.cPADenominazione = cPADenominazione;
    }

    public String getcPANome() {
        return cPANome;
    }

    public FAHeader cPANome(String cPANome) {
        this.cPANome = cPANome;
        return this;
    }

    public void setcPANome(String cPANome) {
        this.cPANome = cPANome;
    }

    public String getcPACognome() {
        return cPACognome;
    }

    public FAHeader cPACognome(String cPACognome) {
        this.cPACognome = cPACognome;
        return this;
    }

    public void setcPACognome(String cPACognome) {
        this.cPACognome = cPACognome;
    }

    public String getcPATitolo() {
        return cPATitolo;
    }

    public FAHeader cPATitolo(String cPATitolo) {
        this.cPATitolo = cPATitolo;
        return this;
    }

    public void setcPATitolo(String cPATitolo) {
        this.cPATitolo = cPATitolo;
    }

    public String getcPACodEORI() {
        return cPACodEORI;
    }

    public FAHeader cPACodEORI(String cPACodEORI) {
        this.cPACodEORI = cPACodEORI;
        return this;
    }

    public void setcPACodEORI(String cPACodEORI) {
        this.cPACodEORI = cPACodEORI;
    }

    public String getcPAAlboProfessionale() {
        return cPAAlboProfessionale;
    }

    public FAHeader cPAAlboProfessionale(String cPAAlboProfessionale) {
        this.cPAAlboProfessionale = cPAAlboProfessionale;
        return this;
    }

    public void setcPAAlboProfessionale(String cPAAlboProfessionale) {
        this.cPAAlboProfessionale = cPAAlboProfessionale;
    }

    public String getcPAProvinciaAlbo() {
        return cPAProvinciaAlbo;
    }

    public FAHeader cPAProvinciaAlbo(String cPAProvinciaAlbo) {
        this.cPAProvinciaAlbo = cPAProvinciaAlbo;
        return this;
    }

    public void setcPAProvinciaAlbo(String cPAProvinciaAlbo) {
        this.cPAProvinciaAlbo = cPAProvinciaAlbo;
    }

    public String getcPANumeroIscrizioneAlbo() {
        return cPANumeroIscrizioneAlbo;
    }

    public FAHeader cPANumeroIscrizioneAlbo(String cPANumeroIscrizioneAlbo) {
        this.cPANumeroIscrizioneAlbo = cPANumeroIscrizioneAlbo;
        return this;
    }

    public void setcPANumeroIscrizioneAlbo(String cPANumeroIscrizioneAlbo) {
        this.cPANumeroIscrizioneAlbo = cPANumeroIscrizioneAlbo;
    }

    public String getcPADataIscrizioneAlbo() {
        return cPADataIscrizioneAlbo;
    }

    public FAHeader cPADataIscrizioneAlbo(String cPADataIscrizioneAlbo) {
        this.cPADataIscrizioneAlbo = cPADataIscrizioneAlbo;
        return this;
    }

    public void setcPADataIscrizioneAlbo(String cPADataIscrizioneAlbo) {
        this.cPADataIscrizioneAlbo = cPADataIscrizioneAlbo;
    }

    public String getcPARegimeFiscale() {
        return cPARegimeFiscale;
    }

    public FAHeader cPARegimeFiscale(String cPARegimeFiscale) {
        this.cPARegimeFiscale = cPARegimeFiscale;
        return this;
    }

    public void setcPARegimeFiscale(String cPARegimeFiscale) {
        this.cPARegimeFiscale = cPARegimeFiscale;
    }

    public String getcPSIndirizzo() {
        return cPSIndirizzo;
    }

    public FAHeader cPSIndirizzo(String cPSIndirizzo) {
        this.cPSIndirizzo = cPSIndirizzo;
        return this;
    }

    public void setcPSIndirizzo(String cPSIndirizzo) {
        this.cPSIndirizzo = cPSIndirizzo;
    }

    public String getcPSNumeroCivico() {
        return cPSNumeroCivico;
    }

    public FAHeader cPSNumeroCivico(String cPSNumeroCivico) {
        this.cPSNumeroCivico = cPSNumeroCivico;
        return this;
    }

    public void setcPSNumeroCivico(String cPSNumeroCivico) {
        this.cPSNumeroCivico = cPSNumeroCivico;
    }

    public String getcPSCAP() {
        return cPSCAP;
    }

    public FAHeader cPSCAP(String cPSCAP) {
        this.cPSCAP = cPSCAP;
        return this;
    }

    public void setcPSCAP(String cPSCAP) {
        this.cPSCAP = cPSCAP;
    }

    public String getcPSComune() {
        return cPSComune;
    }

    public FAHeader cPSComune(String cPSComune) {
        this.cPSComune = cPSComune;
        return this;
    }

    public void setcPSComune(String cPSComune) {
        this.cPSComune = cPSComune;
    }

    public String getcPSProvincia() {
        return cPSProvincia;
    }

    public FAHeader cPSProvincia(String cPSProvincia) {
        this.cPSProvincia = cPSProvincia;
        return this;
    }

    public void setcPSProvincia(String cPSProvincia) {
        this.cPSProvincia = cPSProvincia;
    }

    public String getcPSNazione() {
        return cPSNazione;
    }

    public FAHeader cPSNazione(String cPSNazione) {
        this.cPSNazione = cPSNazione;
        return this;
    }

    public void setcPSNazione(String cPSNazione) {
        this.cPSNazione = cPSNazione;
    }

    public String getsOIndirizzo() {
        return sOIndirizzo;
    }

    public FAHeader sOIndirizzo(String sOIndirizzo) {
        this.sOIndirizzo = sOIndirizzo;
        return this;
    }

    public void setsOIndirizzo(String sOIndirizzo) {
        this.sOIndirizzo = sOIndirizzo;
    }

    public String getsONumeroCivico() {
        return sONumeroCivico;
    }

    public FAHeader sONumeroCivico(String sONumeroCivico) {
        this.sONumeroCivico = sONumeroCivico;
        return this;
    }

    public void setsONumeroCivico(String sONumeroCivico) {
        this.sONumeroCivico = sONumeroCivico;
    }

    public String getsOCAP() {
        return sOCAP;
    }

    public FAHeader sOCAP(String sOCAP) {
        this.sOCAP = sOCAP;
        return this;
    }

    public void setsOCAP(String sOCAP) {
        this.sOCAP = sOCAP;
    }

    public String getsOComune() {
        return sOComune;
    }

    public FAHeader sOComune(String sOComune) {
        this.sOComune = sOComune;
        return this;
    }

    public void setsOComune(String sOComune) {
        this.sOComune = sOComune;
    }

    public String getsOProvincia() {
        return sOProvincia;
    }

    public FAHeader sOProvincia(String sOProvincia) {
        this.sOProvincia = sOProvincia;
        return this;
    }

    public void setsOProvincia(String sOProvincia) {
        this.sOProvincia = sOProvincia;
    }

    public String getsONazione() {
        return sONazione;
    }

    public FAHeader sONazione(String sONazione) {
        this.sONazione = sONazione;
        return this;
    }

    public void setsONazione(String sONazione) {
        this.sONazione = sONazione;
    }

    public String getsOIREAUfficio() {
        return sOIREAUfficio;
    }

    public FAHeader sOIREAUfficio(String sOIREAUfficio) {
        this.sOIREAUfficio = sOIREAUfficio;
        return this;
    }

    public void setsOIREAUfficio(String sOIREAUfficio) {
        this.sOIREAUfficio = sOIREAUfficio;
    }

    public String getsOIREANumeroREA() {
        return sOIREANumeroREA;
    }

    public FAHeader sOIREANumeroREA(String sOIREANumeroREA) {
        this.sOIREANumeroREA = sOIREANumeroREA;
        return this;
    }

    public void setsOIREANumeroREA(String sOIREANumeroREA) {
        this.sOIREANumeroREA = sOIREANumeroREA;
    }

    public String getsOIREACapitaleSociale() {
        return sOIREACapitaleSociale;
    }

    public FAHeader sOIREACapitaleSociale(String sOIREACapitaleSociale) {
        this.sOIREACapitaleSociale = sOIREACapitaleSociale;
        return this;
    }

    public void setsOIREACapitaleSociale(String sOIREACapitaleSociale) {
        this.sOIREACapitaleSociale = sOIREACapitaleSociale;
    }

    public String getsOIREASocioUnico() {
        return sOIREASocioUnico;
    }

    public FAHeader sOIREASocioUnico(String sOIREASocioUnico) {
        this.sOIREASocioUnico = sOIREASocioUnico;
        return this;
    }

    public void setsOIREASocioUnico(String sOIREASocioUnico) {
        this.sOIREASocioUnico = sOIREASocioUnico;
    }

    public String getsOIREAStatoLiquidazione() {
        return sOIREAStatoLiquidazione;
    }

    public FAHeader sOIREAStatoLiquidazione(String sOIREAStatoLiquidazione) {
        this.sOIREAStatoLiquidazione = sOIREAStatoLiquidazione;
        return this;
    }

    public void setsOIREAStatoLiquidazione(String sOIREAStatoLiquidazione) {
        this.sOIREAStatoLiquidazione = sOIREAStatoLiquidazione;
    }

    public String getsOCTelefono() {
        return sOCTelefono;
    }

    public FAHeader sOCTelefono(String sOCTelefono) {
        this.sOCTelefono = sOCTelefono;
        return this;
    }

    public void setsOCTelefono(String sOCTelefono) {
        this.sOCTelefono = sOCTelefono;
    }

    public String getsOCFax() {
        return sOCFax;
    }

    public FAHeader sOCFax(String sOCFax) {
        this.sOCFax = sOCFax;
        return this;
    }

    public void setsOCFax(String sOCFax) {
        this.sOCFax = sOCFax;
    }

    public String getsOCEmail() {
        return sOCEmail;
    }

    public FAHeader sOCEmail(String sOCEmail) {
        this.sOCEmail = sOCEmail;
        return this;
    }

    public void setsOCEmail(String sOCEmail) {
        this.sOCEmail = sOCEmail;
    }

    public String getsOCRiferimentoAmministrazione() {
        return sOCRiferimentoAmministrazione;
    }

    public FAHeader sOCRiferimentoAmministrazione(String sOCRiferimentoAmministrazione) {
        this.sOCRiferimentoAmministrazione = sOCRiferimentoAmministrazione;
        return this;
    }

    public void setsOCRiferimentoAmministrazione(String sOCRiferimentoAmministrazione) {
        this.sOCRiferimentoAmministrazione = sOCRiferimentoAmministrazione;
    }

    public String getrFDAIdPaese() {
        return rFDAIdPaese;
    }

    public FAHeader rFDAIdPaese(String rFDAIdPaese) {
        this.rFDAIdPaese = rFDAIdPaese;
        return this;
    }

    public void setrFDAIdPaese(String rFDAIdPaese) {
        this.rFDAIdPaese = rFDAIdPaese;
    }

    public String getrFDAIdCodice() {
        return rFDAIdCodice;
    }

    public FAHeader rFDAIdCodice(String rFDAIdCodice) {
        this.rFDAIdCodice = rFDAIdCodice;
        return this;
    }

    public void setrFDAIdCodice(String rFDAIdCodice) {
        this.rFDAIdCodice = rFDAIdCodice;
    }

    public String getrFDACodiceFiscale() {
        return rFDACodiceFiscale;
    }

    public FAHeader rFDACodiceFiscale(String rFDACodiceFiscale) {
        this.rFDACodiceFiscale = rFDACodiceFiscale;
        return this;
    }

    public void setrFDACodiceFiscale(String rFDACodiceFiscale) {
        this.rFDACodiceFiscale = rFDACodiceFiscale;
    }

    public String getrFADenominazione() {
        return rFADenominazione;
    }

    public FAHeader rFADenominazione(String rFADenominazione) {
        this.rFADenominazione = rFADenominazione;
        return this;
    }

    public void setrFADenominazione(String rFADenominazione) {
        this.rFADenominazione = rFADenominazione;
    }

    public String getrFANome() {
        return rFANome;
    }

    public FAHeader rFANome(String rFANome) {
        this.rFANome = rFANome;
        return this;
    }

    public void setrFANome(String rFANome) {
        this.rFANome = rFANome;
    }

    public String getrFACognome() {
        return rFACognome;
    }

    public FAHeader rFACognome(String rFACognome) {
        this.rFACognome = rFACognome;
        return this;
    }

    public void setrFACognome(String rFACognome) {
        this.rFACognome = rFACognome;
    }

    public String getrFATitolo() {
        return rFATitolo;
    }

    public FAHeader rFATitolo(String rFATitolo) {
        this.rFATitolo = rFATitolo;
        return this;
    }

    public void setrFATitolo(String rFATitolo) {
        this.rFATitolo = rFATitolo;
    }

    public String getrFACodEORI() {
        return rFACodEORI;
    }

    public FAHeader rFACodEORI(String rFACodEORI) {
        this.rFACodEORI = rFACodEORI;
        return this;
    }

    public void setrFACodEORI(String rFACodEORI) {
        this.rFACodEORI = rFACodEORI;
    }

    public String getcCDAIdPaese() {
        return cCDAIdPaese;
    }

    public FAHeader cCDAIdPaese(String cCDAIdPaese) {
        this.cCDAIdPaese = cCDAIdPaese;
        return this;
    }

    public void setcCDAIdPaese(String cCDAIdPaese) {
        this.cCDAIdPaese = cCDAIdPaese;
    }

    public String getcCDAIdCodice() {
        return cCDAIdCodice;
    }

    public FAHeader cCDAIdCodice(String cCDAIdCodice) {
        this.cCDAIdCodice = cCDAIdCodice;
        return this;
    }

    public void setcCDAIdCodice(String cCDAIdCodice) {
        this.cCDAIdCodice = cCDAIdCodice;
    }

    public String getcCDACodiceFiscale() {
        return cCDACodiceFiscale;
    }

    public FAHeader cCDACodiceFiscale(String cCDACodiceFiscale) {
        this.cCDACodiceFiscale = cCDACodiceFiscale;
        return this;
    }

    public void setcCDACodiceFiscale(String cCDACodiceFiscale) {
        this.cCDACodiceFiscale = cCDACodiceFiscale;
    }

    public String getcCADenominazione() {
        return cCADenominazione;
    }

    public FAHeader cCADenominazione(String cCADenominazione) {
        this.cCADenominazione = cCADenominazione;
        return this;
    }

    public void setcCADenominazione(String cCADenominazione) {
        this.cCADenominazione = cCADenominazione;
    }

    public String getcCANome() {
        return cCANome;
    }

    public FAHeader cCANome(String cCANome) {
        this.cCANome = cCANome;
        return this;
    }

    public void setcCANome(String cCANome) {
        this.cCANome = cCANome;
    }

    public String getcCACognome() {
        return cCACognome;
    }

    public FAHeader cCACognome(String cCACognome) {
        this.cCACognome = cCACognome;
        return this;
    }

    public void setcCACognome(String cCACognome) {
        this.cCACognome = cCACognome;
    }

    public String getcCATitolo() {
        return cCATitolo;
    }

    public FAHeader cCATitolo(String cCATitolo) {
        this.cCATitolo = cCATitolo;
        return this;
    }

    public void setcCATitolo(String cCATitolo) {
        this.cCATitolo = cCATitolo;
    }

    public String getcCACodEORI() {
        return cCACodEORI;
    }

    public FAHeader cCACodEORI(String cCACodEORI) {
        this.cCACodEORI = cCACodEORI;
        return this;
    }

    public void setcCACodEORI(String cCACodEORI) {
        this.cCACodEORI = cCACodEORI;
    }

    public String getcCSIndirizzo() {
        return cCSIndirizzo;
    }

    public FAHeader cCSIndirizzo(String cCSIndirizzo) {
        this.cCSIndirizzo = cCSIndirizzo;
        return this;
    }

    public void setcCSIndirizzo(String cCSIndirizzo) {
        this.cCSIndirizzo = cCSIndirizzo;
    }

    public String getcCSNumeroCivico() {
        return cCSNumeroCivico;
    }

    public FAHeader cCSNumeroCivico(String cCSNumeroCivico) {
        this.cCSNumeroCivico = cCSNumeroCivico;
        return this;
    }

    public void setcCSNumeroCivico(String cCSNumeroCivico) {
        this.cCSNumeroCivico = cCSNumeroCivico;
    }

    public String getcCSCAP() {
        return cCSCAP;
    }

    public FAHeader cCSCAP(String cCSCAP) {
        this.cCSCAP = cCSCAP;
        return this;
    }

    public void setcCSCAP(String cCSCAP) {
        this.cCSCAP = cCSCAP;
    }

    public String getcCSComune() {
        return cCSComune;
    }

    public FAHeader cCSComune(String cCSComune) {
        this.cCSComune = cCSComune;
        return this;
    }

    public void setcCSComune(String cCSComune) {
        this.cCSComune = cCSComune;
    }

    public String getcCSProvincia() {
        return cCSProvincia;
    }

    public FAHeader cCSProvincia(String cCSProvincia) {
        this.cCSProvincia = cCSProvincia;
        return this;
    }

    public void setcCSProvincia(String cCSProvincia) {
        this.cCSProvincia = cCSProvincia;
    }

    public String getcCSNazione() {
        return cCSNazione;
    }

    public FAHeader cCSNazione(String cCSNazione) {
        this.cCSNazione = cCSNazione;
        return this;
    }

    public void setcCSNazione(String cCSNazione) {
        this.cCSNazione = cCSNazione;
    }

    public String getcCSOIndirizzo() {
        return cCSOIndirizzo;
    }

    public FAHeader cCSOIndirizzo(String cCSOIndirizzo) {
        this.cCSOIndirizzo = cCSOIndirizzo;
        return this;
    }

    public void setcCSOIndirizzo(String cCSOIndirizzo) {
        this.cCSOIndirizzo = cCSOIndirizzo;
    }

    public String getcCSONumeroCivico() {
        return cCSONumeroCivico;
    }

    public FAHeader cCSONumeroCivico(String cCSONumeroCivico) {
        this.cCSONumeroCivico = cCSONumeroCivico;
        return this;
    }

    public void setcCSONumeroCivico(String cCSONumeroCivico) {
        this.cCSONumeroCivico = cCSONumeroCivico;
    }

    public String getcCSOCAP() {
        return cCSOCAP;
    }

    public FAHeader cCSOCAP(String cCSOCAP) {
        this.cCSOCAP = cCSOCAP;
        return this;
    }

    public void setcCSOCAP(String cCSOCAP) {
        this.cCSOCAP = cCSOCAP;
    }

    public String getcCSOComune() {
        return cCSOComune;
    }

    public FAHeader cCSOComune(String cCSOComune) {
        this.cCSOComune = cCSOComune;
        return this;
    }

    public void setcCSOComune(String cCSOComune) {
        this.cCSOComune = cCSOComune;
    }

    public String getcCSOProvincia() {
        return cCSOProvincia;
    }

    public FAHeader cCSOProvincia(String cCSOProvincia) {
        this.cCSOProvincia = cCSOProvincia;
        return this;
    }

    public void setcCSOProvincia(String cCSOProvincia) {
        this.cCSOProvincia = cCSOProvincia;
    }

    public String getcCSONazione() {
        return cCSONazione;
    }

    public FAHeader cCSONazione(String cCSONazione) {
        this.cCSONazione = cCSONazione;
        return this;
    }

    public void setcCSONazione(String cCSONazione) {
        this.cCSONazione = cCSONazione;
    }

    public String getcCRFIdPaese() {
        return cCRFIdPaese;
    }

    public FAHeader cCRFIdPaese(String cCRFIdPaese) {
        this.cCRFIdPaese = cCRFIdPaese;
        return this;
    }

    public void setcCRFIdPaese(String cCRFIdPaese) {
        this.cCRFIdPaese = cCRFIdPaese;
    }

    public String getcCRFIdCodice() {
        return cCRFIdCodice;
    }

    public FAHeader cCRFIdCodice(String cCRFIdCodice) {
        this.cCRFIdCodice = cCRFIdCodice;
        return this;
    }

    public void setcCRFIdCodice(String cCRFIdCodice) {
        this.cCRFIdCodice = cCRFIdCodice;
    }

    public String getcCRFDenominazione() {
        return cCRFDenominazione;
    }

    public FAHeader cCRFDenominazione(String cCRFDenominazione) {
        this.cCRFDenominazione = cCRFDenominazione;
        return this;
    }

    public void setcCRFDenominazione(String cCRFDenominazione) {
        this.cCRFDenominazione = cCRFDenominazione;
    }

    public String getcCRFNome() {
        return cCRFNome;
    }

    public FAHeader cCRFNome(String cCRFNome) {
        this.cCRFNome = cCRFNome;
        return this;
    }

    public void setcCRFNome(String cCRFNome) {
        this.cCRFNome = cCRFNome;
    }

    public String getcCRFCognome() {
        return cCRFCognome;
    }

    public FAHeader cCRFCognome(String cCRFCognome) {
        this.cCRFCognome = cCRFCognome;
        return this;
    }

    public void setcCRFCognome(String cCRFCognome) {
        this.cCRFCognome = cCRFCognome;
    }

    public String gettIDAIdPaese() {
        return tIDAIdPaese;
    }

    public FAHeader tIDAIdPaese(String tIDAIdPaese) {
        this.tIDAIdPaese = tIDAIdPaese;
        return this;
    }

    public void settIDAIdPaese(String tIDAIdPaese) {
        this.tIDAIdPaese = tIDAIdPaese;
    }

    public String gettIDAIdCodice() {
        return tIDAIdCodice;
    }

    public FAHeader tIDAIdCodice(String tIDAIdCodice) {
        this.tIDAIdCodice = tIDAIdCodice;
        return this;
    }

    public void settIDAIdCodice(String tIDAIdCodice) {
        this.tIDAIdCodice = tIDAIdCodice;
    }

    public String gettIDACodiceFiscale() {
        return tIDACodiceFiscale;
    }

    public FAHeader tIDACodiceFiscale(String tIDACodiceFiscale) {
        this.tIDACodiceFiscale = tIDACodiceFiscale;
        return this;
    }

    public void settIDACodiceFiscale(String tIDACodiceFiscale) {
        this.tIDACodiceFiscale = tIDACodiceFiscale;
    }

    public String gettIADenominazione() {
        return tIADenominazione;
    }

    public FAHeader tIADenominazione(String tIADenominazione) {
        this.tIADenominazione = tIADenominazione;
        return this;
    }

    public void settIADenominazione(String tIADenominazione) {
        this.tIADenominazione = tIADenominazione;
    }

    public String gettIANome() {
        return tIANome;
    }

    public FAHeader tIANome(String tIANome) {
        this.tIANome = tIANome;
        return this;
    }

    public void settIANome(String tIANome) {
        this.tIANome = tIANome;
    }

    public String gettIACognome() {
        return tIACognome;
    }

    public FAHeader tIACognome(String tIACognome) {
        this.tIACognome = tIACognome;
        return this;
    }

    public void settIACognome(String tIACognome) {
        this.tIACognome = tIACognome;
    }

    public String gettIATitolo() {
        return tIATitolo;
    }

    public FAHeader tIATitolo(String tIATitolo) {
        this.tIATitolo = tIATitolo;
        return this;
    }

    public void settIATitolo(String tIATitolo) {
        this.tIATitolo = tIATitolo;
    }

    public String gettIACodEORI() {
        return tIACodEORI;
    }

    public FAHeader tIACodEORI(String tIACodEORI) {
        this.tIACodEORI = tIACodEORI;
        return this;
    }

    public void settIACodEORI(String tIACodEORI) {
        this.tIACodEORI = tIACodEORI;
    }

    public String getSoggettoEmittente() {
        return soggettoEmittente;
    }

    public FAHeader soggettoEmittente(String soggettoEmittente) {
        this.soggettoEmittente = soggettoEmittente;
        return this;
    }

    public void setSoggettoEmittente(String soggettoEmittente) {
        this.soggettoEmittente = soggettoEmittente;
    }

    public Set<FABody> getFABodies() {
        return fABodies;
    }

    public FAHeader fABodies(Set<FABody> fABodies) {
        this.fABodies = fABodies;
        return this;
    }

    public FAHeader addFABody(FABody fABody) {
        this.fABodies.add(fABody);
        fABody.setFAHeader(this);
        return this;
    }

    public FAHeader removeFABody(FABody fABody) {
        this.fABodies.remove(fABody);
        fABody.setFAHeader(null);
        return this;
    }

    public void setFABodies(Set<FABody> fABodies) {
        this.fABodies = fABodies;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FAHeader)) {
            return false;
        }
        return id != null && id.equals(((FAHeader) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FAHeader{" +
            "id=" + getId() +
            ", trasmittenteIdPaese='" + getTrasmittenteIdPaese() + "'" +
            ", trasmittenteIdCodice='" + getTrasmittenteIdCodice() + "'" +
            ", progressivoInvio='" + getProgressivoInvio() + "'" +
            ", formatoTrasmissione='" + getFormatoTrasmissione() + "'" +
            ", codiceDestinatario='" + getCodiceDestinatario() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", email='" + getEmail() + "'" +
            ", pECDestinatario='" + getpECDestinatario() + "'" +
            ", cPDAIdPaese='" + getcPDAIdPaese() + "'" +
            ", cPDAIdCodice='" + getcPDAIdCodice() + "'" +
            ", cPDACodiceFiscale='" + getcPDACodiceFiscale() + "'" +
            ", cPADenominazione='" + getcPADenominazione() + "'" +
            ", cPANome='" + getcPANome() + "'" +
            ", cPACognome='" + getcPACognome() + "'" +
            ", cPATitolo='" + getcPATitolo() + "'" +
            ", cPACodEORI='" + getcPACodEORI() + "'" +
            ", cPAAlboProfessionale='" + getcPAAlboProfessionale() + "'" +
            ", cPAProvinciaAlbo='" + getcPAProvinciaAlbo() + "'" +
            ", cPANumeroIscrizioneAlbo='" + getcPANumeroIscrizioneAlbo() + "'" +
            ", cPADataIscrizioneAlbo='" + getcPADataIscrizioneAlbo() + "'" +
            ", cPARegimeFiscale='" + getcPARegimeFiscale() + "'" +
            ", cPSIndirizzo='" + getcPSIndirizzo() + "'" +
            ", cPSNumeroCivico='" + getcPSNumeroCivico() + "'" +
            ", cPSCAP='" + getcPSCAP() + "'" +
            ", cPSComune='" + getcPSComune() + "'" +
            ", cPSProvincia='" + getcPSProvincia() + "'" +
            ", cPSNazione='" + getcPSNazione() + "'" +
            ", sOIndirizzo='" + getsOIndirizzo() + "'" +
            ", sONumeroCivico='" + getsONumeroCivico() + "'" +
            ", sOCAP='" + getsOCAP() + "'" +
            ", sOComune='" + getsOComune() + "'" +
            ", sOProvincia='" + getsOProvincia() + "'" +
            ", sONazione='" + getsONazione() + "'" +
            ", sOIREAUfficio='" + getsOIREAUfficio() + "'" +
            ", sOIREANumeroREA='" + getsOIREANumeroREA() + "'" +
            ", sOIREACapitaleSociale='" + getsOIREACapitaleSociale() + "'" +
            ", sOIREASocioUnico='" + getsOIREASocioUnico() + "'" +
            ", sOIREAStatoLiquidazione='" + getsOIREAStatoLiquidazione() + "'" +
            ", sOCTelefono='" + getsOCTelefono() + "'" +
            ", sOCFax='" + getsOCFax() + "'" +
            ", sOCEmail='" + getsOCEmail() + "'" +
            ", sOCRiferimentoAmministrazione='" + getsOCRiferimentoAmministrazione() + "'" +
            ", rFDAIdPaese='" + getrFDAIdPaese() + "'" +
            ", rFDAIdCodice='" + getrFDAIdCodice() + "'" +
            ", rFDACodiceFiscale='" + getrFDACodiceFiscale() + "'" +
            ", rFADenominazione='" + getrFADenominazione() + "'" +
            ", rFANome='" + getrFANome() + "'" +
            ", rFACognome='" + getrFACognome() + "'" +
            ", rFATitolo='" + getrFATitolo() + "'" +
            ", rFACodEORI='" + getrFACodEORI() + "'" +
            ", cCDAIdPaese='" + getcCDAIdPaese() + "'" +
            ", cCDAIdCodice='" + getcCDAIdCodice() + "'" +
            ", cCDACodiceFiscale='" + getcCDACodiceFiscale() + "'" +
            ", cCADenominazione='" + getcCADenominazione() + "'" +
            ", cCANome='" + getcCANome() + "'" +
            ", cCACognome='" + getcCACognome() + "'" +
            ", cCATitolo='" + getcCATitolo() + "'" +
            ", cCACodEORI='" + getcCACodEORI() + "'" +
            ", cCSIndirizzo='" + getcCSIndirizzo() + "'" +
            ", cCSNumeroCivico='" + getcCSNumeroCivico() + "'" +
            ", cCSCAP='" + getcCSCAP() + "'" +
            ", cCSComune='" + getcCSComune() + "'" +
            ", cCSProvincia='" + getcCSProvincia() + "'" +
            ", cCSNazione='" + getcCSNazione() + "'" +
            ", cCSOIndirizzo='" + getcCSOIndirizzo() + "'" +
            ", cCSONumeroCivico='" + getcCSONumeroCivico() + "'" +
            ", cCSOCAP='" + getcCSOCAP() + "'" +
            ", cCSOComune='" + getcCSOComune() + "'" +
            ", cCSOProvincia='" + getcCSOProvincia() + "'" +
            ", cCSONazione='" + getcCSONazione() + "'" +
            ", cCRFIdPaese='" + getcCRFIdPaese() + "'" +
            ", cCRFIdCodice='" + getcCRFIdCodice() + "'" +
            ", cCRFDenominazione='" + getcCRFDenominazione() + "'" +
            ", cCRFNome='" + getcCRFNome() + "'" +
            ", cCRFCognome='" + getcCRFCognome() + "'" +
            ", tIDAIdPaese='" + gettIDAIdPaese() + "'" +
            ", tIDAIdCodice='" + gettIDAIdCodice() + "'" +
            ", tIDACodiceFiscale='" + gettIDACodiceFiscale() + "'" +
            ", tIADenominazione='" + gettIADenominazione() + "'" +
            ", tIANome='" + gettIANome() + "'" +
            ", tIACognome='" + gettIACognome() + "'" +
            ", tIATitolo='" + gettIATitolo() + "'" +
            ", tIACodEORI='" + gettIACodEORI() + "'" +
            ", soggettoEmittente='" + getSoggettoEmittente() + "'" +
            "}";
    }
}
