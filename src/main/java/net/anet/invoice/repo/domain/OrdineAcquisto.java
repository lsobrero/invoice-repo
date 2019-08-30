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
 * A OrdineAcquisto.
 */
@Entity
@Table(name = "ordine_acquisto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrdineAcquisto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 20)
    @Column(name = "id_documento", length = 20)
    private String idDocumento;

    @Size(max = 10)
    @Column(name = "d_oa_data", length = 10)
    private String dOAData;

    @Size(max = 20)
    @Column(name = "num_item", length = 20)
    private String numItem;

    @Size(max = 100)
    @Column(name = "codice_commessa_convenzione", length = 100)
    private String codiceCommessaConvenzione;

    @Size(max = 15)
    @Column(name = "codice_cup", length = 15)
    private String codiceCUP;

    @Size(max = 15)
    @Column(name = "codice_cig", length = 15)
    private String codiceCIG;

    @OneToMany(mappedBy = "ordineAcquisto" , cascade = {CascadeType.ALL} , orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NumeroLinea> numeroLineas = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("ordineAcquistos")
    private FABody fABody;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public OrdineAcquisto idDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
        return this;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getdOAData() {
        return dOAData;
    }

    public OrdineAcquisto dOAData(String dOAData) {
        this.dOAData = dOAData;
        return this;
    }

    public void setdOAData(String dOAData) {
        this.dOAData = dOAData;
    }

    public String getNumItem() {
        return numItem;
    }

    public OrdineAcquisto numItem(String numItem) {
        this.numItem = numItem;
        return this;
    }

    public void setNumItem(String numItem) {
        this.numItem = numItem;
    }

    public String getCodiceCommessaConvenzione() {
        return codiceCommessaConvenzione;
    }

    public OrdineAcquisto codiceCommessaConvenzione(String codiceCommessaConvenzione) {
        this.codiceCommessaConvenzione = codiceCommessaConvenzione;
        return this;
    }

    public void setCodiceCommessaConvenzione(String codiceCommessaConvenzione) {
        this.codiceCommessaConvenzione = codiceCommessaConvenzione;
    }

    public String getCodiceCUP() {
        return codiceCUP;
    }

    public OrdineAcquisto codiceCUP(String codiceCUP) {
        this.codiceCUP = codiceCUP;
        return this;
    }

    public void setCodiceCUP(String codiceCUP) {
        this.codiceCUP = codiceCUP;
    }

    public String getCodiceCIG() {
        return codiceCIG;
    }

    public OrdineAcquisto codiceCIG(String codiceCIG) {
        this.codiceCIG = codiceCIG;
        return this;
    }

    public void setCodiceCIG(String codiceCIG) {
        this.codiceCIG = codiceCIG;
    }

    public Set<NumeroLinea> getNumeroLineas() {
        return numeroLineas;
    }

    public OrdineAcquisto numeroLineas(Set<NumeroLinea> numeroLineas) {
        this.numeroLineas = numeroLineas;
        return this;
    }

    public OrdineAcquisto addNumeroLinea(NumeroLinea numeroLinea) {
        this.numeroLineas.add(numeroLinea);
        numeroLinea.setOrdineAcquisto(this);
        return this;
    }

    public OrdineAcquisto removeNumeroLinea(NumeroLinea numeroLinea) {
        this.numeroLineas.remove(numeroLinea);
        numeroLinea.setOrdineAcquisto(null);
        return this;
    }

    public void setNumeroLineas(Set<NumeroLinea> numeroLineas) {
        this.numeroLineas = numeroLineas;
    }

    public FABody getFABody() {
        return fABody;
    }

    public OrdineAcquisto fABody(FABody fABody) {
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
        if (!(o instanceof OrdineAcquisto)) {
            return false;
        }
        return id != null && id.equals(((OrdineAcquisto) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrdineAcquisto{" +
            "id=" + getId() +
            ", idDocumento='" + getIdDocumento() + "'" +
            ", dOAData='" + getdOAData() + "'" +
            ", numItem='" + getNumItem() + "'" +
            ", codiceCommessaConvenzione='" + getCodiceCommessaConvenzione() + "'" +
            ", codiceCUP='" + getCodiceCUP() + "'" +
            ", codiceCIG='" + getCodiceCIG() + "'" +
            "}";
    }
}
