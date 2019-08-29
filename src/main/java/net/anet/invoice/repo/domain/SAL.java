package net.anet.invoice.repo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A SAL.
 */
@Entity
@Table(name = "sal")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SAL implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 3)
    @Column(name = "riferimento_fase", length = 3)
    private String riferimentoFase;

    @ManyToOne
    @JsonIgnoreProperties("sALS")
    private FABody fABody;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRiferimentoFase() {
        return riferimentoFase;
    }

    public SAL riferimentoFase(String riferimentoFase) {
        this.riferimentoFase = riferimentoFase;
        return this;
    }

    public void setRiferimentoFase(String riferimentoFase) {
        this.riferimentoFase = riferimentoFase;
    }

    public FABody getFABody() {
        return fABody;
    }

    public SAL fABody(FABody fABody) {
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
        if (!(o instanceof SAL)) {
            return false;
        }
        return id != null && id.equals(((SAL) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SAL{" +
            "id=" + getId() +
            ", riferimentoFase='" + getRiferimentoFase() + "'" +
            "}";
    }
}
