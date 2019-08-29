package net.anet.invoice.repo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A NumeroLinea.
 */
@Entity
@Table(name = "numero_linea")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NumeroLinea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4)
    @Column(name = "riferimento_numero_linea", length = 4)
    private String riferimentoNumeroLinea;

    @ManyToOne
    @JsonIgnoreProperties("numeroLineas")
    private OrdineAcquisto ordineAcquisto;

    @ManyToOne
    @JsonIgnoreProperties("numeroLineas")
    private DDT dDT;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRiferimentoNumeroLinea() {
        return riferimentoNumeroLinea;
    }

    public NumeroLinea riferimentoNumeroLinea(String riferimentoNumeroLinea) {
        this.riferimentoNumeroLinea = riferimentoNumeroLinea;
        return this;
    }

    public void setRiferimentoNumeroLinea(String riferimentoNumeroLinea) {
        this.riferimentoNumeroLinea = riferimentoNumeroLinea;
    }

    public OrdineAcquisto getOrdineAcquisto() {
        return ordineAcquisto;
    }

    public NumeroLinea ordineAcquisto(OrdineAcquisto ordineAcquisto) {
        this.ordineAcquisto = ordineAcquisto;
        return this;
    }

    public void setOrdineAcquisto(OrdineAcquisto ordineAcquisto) {
        this.ordineAcquisto = ordineAcquisto;
    }

    public DDT getDDT() {
        return dDT;
    }

    public NumeroLinea dDT(DDT dDT) {
        this.dDT = dDT;
        return this;
    }

    public void setDDT(DDT dDT) {
        this.dDT = dDT;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NumeroLinea)) {
            return false;
        }
        return id != null && id.equals(((NumeroLinea) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NumeroLinea{" +
            "id=" + getId() +
            ", riferimentoNumeroLinea='" + getRiferimentoNumeroLinea() + "'" +
            "}";
    }
}
