package net.anet.invoice.repo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Causale.
 */
@Entity
@Table(name = "causale")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Causale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 200)
    @Column(name = "causale", length = 200)
    private String causale;

    @ManyToOne
    @JsonIgnoreProperties("causales")
    private FABody fABody;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCausale() {
        return causale;
    }

    public Causale causale(String causale) {
        this.causale = causale;
        return this;
    }

    public void setCausale(String causale) {
        this.causale = causale;
    }

    public FABody getFABody() {
        return fABody;
    }

    public Causale fABody(FABody fABody) {
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
        if (!(o instanceof Causale)) {
            return false;
        }
        return id != null && id.equals(((Causale) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Causale{" +
            "id=" + getId() +
            ", causale='" + getCausale() + "'" +
            "}";
    }
}
