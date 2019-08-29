package net.anet.invoice.repo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CassaPrevidenziale.
 */
@Entity
@Table(name = "cassa_previdenziale")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CassaPrevidenziale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4)
    @Column(name = "tipo_cassa", length = 4)
    private String tipoCassa;

    @Size(max = 6)
    @Column(name = "al_cassa", length = 6)
    private String alCassa;

    @Size(max = 15)
    @Column(name = "importo_contributo_cassa", length = 15)
    private String importoContributoCassa;

    @Size(max = 15)
    @Column(name = "imponibile_cassa", length = 15)
    private String imponibileCassa;

    @Size(max = 6)
    @Column(name = "aliquota_iva", length = 6)
    private String aliquotaIVA;

    @Size(max = 2)
    @Column(name = "ritenuta", length = 2)
    private String ritenuta;

    @Size(max = 2)
    @Column(name = "natura", length = 2)
    private String natura;

    @Size(max = 20)
    @Column(name = "riferimento_amministrazione", length = 20)
    private String riferimentoAmministrazione;

    @ManyToOne
    @JsonIgnoreProperties("cassaPrevidenziales")
    private FABody fABody;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoCassa() {
        return tipoCassa;
    }

    public CassaPrevidenziale tipoCassa(String tipoCassa) {
        this.tipoCassa = tipoCassa;
        return this;
    }

    public void setTipoCassa(String tipoCassa) {
        this.tipoCassa = tipoCassa;
    }

    public String getAlCassa() {
        return alCassa;
    }

    public CassaPrevidenziale alCassa(String alCassa) {
        this.alCassa = alCassa;
        return this;
    }

    public void setAlCassa(String alCassa) {
        this.alCassa = alCassa;
    }

    public String getImportoContributoCassa() {
        return importoContributoCassa;
    }

    public CassaPrevidenziale importoContributoCassa(String importoContributoCassa) {
        this.importoContributoCassa = importoContributoCassa;
        return this;
    }

    public void setImportoContributoCassa(String importoContributoCassa) {
        this.importoContributoCassa = importoContributoCassa;
    }

    public String getImponibileCassa() {
        return imponibileCassa;
    }

    public CassaPrevidenziale imponibileCassa(String imponibileCassa) {
        this.imponibileCassa = imponibileCassa;
        return this;
    }

    public void setImponibileCassa(String imponibileCassa) {
        this.imponibileCassa = imponibileCassa;
    }

    public String getAliquotaIVA() {
        return aliquotaIVA;
    }

    public CassaPrevidenziale aliquotaIVA(String aliquotaIVA) {
        this.aliquotaIVA = aliquotaIVA;
        return this;
    }

    public void setAliquotaIVA(String aliquotaIVA) {
        this.aliquotaIVA = aliquotaIVA;
    }

    public String getRitenuta() {
        return ritenuta;
    }

    public CassaPrevidenziale ritenuta(String ritenuta) {
        this.ritenuta = ritenuta;
        return this;
    }

    public void setRitenuta(String ritenuta) {
        this.ritenuta = ritenuta;
    }

    public String getNatura() {
        return natura;
    }

    public CassaPrevidenziale natura(String natura) {
        this.natura = natura;
        return this;
    }

    public void setNatura(String natura) {
        this.natura = natura;
    }

    public String getRiferimentoAmministrazione() {
        return riferimentoAmministrazione;
    }

    public CassaPrevidenziale riferimentoAmministrazione(String riferimentoAmministrazione) {
        this.riferimentoAmministrazione = riferimentoAmministrazione;
        return this;
    }

    public void setRiferimentoAmministrazione(String riferimentoAmministrazione) {
        this.riferimentoAmministrazione = riferimentoAmministrazione;
    }

    public FABody getFABody() {
        return fABody;
    }

    public CassaPrevidenziale fABody(FABody fABody) {
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
        if (!(o instanceof CassaPrevidenziale)) {
            return false;
        }
        return id != null && id.equals(((CassaPrevidenziale) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CassaPrevidenziale{" +
            "id=" + getId() +
            ", tipoCassa='" + getTipoCassa() + "'" +
            ", alCassa='" + getAlCassa() + "'" +
            ", importoContributoCassa='" + getImportoContributoCassa() + "'" +
            ", imponibileCassa='" + getImponibileCassa() + "'" +
            ", aliquotaIVA='" + getAliquotaIVA() + "'" +
            ", ritenuta='" + getRitenuta() + "'" +
            ", natura='" + getNatura() + "'" +
            ", riferimentoAmministrazione='" + getRiferimentoAmministrazione() + "'" +
            "}";
    }
}
