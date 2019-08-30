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
 * A DatiPagamento.
 */
@Entity
@Table(name = "dati_pagamento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DatiPagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4)
    @Column(name = "condizioni_pagamento", length = 4)
    private String condizioniPagamento;

    @OneToMany(mappedBy = "datiPagamento" , cascade = {CascadeType.ALL} , orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DettaglioPagamento> dettaglioPagamentos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("datiPagamentos")
    private FABody fABody;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCondizioniPagamento() {
        return condizioniPagamento;
    }

    public DatiPagamento condizioniPagamento(String condizioniPagamento) {
        this.condizioniPagamento = condizioniPagamento;
        return this;
    }

    public void setCondizioniPagamento(String condizioniPagamento) {
        this.condizioniPagamento = condizioniPagamento;
    }

    public Set<DettaglioPagamento> getDettaglioPagamentos() {
        return dettaglioPagamentos;
    }

    public DatiPagamento dettaglioPagamentos(Set<DettaglioPagamento> dettaglioPagamentos) {
        this.dettaglioPagamentos = dettaglioPagamentos;
        return this;
    }

    public DatiPagamento addDettaglioPagamento(DettaglioPagamento dettaglioPagamento) {
        this.dettaglioPagamentos.add(dettaglioPagamento);
        dettaglioPagamento.setDatiPagamento(this);
        return this;
    }

    public DatiPagamento removeDettaglioPagamento(DettaglioPagamento dettaglioPagamento) {
        this.dettaglioPagamentos.remove(dettaglioPagamento);
        dettaglioPagamento.setDatiPagamento(null);
        return this;
    }

    public void setDettaglioPagamentos(Set<DettaglioPagamento> dettaglioPagamentos) {
        this.dettaglioPagamentos = dettaglioPagamentos;
    }

    public FABody getFABody() {
        return fABody;
    }

    public DatiPagamento fABody(FABody fABody) {
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
        if (!(o instanceof DatiPagamento)) {
            return false;
        }
        return id != null && id.equals(((DatiPagamento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DatiPagamento{" +
            "id=" + getId() +
            ", condizioniPagamento='" + getCondizioniPagamento() + "'" +
            "}";
    }
}
