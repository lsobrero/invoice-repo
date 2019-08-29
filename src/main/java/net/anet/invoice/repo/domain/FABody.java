package net.anet.invoice.repo.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A FABody.
 */
@Entity
@Table(name = "fa_body")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FABody implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4)
    @Column(name = "tipo_documento", length = 4)
    private String tipoDocumento;

    @Size(max = 3)
    @Column(name = "divisa", length = 3)
    private String divisa;

    @Size(max = 10)
    @Column(name = "data", length = 10)
    private String data;

    @Size(max = 20)
    @Column(name = "numero", length = 20)
    private String numero;

    @Size(max = 4)
    @Column(name = "tipo_ritenuta", length = 4)
    private String tipoRitenuta;

    @Size(max = 15)
    @Column(name = "importo_ritenuta", length = 15)
    private String importoRitenuta;

    @Size(max = 6)
    @Column(name = "aliquota_ritenuta", length = 6)
    private String aliquotaRitenuta;

    @Size(max = 2)
    @Column(name = "causale_pagamento", length = 2)
    private String causalePagamento;

    @Size(max = 2)
    @Column(name = "bollo_virtuale", length = 2)
    private String bolloVirtuale;

    @Size(max = 15)
    @Column(name = "importo_bollo", length = 15)
    private String importoBollo;

    @Size(max = 15)
    @Column(name = "importo_totale_documento", length = 15)
    private String importoTotaleDocumento;

    @Size(max = 15)
    @Column(name = "arrotondamento", length = 15)
    private String arrotondamento;

    @Size(max = 2)
    @Column(name = "art_73", length = 2)
    private String art73;

    @Size(max = 2)
    @Column(name = "id_paese", length = 2)
    private String idPaese;

    @Size(max = 28)
    @Column(name = "id_codice", length = 28)
    private String idCodice;

    @Size(max = 16)
    @Column(name = "codice_fiscale", length = 16)
    private String codiceFiscale;

    @Size(max = 80)
    @Column(name = "denominazione", length = 80)
    private String denominazione;

    @Size(max = 60)
    @Column(name = "nome", length = 60)
    private String nome;

    @Size(max = 60)
    @Column(name = "cognome", length = 60)
    private String cognome;

    @Size(max = 10)
    @Column(name = "titolo", length = 10)
    private String titolo;

    @Size(max = 17)
    @Column(name = "cod_eori", length = 17)
    private String codEORI;

    @Size(max = 20)
    @Column(name = "numero_licenza_guida", length = 20)
    private String numeroLicenzaGuida;

    @Size(max = 80)
    @Column(name = "mezzo_trasporto", length = 80)
    private String mezzoTrasporto;

    @Size(max = 100)
    @Column(name = "causale_trasporto", length = 100)
    private String causaleTrasporto;

    @Size(max = 4)
    @Column(name = "numero_colli", length = 4)
    private String numeroColli;

    @Size(max = 100)
    @Column(name = "descrizione", length = 100)
    private String descrizione;

    @Size(max = 10)
    @Column(name = "unita_misura_peso", length = 10)
    private String unitaMisuraPeso;

    @Size(max = 7)
    @Column(name = "peso_lordo", length = 7)
    private String pesoLordo;

    @Size(max = 7)
    @Column(name = "peso_netto", length = 7)
    private String pesoNetto;

    @Size(max = 19)
    @Column(name = "data_ora_ritiro", length = 19)
    private String dataOraRitiro;

    @Size(max = 10)
    @Column(name = "data_inizio_trasporto", length = 10)
    private String dataInizioTrasporto;

    @Size(max = 3)
    @Column(name = "tipo_resa", length = 3)
    private String tipoResa;

    @Size(max = 60)
    @Column(name = "indirizzo", length = 60)
    private String indirizzo;

    @Size(max = 8)
    @Column(name = "numero_civico", length = 8)
    private String numeroCivico;

    @Size(max = 5)
    @Column(name = "c_ap", length = 5)
    private String cAP;

    @Size(max = 60)
    @Column(name = "comune", length = 60)
    private String comune;

    @Size(max = 2)
    @Column(name = "provincia", length = 2)
    private String provincia;

    @Size(max = 2)
    @Column(name = "nazione", length = 2)
    private String nazione;

    @Size(max = 19)
    @Column(name = "data_ora_consegna", length = 19)
    private String dataOraConsegna;

    @Size(max = 20)
    @Column(name = "numero_fattura_principale", length = 20)
    private String numeroFatturaPrincipale;

    @Size(max = 10)
    @Column(name = "data_fattura_principale", length = 10)
    private String dataFatturaPrincipale;

    /**
     * possibile tabella esterna
     */
    @Size(max = 10)
    @ApiModelProperty(value = "possibile tabella esterna")
    @Column(name = "d_v_data", length = 10)
    private String dVData;

    @Size(max = 15)
    @Column(name = "d_v_totale_percorso", length = 15)
    private String dVTotalePercorso;

    @OneToMany(mappedBy = "fABody")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Causale> causales = new HashSet<>();

    @OneToMany(mappedBy = "fABody")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CassaPrevidenziale> cassaPrevidenziales = new HashSet<>();

    @OneToMany(mappedBy = "fABody")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ScontoMaggiorazione> scontoMaggioraziones = new HashSet<>();

    @OneToMany(mappedBy = "fABody")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrdineAcquisto> ordineAcquistos = new HashSet<>();

    @OneToMany(mappedBy = "fABody")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Contratto> contrattos = new HashSet<>();

    @OneToMany(mappedBy = "fABody")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Convenzione> convenziones = new HashSet<>();

    @OneToMany(mappedBy = "fABody")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Ricezione> riceziones = new HashSet<>();

    @OneToMany(mappedBy = "fABody")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FatturaCollegata> fatturaCollegatas = new HashSet<>();

    @OneToMany(mappedBy = "fABody")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SAL> sALS = new HashSet<>();

    @OneToMany(mappedBy = "fABody")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DDT> dDTS = new HashSet<>();

    @OneToMany(mappedBy = "fABody")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DettaglioLinea> dettaglioLineas = new HashSet<>();

    @OneToMany(mappedBy = "fABody")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DatiRiepilogo> datiRiepilogos = new HashSet<>();

    @OneToMany(mappedBy = "fABody")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DatiPagamento> datiPagamentos = new HashSet<>();

    @OneToMany(mappedBy = "fABody")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Allegato> allegatoes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("fABodies")
    private FAHeader fAHeader;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public FABody tipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        return this;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDivisa() {
        return divisa;
    }

    public FABody divisa(String divisa) {
        this.divisa = divisa;
        return this;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public String getData() {
        return data;
    }

    public FABody data(String data) {
        this.data = data;
        return this;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNumero() {
        return numero;
    }

    public FABody numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoRitenuta() {
        return tipoRitenuta;
    }

    public FABody tipoRitenuta(String tipoRitenuta) {
        this.tipoRitenuta = tipoRitenuta;
        return this;
    }

    public void setTipoRitenuta(String tipoRitenuta) {
        this.tipoRitenuta = tipoRitenuta;
    }

    public String getImportoRitenuta() {
        return importoRitenuta;
    }

    public FABody importoRitenuta(String importoRitenuta) {
        this.importoRitenuta = importoRitenuta;
        return this;
    }

    public void setImportoRitenuta(String importoRitenuta) {
        this.importoRitenuta = importoRitenuta;
    }

    public String getAliquotaRitenuta() {
        return aliquotaRitenuta;
    }

    public FABody aliquotaRitenuta(String aliquotaRitenuta) {
        this.aliquotaRitenuta = aliquotaRitenuta;
        return this;
    }

    public void setAliquotaRitenuta(String aliquotaRitenuta) {
        this.aliquotaRitenuta = aliquotaRitenuta;
    }

    public String getCausalePagamento() {
        return causalePagamento;
    }

    public FABody causalePagamento(String causalePagamento) {
        this.causalePagamento = causalePagamento;
        return this;
    }

    public void setCausalePagamento(String causalePagamento) {
        this.causalePagamento = causalePagamento;
    }

    public String getBolloVirtuale() {
        return bolloVirtuale;
    }

    public FABody bolloVirtuale(String bolloVirtuale) {
        this.bolloVirtuale = bolloVirtuale;
        return this;
    }

    public void setBolloVirtuale(String bolloVirtuale) {
        this.bolloVirtuale = bolloVirtuale;
    }

    public String getImportoBollo() {
        return importoBollo;
    }

    public FABody importoBollo(String importoBollo) {
        this.importoBollo = importoBollo;
        return this;
    }

    public void setImportoBollo(String importoBollo) {
        this.importoBollo = importoBollo;
    }

    public String getImportoTotaleDocumento() {
        return importoTotaleDocumento;
    }

    public FABody importoTotaleDocumento(String importoTotaleDocumento) {
        this.importoTotaleDocumento = importoTotaleDocumento;
        return this;
    }

    public void setImportoTotaleDocumento(String importoTotaleDocumento) {
        this.importoTotaleDocumento = importoTotaleDocumento;
    }

    public String getArrotondamento() {
        return arrotondamento;
    }

    public FABody arrotondamento(String arrotondamento) {
        this.arrotondamento = arrotondamento;
        return this;
    }

    public void setArrotondamento(String arrotondamento) {
        this.arrotondamento = arrotondamento;
    }

    public String getArt73() {
        return art73;
    }

    public FABody art73(String art73) {
        this.art73 = art73;
        return this;
    }

    public void setArt73(String art73) {
        this.art73 = art73;
    }

    public String getIdPaese() {
        return idPaese;
    }

    public FABody idPaese(String idPaese) {
        this.idPaese = idPaese;
        return this;
    }

    public void setIdPaese(String idPaese) {
        this.idPaese = idPaese;
    }

    public String getIdCodice() {
        return idCodice;
    }

    public FABody idCodice(String idCodice) {
        this.idCodice = idCodice;
        return this;
    }

    public void setIdCodice(String idCodice) {
        this.idCodice = idCodice;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public FABody codiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
        return this;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public FABody denominazione(String denominazione) {
        this.denominazione = denominazione;
        return this;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getNome() {
        return nome;
    }

    public FABody nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public FABody cognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTitolo() {
        return titolo;
    }

    public FABody titolo(String titolo) {
        this.titolo = titolo;
        return this;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCodEORI() {
        return codEORI;
    }

    public FABody codEORI(String codEORI) {
        this.codEORI = codEORI;
        return this;
    }

    public void setCodEORI(String codEORI) {
        this.codEORI = codEORI;
    }

    public String getNumeroLicenzaGuida() {
        return numeroLicenzaGuida;
    }

    public FABody numeroLicenzaGuida(String numeroLicenzaGuida) {
        this.numeroLicenzaGuida = numeroLicenzaGuida;
        return this;
    }

    public void setNumeroLicenzaGuida(String numeroLicenzaGuida) {
        this.numeroLicenzaGuida = numeroLicenzaGuida;
    }

    public String getMezzoTrasporto() {
        return mezzoTrasporto;
    }

    public FABody mezzoTrasporto(String mezzoTrasporto) {
        this.mezzoTrasporto = mezzoTrasporto;
        return this;
    }

    public void setMezzoTrasporto(String mezzoTrasporto) {
        this.mezzoTrasporto = mezzoTrasporto;
    }

    public String getCausaleTrasporto() {
        return causaleTrasporto;
    }

    public FABody causaleTrasporto(String causaleTrasporto) {
        this.causaleTrasporto = causaleTrasporto;
        return this;
    }

    public void setCausaleTrasporto(String causaleTrasporto) {
        this.causaleTrasporto = causaleTrasporto;
    }

    public String getNumeroColli() {
        return numeroColli;
    }

    public FABody numeroColli(String numeroColli) {
        this.numeroColli = numeroColli;
        return this;
    }

    public void setNumeroColli(String numeroColli) {
        this.numeroColli = numeroColli;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public FABody descrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUnitaMisuraPeso() {
        return unitaMisuraPeso;
    }

    public FABody unitaMisuraPeso(String unitaMisuraPeso) {
        this.unitaMisuraPeso = unitaMisuraPeso;
        return this;
    }

    public void setUnitaMisuraPeso(String unitaMisuraPeso) {
        this.unitaMisuraPeso = unitaMisuraPeso;
    }

    public String getPesoLordo() {
        return pesoLordo;
    }

    public FABody pesoLordo(String pesoLordo) {
        this.pesoLordo = pesoLordo;
        return this;
    }

    public void setPesoLordo(String pesoLordo) {
        this.pesoLordo = pesoLordo;
    }

    public String getPesoNetto() {
        return pesoNetto;
    }

    public FABody pesoNetto(String pesoNetto) {
        this.pesoNetto = pesoNetto;
        return this;
    }

    public void setPesoNetto(String pesoNetto) {
        this.pesoNetto = pesoNetto;
    }

    public String getDataOraRitiro() {
        return dataOraRitiro;
    }

    public FABody dataOraRitiro(String dataOraRitiro) {
        this.dataOraRitiro = dataOraRitiro;
        return this;
    }

    public void setDataOraRitiro(String dataOraRitiro) {
        this.dataOraRitiro = dataOraRitiro;
    }

    public String getDataInizioTrasporto() {
        return dataInizioTrasporto;
    }

    public FABody dataInizioTrasporto(String dataInizioTrasporto) {
        this.dataInizioTrasporto = dataInizioTrasporto;
        return this;
    }

    public void setDataInizioTrasporto(String dataInizioTrasporto) {
        this.dataInizioTrasporto = dataInizioTrasporto;
    }

    public String getTipoResa() {
        return tipoResa;
    }

    public FABody tipoResa(String tipoResa) {
        this.tipoResa = tipoResa;
        return this;
    }

    public void setTipoResa(String tipoResa) {
        this.tipoResa = tipoResa;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public FABody indirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
        return this;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public FABody numeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
        return this;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getcAP() {
        return cAP;
    }

    public FABody cAP(String cAP) {
        this.cAP = cAP;
        return this;
    }

    public void setcAP(String cAP) {
        this.cAP = cAP;
    }

    public String getComune() {
        return comune;
    }

    public FABody comune(String comune) {
        this.comune = comune;
        return this;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public FABody provincia(String provincia) {
        this.provincia = provincia;
        return this;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNazione() {
        return nazione;
    }

    public FABody nazione(String nazione) {
        this.nazione = nazione;
        return this;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getDataOraConsegna() {
        return dataOraConsegna;
    }

    public FABody dataOraConsegna(String dataOraConsegna) {
        this.dataOraConsegna = dataOraConsegna;
        return this;
    }

    public void setDataOraConsegna(String dataOraConsegna) {
        this.dataOraConsegna = dataOraConsegna;
    }

    public String getNumeroFatturaPrincipale() {
        return numeroFatturaPrincipale;
    }

    public FABody numeroFatturaPrincipale(String numeroFatturaPrincipale) {
        this.numeroFatturaPrincipale = numeroFatturaPrincipale;
        return this;
    }

    public void setNumeroFatturaPrincipale(String numeroFatturaPrincipale) {
        this.numeroFatturaPrincipale = numeroFatturaPrincipale;
    }

    public String getDataFatturaPrincipale() {
        return dataFatturaPrincipale;
    }

    public FABody dataFatturaPrincipale(String dataFatturaPrincipale) {
        this.dataFatturaPrincipale = dataFatturaPrincipale;
        return this;
    }

    public void setDataFatturaPrincipale(String dataFatturaPrincipale) {
        this.dataFatturaPrincipale = dataFatturaPrincipale;
    }

    public String getdVData() {
        return dVData;
    }

    public FABody dVData(String dVData) {
        this.dVData = dVData;
        return this;
    }

    public void setdVData(String dVData) {
        this.dVData = dVData;
    }

    public String getdVTotalePercorso() {
        return dVTotalePercorso;
    }

    public FABody dVTotalePercorso(String dVTotalePercorso) {
        this.dVTotalePercorso = dVTotalePercorso;
        return this;
    }

    public void setdVTotalePercorso(String dVTotalePercorso) {
        this.dVTotalePercorso = dVTotalePercorso;
    }

    public Set<Causale> getCausales() {
        return causales;
    }

    public FABody causales(Set<Causale> causales) {
        this.causales = causales;
        return this;
    }

    public FABody addCausale(Causale causale) {
        this.causales.add(causale);
        causale.setFABody(this);
        return this;
    }

    public FABody removeCausale(Causale causale) {
        this.causales.remove(causale);
        causale.setFABody(null);
        return this;
    }

    public void setCausales(Set<Causale> causales) {
        this.causales = causales;
    }

    public Set<CassaPrevidenziale> getCassaPrevidenziales() {
        return cassaPrevidenziales;
    }

    public FABody cassaPrevidenziales(Set<CassaPrevidenziale> cassaPrevidenziales) {
        this.cassaPrevidenziales = cassaPrevidenziales;
        return this;
    }

    public FABody addCassaPrevidenziale(CassaPrevidenziale cassaPrevidenziale) {
        this.cassaPrevidenziales.add(cassaPrevidenziale);
        cassaPrevidenziale.setFABody(this);
        return this;
    }

    public FABody removeCassaPrevidenziale(CassaPrevidenziale cassaPrevidenziale) {
        this.cassaPrevidenziales.remove(cassaPrevidenziale);
        cassaPrevidenziale.setFABody(null);
        return this;
    }

    public void setCassaPrevidenziales(Set<CassaPrevidenziale> cassaPrevidenziales) {
        this.cassaPrevidenziales = cassaPrevidenziales;
    }

    public Set<ScontoMaggiorazione> getScontoMaggioraziones() {
        return scontoMaggioraziones;
    }

    public FABody scontoMaggioraziones(Set<ScontoMaggiorazione> scontoMaggioraziones) {
        this.scontoMaggioraziones = scontoMaggioraziones;
        return this;
    }

    public FABody addScontoMaggiorazione(ScontoMaggiorazione scontoMaggiorazione) {
        this.scontoMaggioraziones.add(scontoMaggiorazione);
        scontoMaggiorazione.setFABody(this);
        return this;
    }

    public FABody removeScontoMaggiorazione(ScontoMaggiorazione scontoMaggiorazione) {
        this.scontoMaggioraziones.remove(scontoMaggiorazione);
        scontoMaggiorazione.setFABody(null);
        return this;
    }

    public void setScontoMaggioraziones(Set<ScontoMaggiorazione> scontoMaggioraziones) {
        this.scontoMaggioraziones = scontoMaggioraziones;
    }

    public Set<OrdineAcquisto> getOrdineAcquistos() {
        return ordineAcquistos;
    }

    public FABody ordineAcquistos(Set<OrdineAcquisto> ordineAcquistos) {
        this.ordineAcquistos = ordineAcquistos;
        return this;
    }

    public FABody addOrdineAcquisto(OrdineAcquisto ordineAcquisto) {
        this.ordineAcquistos.add(ordineAcquisto);
        ordineAcquisto.setFABody(this);
        return this;
    }

    public FABody removeOrdineAcquisto(OrdineAcquisto ordineAcquisto) {
        this.ordineAcquistos.remove(ordineAcquisto);
        ordineAcquisto.setFABody(null);
        return this;
    }

    public void setOrdineAcquistos(Set<OrdineAcquisto> ordineAcquistos) {
        this.ordineAcquistos = ordineAcquistos;
    }

    public Set<Contratto> getContrattos() {
        return contrattos;
    }

    public FABody contrattos(Set<Contratto> contrattos) {
        this.contrattos = contrattos;
        return this;
    }

    public FABody addContratto(Contratto contratto) {
        this.contrattos.add(contratto);
        contratto.setFABody(this);
        return this;
    }

    public FABody removeContratto(Contratto contratto) {
        this.contrattos.remove(contratto);
        contratto.setFABody(null);
        return this;
    }

    public void setContrattos(Set<Contratto> contrattos) {
        this.contrattos = contrattos;
    }

    public Set<Convenzione> getConvenziones() {
        return convenziones;
    }

    public FABody convenziones(Set<Convenzione> convenziones) {
        this.convenziones = convenziones;
        return this;
    }

    public FABody addConvenzione(Convenzione convenzione) {
        this.convenziones.add(convenzione);
        convenzione.setFABody(this);
        return this;
    }

    public FABody removeConvenzione(Convenzione convenzione) {
        this.convenziones.remove(convenzione);
        convenzione.setFABody(null);
        return this;
    }

    public void setConvenziones(Set<Convenzione> convenziones) {
        this.convenziones = convenziones;
    }

    public Set<Ricezione> getRiceziones() {
        return riceziones;
    }

    public FABody riceziones(Set<Ricezione> riceziones) {
        this.riceziones = riceziones;
        return this;
    }

    public FABody addRicezione(Ricezione ricezione) {
        this.riceziones.add(ricezione);
        ricezione.setFABody(this);
        return this;
    }

    public FABody removeRicezione(Ricezione ricezione) {
        this.riceziones.remove(ricezione);
        ricezione.setFABody(null);
        return this;
    }

    public void setRiceziones(Set<Ricezione> riceziones) {
        this.riceziones = riceziones;
    }

    public Set<FatturaCollegata> getFatturaCollegatas() {
        return fatturaCollegatas;
    }

    public FABody fatturaCollegatas(Set<FatturaCollegata> fatturaCollegatas) {
        this.fatturaCollegatas = fatturaCollegatas;
        return this;
    }

    public FABody addFatturaCollegata(FatturaCollegata fatturaCollegata) {
        this.fatturaCollegatas.add(fatturaCollegata);
        fatturaCollegata.setFABody(this);
        return this;
    }

    public FABody removeFatturaCollegata(FatturaCollegata fatturaCollegata) {
        this.fatturaCollegatas.remove(fatturaCollegata);
        fatturaCollegata.setFABody(null);
        return this;
    }

    public void setFatturaCollegatas(Set<FatturaCollegata> fatturaCollegatas) {
        this.fatturaCollegatas = fatturaCollegatas;
    }

    public Set<SAL> getSALS() {
        return sALS;
    }

    public FABody sALS(Set<SAL> sALS) {
        this.sALS = sALS;
        return this;
    }

    public FABody addSAL(SAL sAL) {
        this.sALS.add(sAL);
        sAL.setFABody(this);
        return this;
    }

    public FABody removeSAL(SAL sAL) {
        this.sALS.remove(sAL);
        sAL.setFABody(null);
        return this;
    }

    public void setSALS(Set<SAL> sALS) {
        this.sALS = sALS;
    }

    public Set<DDT> getDDTS() {
        return dDTS;
    }

    public FABody dDTS(Set<DDT> dDTS) {
        this.dDTS = dDTS;
        return this;
    }

    public FABody addDDT(DDT dDT) {
        this.dDTS.add(dDT);
        dDT.setFABody(this);
        return this;
    }

    public FABody removeDDT(DDT dDT) {
        this.dDTS.remove(dDT);
        dDT.setFABody(null);
        return this;
    }

    public void setDDTS(Set<DDT> dDTS) {
        this.dDTS = dDTS;
    }

    public Set<DettaglioLinea> getDettaglioLineas() {
        return dettaglioLineas;
    }

    public FABody dettaglioLineas(Set<DettaglioLinea> dettaglioLineas) {
        this.dettaglioLineas = dettaglioLineas;
        return this;
    }

    public FABody addDettaglioLinea(DettaglioLinea dettaglioLinea) {
        this.dettaglioLineas.add(dettaglioLinea);
        dettaglioLinea.setFABody(this);
        return this;
    }

    public FABody removeDettaglioLinea(DettaglioLinea dettaglioLinea) {
        this.dettaglioLineas.remove(dettaglioLinea);
        dettaglioLinea.setFABody(null);
        return this;
    }

    public void setDettaglioLineas(Set<DettaglioLinea> dettaglioLineas) {
        this.dettaglioLineas = dettaglioLineas;
    }

    public Set<DatiRiepilogo> getDatiRiepilogos() {
        return datiRiepilogos;
    }

    public FABody datiRiepilogos(Set<DatiRiepilogo> datiRiepilogos) {
        this.datiRiepilogos = datiRiepilogos;
        return this;
    }

    public FABody addDatiRiepilogo(DatiRiepilogo datiRiepilogo) {
        this.datiRiepilogos.add(datiRiepilogo);
        datiRiepilogo.setFABody(this);
        return this;
    }

    public FABody removeDatiRiepilogo(DatiRiepilogo datiRiepilogo) {
        this.datiRiepilogos.remove(datiRiepilogo);
        datiRiepilogo.setFABody(null);
        return this;
    }

    public void setDatiRiepilogos(Set<DatiRiepilogo> datiRiepilogos) {
        this.datiRiepilogos = datiRiepilogos;
    }

    public Set<DatiPagamento> getDatiPagamentos() {
        return datiPagamentos;
    }

    public FABody datiPagamentos(Set<DatiPagamento> datiPagamentos) {
        this.datiPagamentos = datiPagamentos;
        return this;
    }

    public FABody addDatiPagamento(DatiPagamento datiPagamento) {
        this.datiPagamentos.add(datiPagamento);
        datiPagamento.setFABody(this);
        return this;
    }

    public FABody removeDatiPagamento(DatiPagamento datiPagamento) {
        this.datiPagamentos.remove(datiPagamento);
        datiPagamento.setFABody(null);
        return this;
    }

    public void setDatiPagamentos(Set<DatiPagamento> datiPagamentos) {
        this.datiPagamentos = datiPagamentos;
    }

    public Set<Allegato> getAllegatoes() {
        return allegatoes;
    }

    public FABody allegatoes(Set<Allegato> allegatoes) {
        this.allegatoes = allegatoes;
        return this;
    }

    public FABody addAllegato(Allegato allegato) {
        this.allegatoes.add(allegato);
        allegato.setFABody(this);
        return this;
    }

    public FABody removeAllegato(Allegato allegato) {
        this.allegatoes.remove(allegato);
        allegato.setFABody(null);
        return this;
    }

    public void setAllegatoes(Set<Allegato> allegatoes) {
        this.allegatoes = allegatoes;
    }

    public FAHeader getFAHeader() {
        return fAHeader;
    }

    public FABody fAHeader(FAHeader fAHeader) {
        this.fAHeader = fAHeader;
        return this;
    }

    public void setFAHeader(FAHeader fAHeader) {
        this.fAHeader = fAHeader;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FABody)) {
            return false;
        }
        return id != null && id.equals(((FABody) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FABody{" +
            "id=" + getId() +
            ", tipoDocumento='" + getTipoDocumento() + "'" +
            ", divisa='" + getDivisa() + "'" +
            ", data='" + getData() + "'" +
            ", numero='" + getNumero() + "'" +
            ", tipoRitenuta='" + getTipoRitenuta() + "'" +
            ", importoRitenuta='" + getImportoRitenuta() + "'" +
            ", aliquotaRitenuta='" + getAliquotaRitenuta() + "'" +
            ", causalePagamento='" + getCausalePagamento() + "'" +
            ", bolloVirtuale='" + getBolloVirtuale() + "'" +
            ", importoBollo='" + getImportoBollo() + "'" +
            ", importoTotaleDocumento='" + getImportoTotaleDocumento() + "'" +
            ", arrotondamento='" + getArrotondamento() + "'" +
            ", art73='" + getArt73() + "'" +
            ", idPaese='" + getIdPaese() + "'" +
            ", idCodice='" + getIdCodice() + "'" +
            ", codiceFiscale='" + getCodiceFiscale() + "'" +
            ", denominazione='" + getDenominazione() + "'" +
            ", nome='" + getNome() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", titolo='" + getTitolo() + "'" +
            ", codEORI='" + getCodEORI() + "'" +
            ", numeroLicenzaGuida='" + getNumeroLicenzaGuida() + "'" +
            ", mezzoTrasporto='" + getMezzoTrasporto() + "'" +
            ", causaleTrasporto='" + getCausaleTrasporto() + "'" +
            ", numeroColli='" + getNumeroColli() + "'" +
            ", descrizione='" + getDescrizione() + "'" +
            ", unitaMisuraPeso='" + getUnitaMisuraPeso() + "'" +
            ", pesoLordo='" + getPesoLordo() + "'" +
            ", pesoNetto='" + getPesoNetto() + "'" +
            ", dataOraRitiro='" + getDataOraRitiro() + "'" +
            ", dataInizioTrasporto='" + getDataInizioTrasporto() + "'" +
            ", tipoResa='" + getTipoResa() + "'" +
            ", indirizzo='" + getIndirizzo() + "'" +
            ", numeroCivico='" + getNumeroCivico() + "'" +
            ", cAP='" + getcAP() + "'" +
            ", comune='" + getComune() + "'" +
            ", provincia='" + getProvincia() + "'" +
            ", nazione='" + getNazione() + "'" +
            ", dataOraConsegna='" + getDataOraConsegna() + "'" +
            ", numeroFatturaPrincipale='" + getNumeroFatturaPrincipale() + "'" +
            ", dataFatturaPrincipale='" + getDataFatturaPrincipale() + "'" +
            ", dVData='" + getdVData() + "'" +
            ", dVTotalePercorso='" + getdVTotalePercorso() + "'" +
            "}";
    }
}
