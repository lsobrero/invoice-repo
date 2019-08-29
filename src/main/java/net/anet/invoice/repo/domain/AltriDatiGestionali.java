package net.anet.invoice.repo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AltriDatiGestionali.
 */
@Entity
@Table(name = "altri_dati_gestionali")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AltriDatiGestionali implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "tipo_dato", length = 10)
    private String tipoDato;

    @Size(max = 60)
    @Column(name = "riferimento_testo", length = 60)
    private String riferimentoTesto;

    @Size(max = 21)
    @Column(name = "riferimento_numero", length = 21)
    private String riferimentoNumero;

    @Size(max = 10)
    @Column(name = "riferimento_data", length = 10)
    private String riferimentoData;

    @ManyToOne
    @JsonIgnoreProperties("altriDatiGestionalis")
    private DettaglioLinea dettaglioLinea;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public AltriDatiGestionali tipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
        return this;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getRiferimentoTesto() {
        return riferimentoTesto;
    }

    public AltriDatiGestionali riferimentoTesto(String riferimentoTesto) {
        this.riferimentoTesto = riferimentoTesto;
        return this;
    }

    public void setRiferimentoTesto(String riferimentoTesto) {
        this.riferimentoTesto = riferimentoTesto;
    }

    public String getRiferimentoNumero() {
        return riferimentoNumero;
    }

    public AltriDatiGestionali riferimentoNumero(String riferimentoNumero) {
        this.riferimentoNumero = riferimentoNumero;
        return this;
    }

    public void setRiferimentoNumero(String riferimentoNumero) {
        this.riferimentoNumero = riferimentoNumero;
    }

    public String getRiferimentoData() {
        return riferimentoData;
    }

    public AltriDatiGestionali riferimentoData(String riferimentoData) {
        this.riferimentoData = riferimentoData;
        return this;
    }

    public void setRiferimentoData(String riferimentoData) {
        this.riferimentoData = riferimentoData;
    }

    public DettaglioLinea getDettaglioLinea() {
        return dettaglioLinea;
    }

    public AltriDatiGestionali dettaglioLinea(DettaglioLinea dettaglioLinea) {
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
        if (!(o instanceof AltriDatiGestionali)) {
            return false;
        }
        return id != null && id.equals(((AltriDatiGestionali) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AltriDatiGestionali{" +
            "id=" + getId() +
            ", tipoDato='" + getTipoDato() + "'" +
            ", riferimentoTesto='" + getRiferimentoTesto() + "'" +
            ", riferimentoNumero='" + getRiferimentoNumero() + "'" +
            ", riferimentoData='" + getRiferimentoData() + "'" +
            "}";
    }
}
