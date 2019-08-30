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
 * A DDT.
 */
@Entity
@Table(name = "ddt")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DDT implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 20)
    @Column(name = "numero_ddt", length = 20)
    private String numeroDDT;

    @Size(max = 10)
    @Column(name = "data_ddt", length = 10)
    private String dataDDT;

    @OneToMany(mappedBy = "dDT" , cascade = {CascadeType.ALL} , orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NumeroLinea> numeroLineas = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("dDTS")
    private FABody fABody;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDDT() {
        return numeroDDT;
    }

    public DDT numeroDDT(String numeroDDT) {
        this.numeroDDT = numeroDDT;
        return this;
    }

    public void setNumeroDDT(String numeroDDT) {
        this.numeroDDT = numeroDDT;
    }

    public String getDataDDT() {
        return dataDDT;
    }

    public DDT dataDDT(String dataDDT) {
        this.dataDDT = dataDDT;
        return this;
    }

    public void setDataDDT(String dataDDT) {
        this.dataDDT = dataDDT;
    }

    public Set<NumeroLinea> getNumeroLineas() {
        return numeroLineas;
    }

    public DDT numeroLineas(Set<NumeroLinea> numeroLineas) {
        this.numeroLineas = numeroLineas;
        return this;
    }

    public DDT addNumeroLinea(NumeroLinea numeroLinea) {
        this.numeroLineas.add(numeroLinea);
        numeroLinea.setDDT(this);
        return this;
    }

    public DDT removeNumeroLinea(NumeroLinea numeroLinea) {
        this.numeroLineas.remove(numeroLinea);
        numeroLinea.setDDT(null);
        return this;
    }

    public void setNumeroLineas(Set<NumeroLinea> numeroLineas) {
        this.numeroLineas = numeroLineas;
    }

    public FABody getFABody() {
        return fABody;
    }

    public DDT fABody(FABody fABody) {
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
        if (!(o instanceof DDT)) {
            return false;
        }
        return id != null && id.equals(((DDT) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DDT{" +
            "id=" + getId() +
            ", numeroDDT='" + getNumeroDDT() + "'" +
            ", dataDDT='" + getDataDDT() + "'" +
            "}";
    }
}
