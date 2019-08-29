package net.anet.invoice.repo.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DettaglioLinea.
 */
@Entity
@Table(name = "dettaglio_linea")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DettaglioLinea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4)
    @Column(name = "numero_linea", length = 4)
    private String numeroLinea;

    @Size(max = 2)
    @Column(name = "tipo_cessione_prestazione", length = 2)
    private String tipoCessionePrestazione;

    @Size(max = 1000)
    @Column(name = "d_l_descrizione", length = 1000)
    private String dLDescrizione;

    @Size(max = 21)
    @Column(name = "quantita", length = 21)
    private String quantita;

    @Size(max = 10)
    @Column(name = "unita_misura", length = 10)
    private String unitaMisura;

    @Size(max = 10)
    @Column(name = "data_inizio_periodo", length = 10)
    private String dataInizioPeriodo;

    @Size(max = 10)
    @Column(name = "data_fine_periodo", length = 10)
    private String dataFinePeriodo;

    @Size(max = 21)
    @Column(name = "prezzo_unitario", length = 21)
    private String prezzoUnitario;

    @Size(max = 21)
    @Column(name = "s_m_prezzo_totale", length = 21)
    private String sMPrezzoTotale;

    @Size(max = 6)
    @Column(name = "s_m_aliquota_iva", length = 6)
    private String sMAliquotaIVA;

    @Size(max = 2)
    @Column(name = "s_m_ritenuta", length = 2)
    private String sMRitenuta;

    @Size(max = 2)
    @Column(name = "s_m_natura", length = 2)
    private String sMNatura;

    @Size(max = 20)
    @Column(name = "s_m_riferimento_amministrazione", length = 20)
    private String sMRiferimentoAmministrazione;

    @OneToMany(mappedBy = "dettaglioLinea")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CodiceArticolo> codiceArticolos = new HashSet<>();

    @OneToMany(mappedBy = "dettaglioLinea")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ScontoMaggiorazione> scontoMaggioraziones = new HashSet<>();

    @OneToMany(mappedBy = "dettaglioLinea")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AltriDatiGestionali> altriDatiGestionalis = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("dettaglioLineas")
    private FABody fABody;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroLinea() {
        return numeroLinea;
    }

    public DettaglioLinea numeroLinea(String numeroLinea) {
        this.numeroLinea = numeroLinea;
        return this;
    }

    public void setNumeroLinea(String numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public String getTipoCessionePrestazione() {
        return tipoCessionePrestazione;
    }

    public DettaglioLinea tipoCessionePrestazione(String tipoCessionePrestazione) {
        this.tipoCessionePrestazione = tipoCessionePrestazione;
        return this;
    }

    public void setTipoCessionePrestazione(String tipoCessionePrestazione) {
        this.tipoCessionePrestazione = tipoCessionePrestazione;
    }

    public String getdLDescrizione() {
        return dLDescrizione;
    }

    public DettaglioLinea dLDescrizione(String dLDescrizione) {
        this.dLDescrizione = dLDescrizione;
        return this;
    }

    public void setdLDescrizione(String dLDescrizione) {
        this.dLDescrizione = dLDescrizione;
    }

    public String getQuantita() {
        return quantita;
    }

    public DettaglioLinea quantita(String quantita) {
        this.quantita = quantita;
        return this;
    }

    public void setQuantita(String quantita) {
        this.quantita = quantita;
    }

    public String getUnitaMisura() {
        return unitaMisura;
    }

    public DettaglioLinea unitaMisura(String unitaMisura) {
        this.unitaMisura = unitaMisura;
        return this;
    }

    public void setUnitaMisura(String unitaMisura) {
        this.unitaMisura = unitaMisura;
    }

    public String getDataInizioPeriodo() {
        return dataInizioPeriodo;
    }

    public DettaglioLinea dataInizioPeriodo(String dataInizioPeriodo) {
        this.dataInizioPeriodo = dataInizioPeriodo;
        return this;
    }

    public void setDataInizioPeriodo(String dataInizioPeriodo) {
        this.dataInizioPeriodo = dataInizioPeriodo;
    }

    public String getDataFinePeriodo() {
        return dataFinePeriodo;
    }

    public DettaglioLinea dataFinePeriodo(String dataFinePeriodo) {
        this.dataFinePeriodo = dataFinePeriodo;
        return this;
    }

    public void setDataFinePeriodo(String dataFinePeriodo) {
        this.dataFinePeriodo = dataFinePeriodo;
    }

    public String getPrezzoUnitario() {
        return prezzoUnitario;
    }

    public DettaglioLinea prezzoUnitario(String prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
        return this;
    }

    public void setPrezzoUnitario(String prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }

    public String getsMPrezzoTotale() {
        return sMPrezzoTotale;
    }

    public DettaglioLinea sMPrezzoTotale(String sMPrezzoTotale) {
        this.sMPrezzoTotale = sMPrezzoTotale;
        return this;
    }

    public void setsMPrezzoTotale(String sMPrezzoTotale) {
        this.sMPrezzoTotale = sMPrezzoTotale;
    }

    public String getsMAliquotaIVA() {
        return sMAliquotaIVA;
    }

    public DettaglioLinea sMAliquotaIVA(String sMAliquotaIVA) {
        this.sMAliquotaIVA = sMAliquotaIVA;
        return this;
    }

    public void setsMAliquotaIVA(String sMAliquotaIVA) {
        this.sMAliquotaIVA = sMAliquotaIVA;
    }

    public String getsMRitenuta() {
        return sMRitenuta;
    }

    public DettaglioLinea sMRitenuta(String sMRitenuta) {
        this.sMRitenuta = sMRitenuta;
        return this;
    }

    public void setsMRitenuta(String sMRitenuta) {
        this.sMRitenuta = sMRitenuta;
    }

    public String getsMNatura() {
        return sMNatura;
    }

    public DettaglioLinea sMNatura(String sMNatura) {
        this.sMNatura = sMNatura;
        return this;
    }

    public void setsMNatura(String sMNatura) {
        this.sMNatura = sMNatura;
    }

    public String getsMRiferimentoAmministrazione() {
        return sMRiferimentoAmministrazione;
    }

    public DettaglioLinea sMRiferimentoAmministrazione(String sMRiferimentoAmministrazione) {
        this.sMRiferimentoAmministrazione = sMRiferimentoAmministrazione;
        return this;
    }

    public void setsMRiferimentoAmministrazione(String sMRiferimentoAmministrazione) {
        this.sMRiferimentoAmministrazione = sMRiferimentoAmministrazione;
    }

    public Set<CodiceArticolo> getCodiceArticolos() {
        return codiceArticolos;
    }

    public DettaglioLinea codiceArticolos(Set<CodiceArticolo> codiceArticolos) {
        this.codiceArticolos = codiceArticolos;
        return this;
    }

    public DettaglioLinea addCodiceArticolo(CodiceArticolo codiceArticolo) {
        this.codiceArticolos.add(codiceArticolo);
        codiceArticolo.setDettaglioLinea(this);
        return this;
    }

    public DettaglioLinea removeCodiceArticolo(CodiceArticolo codiceArticolo) {
        this.codiceArticolos.remove(codiceArticolo);
        codiceArticolo.setDettaglioLinea(null);
        return this;
    }

    public void setCodiceArticolos(Set<CodiceArticolo> codiceArticolos) {
        this.codiceArticolos = codiceArticolos;
    }

    public Set<ScontoMaggiorazione> getScontoMaggioraziones() {
        return scontoMaggioraziones;
    }

    public DettaglioLinea scontoMaggioraziones(Set<ScontoMaggiorazione> scontoMaggioraziones) {
        this.scontoMaggioraziones = scontoMaggioraziones;
        return this;
    }

    public DettaglioLinea addScontoMaggiorazione(ScontoMaggiorazione scontoMaggiorazione) {
        this.scontoMaggioraziones.add(scontoMaggiorazione);
        scontoMaggiorazione.setDettaglioLinea(this);
        return this;
    }

    public DettaglioLinea removeScontoMaggiorazione(ScontoMaggiorazione scontoMaggiorazione) {
        this.scontoMaggioraziones.remove(scontoMaggiorazione);
        scontoMaggiorazione.setDettaglioLinea(null);
        return this;
    }

    public void setScontoMaggioraziones(Set<ScontoMaggiorazione> scontoMaggioraziones) {
        this.scontoMaggioraziones = scontoMaggioraziones;
    }

    public Set<AltriDatiGestionali> getAltriDatiGestionalis() {
        return altriDatiGestionalis;
    }

    public DettaglioLinea altriDatiGestionalis(Set<AltriDatiGestionali> altriDatiGestionalis) {
        this.altriDatiGestionalis = altriDatiGestionalis;
        return this;
    }

    public DettaglioLinea addAltriDatiGestionali(AltriDatiGestionali altriDatiGestionali) {
        this.altriDatiGestionalis.add(altriDatiGestionali);
        altriDatiGestionali.setDettaglioLinea(this);
        return this;
    }

    public DettaglioLinea removeAltriDatiGestionali(AltriDatiGestionali altriDatiGestionali) {
        this.altriDatiGestionalis.remove(altriDatiGestionali);
        altriDatiGestionali.setDettaglioLinea(null);
        return this;
    }

    public void setAltriDatiGestionalis(Set<AltriDatiGestionali> altriDatiGestionalis) {
        this.altriDatiGestionalis = altriDatiGestionalis;
    }

    public FABody getFABody() {
        return fABody;
    }

    public DettaglioLinea fABody(FABody fABody) {
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
        if (!(o instanceof DettaglioLinea)) {
            return false;
        }
        return id != null && id.equals(((DettaglioLinea) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DettaglioLinea{" +
            "id=" + getId() +
            ", numeroLinea='" + getNumeroLinea() + "'" +
            ", tipoCessionePrestazione='" + getTipoCessionePrestazione() + "'" +
            ", dLDescrizione='" + getdLDescrizione() + "'" +
            ", quantita='" + getQuantita() + "'" +
            ", unitaMisura='" + getUnitaMisura() + "'" +
            ", dataInizioPeriodo='" + getDataInizioPeriodo() + "'" +
            ", dataFinePeriodo='" + getDataFinePeriodo() + "'" +
            ", prezzoUnitario='" + getPrezzoUnitario() + "'" +
            ", sMPrezzoTotale='" + getsMPrezzoTotale() + "'" +
            ", sMAliquotaIVA='" + getsMAliquotaIVA() + "'" +
            ", sMRitenuta='" + getsMRitenuta() + "'" +
            ", sMNatura='" + getsMNatura() + "'" +
            ", sMRiferimentoAmministrazione='" + getsMRiferimentoAmministrazione() + "'" +
            "}";
    }
}
