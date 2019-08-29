package net.anet.invoice.repo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DettaglioPagamento.
 */
@Entity
@Table(name = "dettaglio_pagamento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DettaglioPagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 200)
    @Column(name = "beneficiario", length = 200)
    private String beneficiario;

    @Size(max = 4)
    @Column(name = "modalita_pagamento", length = 4)
    private String modalitaPagamento;

    @Size(max = 10)
    @Column(name = "data_riferimento_termini_pagamento", length = 10)
    private String dataRiferimentoTerminiPagamento;

    @Size(max = 5)
    @Column(name = "giorni_termini_pagamento", length = 5)
    private String giorniTerminiPagamento;

    @Size(max = 10)
    @Column(name = "data_scadenza_pagamento", length = 10)
    private String dataScadenzaPagamento;

    @Size(max = 15)
    @Column(name = "importo_pagamento", length = 15)
    private String importoPagamento;

    @Size(max = 20)
    @Column(name = "cod_ufficio_postale", length = 20)
    private String codUfficioPostale;

    @Size(max = 60)
    @Column(name = "cognome_quietanzante", length = 60)
    private String cognomeQuietanzante;

    @Size(max = 60)
    @Column(name = "nome_quietanzante", length = 60)
    private String nomeQuietanzante;

    @Size(max = 16)
    @Column(name = "c_f_quietanzante", length = 16)
    private String cFQuietanzante;

    @Size(max = 10)
    @Column(name = "titolo_quietanzante", length = 10)
    private String titoloQuietanzante;

    @Size(max = 80)
    @Column(name = "istituto_finanziario", length = 80)
    private String istitutoFinanziario;

    @Size(max = 34)
    @Column(name = "i_ban", length = 34)
    private String iBAN;

    @Size(max = 5)
    @Column(name = "a_bi", length = 5)
    private String aBI;

    @Size(max = 5)
    @Column(name = "c_ab", length = 5)
    private String cAB;

    @Size(max = 11)
    @Column(name = "b_ic", length = 11)
    private String bIC;

    @Size(max = 15)
    @Column(name = "sconto_pagamento_anticipato", length = 15)
    private String scontoPagamentoAnticipato;

    @Size(max = 10)
    @Column(name = "data_limite_pagamento_anticipato", length = 10)
    private String dataLimitePagamentoAnticipato;

    @Size(max = 15)
    @Column(name = "penalita_pagamenti_ritardati", length = 15)
    private String penalitaPagamentiRitardati;

    @Size(max = 10)
    @Column(name = "data_decorrenza_penale", length = 10)
    private String dataDecorrenzaPenale;

    @Size(max = 60)
    @Column(name = "codice_pagamento", length = 60)
    private String codicePagamento;

    @ManyToOne
    @JsonIgnoreProperties("dettaglioPagamentos")
    private DatiPagamento datiPagamento;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public DettaglioPagamento beneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
        return this;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getModalitaPagamento() {
        return modalitaPagamento;
    }

    public DettaglioPagamento modalitaPagamento(String modalitaPagamento) {
        this.modalitaPagamento = modalitaPagamento;
        return this;
    }

    public void setModalitaPagamento(String modalitaPagamento) {
        this.modalitaPagamento = modalitaPagamento;
    }

    public String getDataRiferimentoTerminiPagamento() {
        return dataRiferimentoTerminiPagamento;
    }

    public DettaglioPagamento dataRiferimentoTerminiPagamento(String dataRiferimentoTerminiPagamento) {
        this.dataRiferimentoTerminiPagamento = dataRiferimentoTerminiPagamento;
        return this;
    }

    public void setDataRiferimentoTerminiPagamento(String dataRiferimentoTerminiPagamento) {
        this.dataRiferimentoTerminiPagamento = dataRiferimentoTerminiPagamento;
    }

    public String getGiorniTerminiPagamento() {
        return giorniTerminiPagamento;
    }

    public DettaglioPagamento giorniTerminiPagamento(String giorniTerminiPagamento) {
        this.giorniTerminiPagamento = giorniTerminiPagamento;
        return this;
    }

    public void setGiorniTerminiPagamento(String giorniTerminiPagamento) {
        this.giorniTerminiPagamento = giorniTerminiPagamento;
    }

    public String getDataScadenzaPagamento() {
        return dataScadenzaPagamento;
    }

    public DettaglioPagamento dataScadenzaPagamento(String dataScadenzaPagamento) {
        this.dataScadenzaPagamento = dataScadenzaPagamento;
        return this;
    }

    public void setDataScadenzaPagamento(String dataScadenzaPagamento) {
        this.dataScadenzaPagamento = dataScadenzaPagamento;
    }

    public String getImportoPagamento() {
        return importoPagamento;
    }

    public DettaglioPagamento importoPagamento(String importoPagamento) {
        this.importoPagamento = importoPagamento;
        return this;
    }

    public void setImportoPagamento(String importoPagamento) {
        this.importoPagamento = importoPagamento;
    }

    public String getCodUfficioPostale() {
        return codUfficioPostale;
    }

    public DettaglioPagamento codUfficioPostale(String codUfficioPostale) {
        this.codUfficioPostale = codUfficioPostale;
        return this;
    }

    public void setCodUfficioPostale(String codUfficioPostale) {
        this.codUfficioPostale = codUfficioPostale;
    }

    public String getCognomeQuietanzante() {
        return cognomeQuietanzante;
    }

    public DettaglioPagamento cognomeQuietanzante(String cognomeQuietanzante) {
        this.cognomeQuietanzante = cognomeQuietanzante;
        return this;
    }

    public void setCognomeQuietanzante(String cognomeQuietanzante) {
        this.cognomeQuietanzante = cognomeQuietanzante;
    }

    public String getNomeQuietanzante() {
        return nomeQuietanzante;
    }

    public DettaglioPagamento nomeQuietanzante(String nomeQuietanzante) {
        this.nomeQuietanzante = nomeQuietanzante;
        return this;
    }

    public void setNomeQuietanzante(String nomeQuietanzante) {
        this.nomeQuietanzante = nomeQuietanzante;
    }

    public String getcFQuietanzante() {
        return cFQuietanzante;
    }

    public DettaglioPagamento cFQuietanzante(String cFQuietanzante) {
        this.cFQuietanzante = cFQuietanzante;
        return this;
    }

    public void setcFQuietanzante(String cFQuietanzante) {
        this.cFQuietanzante = cFQuietanzante;
    }

    public String getTitoloQuietanzante() {
        return titoloQuietanzante;
    }

    public DettaglioPagamento titoloQuietanzante(String titoloQuietanzante) {
        this.titoloQuietanzante = titoloQuietanzante;
        return this;
    }

    public void setTitoloQuietanzante(String titoloQuietanzante) {
        this.titoloQuietanzante = titoloQuietanzante;
    }

    public String getIstitutoFinanziario() {
        return istitutoFinanziario;
    }

    public DettaglioPagamento istitutoFinanziario(String istitutoFinanziario) {
        this.istitutoFinanziario = istitutoFinanziario;
        return this;
    }

    public void setIstitutoFinanziario(String istitutoFinanziario) {
        this.istitutoFinanziario = istitutoFinanziario;
    }

    public String getiBAN() {
        return iBAN;
    }

    public DettaglioPagamento iBAN(String iBAN) {
        this.iBAN = iBAN;
        return this;
    }

    public void setiBAN(String iBAN) {
        this.iBAN = iBAN;
    }

    public String getaBI() {
        return aBI;
    }

    public DettaglioPagamento aBI(String aBI) {
        this.aBI = aBI;
        return this;
    }

    public void setaBI(String aBI) {
        this.aBI = aBI;
    }

    public String getcAB() {
        return cAB;
    }

    public DettaglioPagamento cAB(String cAB) {
        this.cAB = cAB;
        return this;
    }

    public void setcAB(String cAB) {
        this.cAB = cAB;
    }

    public String getbIC() {
        return bIC;
    }

    public DettaglioPagamento bIC(String bIC) {
        this.bIC = bIC;
        return this;
    }

    public void setbIC(String bIC) {
        this.bIC = bIC;
    }

    public String getScontoPagamentoAnticipato() {
        return scontoPagamentoAnticipato;
    }

    public DettaglioPagamento scontoPagamentoAnticipato(String scontoPagamentoAnticipato) {
        this.scontoPagamentoAnticipato = scontoPagamentoAnticipato;
        return this;
    }

    public void setScontoPagamentoAnticipato(String scontoPagamentoAnticipato) {
        this.scontoPagamentoAnticipato = scontoPagamentoAnticipato;
    }

    public String getDataLimitePagamentoAnticipato() {
        return dataLimitePagamentoAnticipato;
    }

    public DettaglioPagamento dataLimitePagamentoAnticipato(String dataLimitePagamentoAnticipato) {
        this.dataLimitePagamentoAnticipato = dataLimitePagamentoAnticipato;
        return this;
    }

    public void setDataLimitePagamentoAnticipato(String dataLimitePagamentoAnticipato) {
        this.dataLimitePagamentoAnticipato = dataLimitePagamentoAnticipato;
    }

    public String getPenalitaPagamentiRitardati() {
        return penalitaPagamentiRitardati;
    }

    public DettaglioPagamento penalitaPagamentiRitardati(String penalitaPagamentiRitardati) {
        this.penalitaPagamentiRitardati = penalitaPagamentiRitardati;
        return this;
    }

    public void setPenalitaPagamentiRitardati(String penalitaPagamentiRitardati) {
        this.penalitaPagamentiRitardati = penalitaPagamentiRitardati;
    }

    public String getDataDecorrenzaPenale() {
        return dataDecorrenzaPenale;
    }

    public DettaglioPagamento dataDecorrenzaPenale(String dataDecorrenzaPenale) {
        this.dataDecorrenzaPenale = dataDecorrenzaPenale;
        return this;
    }

    public void setDataDecorrenzaPenale(String dataDecorrenzaPenale) {
        this.dataDecorrenzaPenale = dataDecorrenzaPenale;
    }

    public String getCodicePagamento() {
        return codicePagamento;
    }

    public DettaglioPagamento codicePagamento(String codicePagamento) {
        this.codicePagamento = codicePagamento;
        return this;
    }

    public void setCodicePagamento(String codicePagamento) {
        this.codicePagamento = codicePagamento;
    }

    public DatiPagamento getDatiPagamento() {
        return datiPagamento;
    }

    public DettaglioPagamento datiPagamento(DatiPagamento datiPagamento) {
        this.datiPagamento = datiPagamento;
        return this;
    }

    public void setDatiPagamento(DatiPagamento datiPagamento) {
        this.datiPagamento = datiPagamento;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DettaglioPagamento)) {
            return false;
        }
        return id != null && id.equals(((DettaglioPagamento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DettaglioPagamento{" +
            "id=" + getId() +
            ", beneficiario='" + getBeneficiario() + "'" +
            ", modalitaPagamento='" + getModalitaPagamento() + "'" +
            ", dataRiferimentoTerminiPagamento='" + getDataRiferimentoTerminiPagamento() + "'" +
            ", giorniTerminiPagamento='" + getGiorniTerminiPagamento() + "'" +
            ", dataScadenzaPagamento='" + getDataScadenzaPagamento() + "'" +
            ", importoPagamento='" + getImportoPagamento() + "'" +
            ", codUfficioPostale='" + getCodUfficioPostale() + "'" +
            ", cognomeQuietanzante='" + getCognomeQuietanzante() + "'" +
            ", nomeQuietanzante='" + getNomeQuietanzante() + "'" +
            ", cFQuietanzante='" + getcFQuietanzante() + "'" +
            ", titoloQuietanzante='" + getTitoloQuietanzante() + "'" +
            ", istitutoFinanziario='" + getIstitutoFinanziario() + "'" +
            ", iBAN='" + getiBAN() + "'" +
            ", aBI='" + getaBI() + "'" +
            ", cAB='" + getcAB() + "'" +
            ", bIC='" + getbIC() + "'" +
            ", scontoPagamentoAnticipato='" + getScontoPagamentoAnticipato() + "'" +
            ", dataLimitePagamentoAnticipato='" + getDataLimitePagamentoAnticipato() + "'" +
            ", penalitaPagamentiRitardati='" + getPenalitaPagamentiRitardati() + "'" +
            ", dataDecorrenzaPenale='" + getDataDecorrenzaPenale() + "'" +
            ", codicePagamento='" + getCodicePagamento() + "'" +
            "}";
    }
}
