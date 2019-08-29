package net.anet.invoice.repo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ScontoMaggiorazione.
 */
@Entity
@Table(name = "sconto_maggiorazione")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ScontoMaggiorazione implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 2)
    @Column(name = "s_m_tipo", length = 2)
    private String sMTipo;

    @Size(max = 6)
    @Column(name = "s_m_percentuale", length = 6)
    private String sMPercentuale;

    @Size(max = 15)
    @Column(name = "s_m_importo", length = 15)
    private String sMImporto;

    @ManyToOne
    @JsonIgnoreProperties("scontoMaggioraziones")
    private FABody fABody;

    @ManyToOne
    @JsonIgnoreProperties("scontoMaggioraziones")
    private DettaglioLinea dettaglioLinea;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getsMTipo() {
        return sMTipo;
    }

    public ScontoMaggiorazione sMTipo(String sMTipo) {
        this.sMTipo = sMTipo;
        return this;
    }

    public void setsMTipo(String sMTipo) {
        this.sMTipo = sMTipo;
    }

    public String getsMPercentuale() {
        return sMPercentuale;
    }

    public ScontoMaggiorazione sMPercentuale(String sMPercentuale) {
        this.sMPercentuale = sMPercentuale;
        return this;
    }

    public void setsMPercentuale(String sMPercentuale) {
        this.sMPercentuale = sMPercentuale;
    }

    public String getsMImporto() {
        return sMImporto;
    }

    public ScontoMaggiorazione sMImporto(String sMImporto) {
        this.sMImporto = sMImporto;
        return this;
    }

    public void setsMImporto(String sMImporto) {
        this.sMImporto = sMImporto;
    }

    public FABody getFABody() {
        return fABody;
    }

    public ScontoMaggiorazione fABody(FABody fABody) {
        this.fABody = fABody;
        return this;
    }

    public void setFABody(FABody fABody) {
        this.fABody = fABody;
    }

    public DettaglioLinea getDettaglioLinea() {
        return dettaglioLinea;
    }

    public ScontoMaggiorazione dettaglioLinea(DettaglioLinea dettaglioLinea) {
        this.dettaglioLinea = dettaglioLinea;
        return this;
    }

    public void setDettaglioLinea(DettaglioLinea dettaglioLinea) {
        this.dettaglioLinea = dettaglioLinea;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScontoMaggiorazione)) {
            return false;
        }
        return id != null && id.equals(((ScontoMaggiorazione) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ScontoMaggiorazione{" +
            "id=" + getId() +
            ", sMTipo='" + getsMTipo() + "'" +
            ", sMPercentuale='" + getsMPercentuale() + "'" +
            ", sMImporto='" + getsMImporto() + "'" +
            "}";
    }
}
