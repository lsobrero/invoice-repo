package net.anet.invoice.repo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CodiceArticolo.
 */
@Entity
@Table(name = "codice_articolo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CodiceArticolo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 35)
    @Column(name = "codice_tipo", length = 35)
    private String codiceTipo;

    @Size(max = 35)
    @Column(name = "codice_valore", length = 35)
    private String codiceValore;

    @ManyToOne
    @JsonIgnoreProperties("codiceArticolos")
    private DettaglioLinea dettaglioLinea;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodiceTipo() {
        return codiceTipo;
    }

    public CodiceArticolo codiceTipo(String codiceTipo) {
        this.codiceTipo = codiceTipo;
        return this;
    }

    public void setCodiceTipo(String codiceTipo) {
        this.codiceTipo = codiceTipo;
    }

    public String getCodiceValore() {
        return codiceValore;
    }

    public CodiceArticolo codiceValore(String codiceValore) {
        this.codiceValore = codiceValore;
        return this;
    }

    public void setCodiceValore(String codiceValore) {
        this.codiceValore = codiceValore;
    }

    public DettaglioLinea getDettaglioLinea() {
        return dettaglioLinea;
    }

    public CodiceArticolo dettaglioLinea(DettaglioLinea dettaglioLinea) {
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
        if (!(o instanceof CodiceArticolo)) {
            return false;
        }
        return id != null && id.equals(((CodiceArticolo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CodiceArticolo{" +
            "id=" + getId() +
            ", codiceTipo='" + getCodiceTipo() + "'" +
            ", codiceValore='" + getCodiceValore() + "'" +
            "}";
    }
}
