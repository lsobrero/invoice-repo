package net.anet.invoice.repo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Allegato.
 */
@Entity
@Table(name = "allegato")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Allegato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 60)
    @Column(name = "nome_attachment", length = 60)
    private String nomeAttachment;

    @Size(max = 10)
    @Column(name = "algoritmo_compressione", length = 10)
    private String algoritmoCompressione;

    @Size(max = 10)
    @Column(name = "formato_attachment", length = 10)
    private String formatoAttachment;

    @Size(max = 100)
    @Column(name = "descrizione_attachment", length = 100)
    private String descrizioneAttachment;

    @Size(max = 1000)
    @Column(name = "attachment", length = 1000)
    private String attachment;

    @ManyToOne
    @JsonIgnoreProperties("allegatoes")
    private FABody fABody;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAttachment() {
        return nomeAttachment;
    }

    public Allegato nomeAttachment(String nomeAttachment) {
        this.nomeAttachment = nomeAttachment;
        return this;
    }

    public void setNomeAttachment(String nomeAttachment) {
        this.nomeAttachment = nomeAttachment;
    }

    public String getAlgoritmoCompressione() {
        return algoritmoCompressione;
    }

    public Allegato algoritmoCompressione(String algoritmoCompressione) {
        this.algoritmoCompressione = algoritmoCompressione;
        return this;
    }

    public void setAlgoritmoCompressione(String algoritmoCompressione) {
        this.algoritmoCompressione = algoritmoCompressione;
    }

    public String getFormatoAttachment() {
        return formatoAttachment;
    }

    public Allegato formatoAttachment(String formatoAttachment) {
        this.formatoAttachment = formatoAttachment;
        return this;
    }

    public void setFormatoAttachment(String formatoAttachment) {
        this.formatoAttachment = formatoAttachment;
    }

    public String getDescrizioneAttachment() {
        return descrizioneAttachment;
    }

    public Allegato descrizioneAttachment(String descrizioneAttachment) {
        this.descrizioneAttachment = descrizioneAttachment;
        return this;
    }

    public void setDescrizioneAttachment(String descrizioneAttachment) {
        this.descrizioneAttachment = descrizioneAttachment;
    }

    public String getAttachment() {
        return attachment;
    }

    public Allegato attachment(String attachment) {
        this.attachment = attachment;
        return this;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public FABody getFABody() {
        return fABody;
    }

    public Allegato fABody(FABody fABody) {
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
        if (!(o instanceof Allegato)) {
            return false;
        }
        return id != null && id.equals(((Allegato) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Allegato{" +
            "id=" + getId() +
            ", nomeAttachment='" + getNomeAttachment() + "'" +
            ", algoritmoCompressione='" + getAlgoritmoCompressione() + "'" +
            ", formatoAttachment='" + getFormatoAttachment() + "'" +
            ", descrizioneAttachment='" + getDescrizioneAttachment() + "'" +
            ", attachment='" + getAttachment() + "'" +
            "}";
    }
}
