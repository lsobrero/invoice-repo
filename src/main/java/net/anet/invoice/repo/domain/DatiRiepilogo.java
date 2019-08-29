package net.anet.invoice.repo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DatiRiepilogo.
 */
@Entity
@Table(name = "dati_riepilogo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DatiRiepilogo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 6)
    @Column(name = "d_r_aliquota_iva", length = 6)
    private String dRAliquotaIVA;

    @Size(max = 2)
    @Column(name = "d_r_natura", length = 2)
    private String dRNatura;

    @Size(max = 15)
    @Column(name = "d_r_spese_accessorie", length = 15)
    private String dRSpeseAccessorie;

    @Size(max = 21)
    @Column(name = "d_r_arrotondamento", length = 21)
    private String dRArrotondamento;

    @Size(max = 15)
    @Column(name = "d_r_imponibile_importo", length = 15)
    private String dRImponibileImporto;

    @Size(max = 15)
    @Column(name = "d_r_imposta", length = 15)
    private String dRImposta;

    @Size(max = 1)
    @Column(name = "d_r_esigibilita_iva", length = 1)
    private String dREsigibilitaIVA;

    @Size(max = 100)
    @Column(name = "d_r_riferimento_normativo", length = 100)
    private String dRRiferimentoNormativo;

    @ManyToOne
    @JsonIgnoreProperties("datiRiepilogos")
    private FABody fABody;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getdRAliquotaIVA() {
        return dRAliquotaIVA;
    }

    public DatiRiepilogo dRAliquotaIVA(String dRAliquotaIVA) {
        this.dRAliquotaIVA = dRAliquotaIVA;
        return this;
    }

    public void setdRAliquotaIVA(String dRAliquotaIVA) {
        this.dRAliquotaIVA = dRAliquotaIVA;
    }

    public String getdRNatura() {
        return dRNatura;
    }

    public DatiRiepilogo dRNatura(String dRNatura) {
        this.dRNatura = dRNatura;
        return this;
    }

    public void setdRNatura(String dRNatura) {
        this.dRNatura = dRNatura;
    }

    public String getdRSpeseAccessorie() {
        return dRSpeseAccessorie;
    }

    public DatiRiepilogo dRSpeseAccessorie(String dRSpeseAccessorie) {
        this.dRSpeseAccessorie = dRSpeseAccessorie;
        return this;
    }

    public void setdRSpeseAccessorie(String dRSpeseAccessorie) {
        this.dRSpeseAccessorie = dRSpeseAccessorie;
    }

    public String getdRArrotondamento() {
        return dRArrotondamento;
    }

    public DatiRiepilogo dRArrotondamento(String dRArrotondamento) {
        this.dRArrotondamento = dRArrotondamento;
        return this;
    }

    public void setdRArrotondamento(String dRArrotondamento) {
        this.dRArrotondamento = dRArrotondamento;
    }

    public String getdRImponibileImporto() {
        return dRImponibileImporto;
    }

    public DatiRiepilogo dRImponibileImporto(String dRImponibileImporto) {
        this.dRImponibileImporto = dRImponibileImporto;
        return this;
    }

    public void setdRImponibileImporto(String dRImponibileImporto) {
        this.dRImponibileImporto = dRImponibileImporto;
    }

    public String getdRImposta() {
        return dRImposta;
    }

    public DatiRiepilogo dRImposta(String dRImposta) {
        this.dRImposta = dRImposta;
        return this;
    }

    public void setdRImposta(String dRImposta) {
        this.dRImposta = dRImposta;
    }

    public String getdREsigibilitaIVA() {
        return dREsigibilitaIVA;
    }

    public DatiRiepilogo dREsigibilitaIVA(String dREsigibilitaIVA) {
        this.dREsigibilitaIVA = dREsigibilitaIVA;
        return this;
    }

    public void setdREsigibilitaIVA(String dREsigibilitaIVA) {
        this.dREsigibilitaIVA = dREsigibilitaIVA;
    }

    public String getdRRiferimentoNormativo() {
        return dRRiferimentoNormativo;
    }

    public DatiRiepilogo dRRiferimentoNormativo(String dRRiferimentoNormativo) {
        this.dRRiferimentoNormativo = dRRiferimentoNormativo;
        return this;
    }

    public void setdRRiferimentoNormativo(String dRRiferimentoNormativo) {
        this.dRRiferimentoNormativo = dRRiferimentoNormativo;
    }

    public FABody getFABody() {
        return fABody;
    }

    public DatiRiepilogo fABody(FABody fABody) {
        this.fABody = fABody;
        return this;
    }

    public void setFABody(FABody fABody) {
        this.fABody = fABody;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DatiRiepilogo)) {
            return false;
        }
        return id != null && id.equals(((DatiRiepilogo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DatiRiepilogo{" +
            "id=" + getId() +
            ", dRAliquotaIVA='" + getdRAliquotaIVA() + "'" +
            ", dRNatura='" + getdRNatura() + "'" +
            ", dRSpeseAccessorie='" + getdRSpeseAccessorie() + "'" +
            ", dRArrotondamento='" + getdRArrotondamento() + "'" +
            ", dRImponibileImporto='" + getdRImponibileImporto() + "'" +
            ", dRImposta='" + getdRImposta() + "'" +
            ", dREsigibilitaIVA='" + getdREsigibilitaIVA() + "'" +
            ", dRRiferimentoNormativo='" + getdRRiferimentoNormativo() + "'" +
            "}";
    }
}
