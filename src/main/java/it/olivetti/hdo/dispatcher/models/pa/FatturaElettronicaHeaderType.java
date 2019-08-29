
package it.olivetti.hdo.dispatcher.models.pa;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FatturaElettronicaHeaderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FatturaElettronicaHeaderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatiTrasmissione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}DatiTrasmissioneType"/>
 *         &lt;element name="CedentePrestatore" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}CedentePrestatoreType"/>
 *         &lt;element name="RappresentanteFiscale" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}RappresentanteFiscaleType" minOccurs="0"/>
 *         &lt;element name="CessionarioCommittente" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}CessionarioCommittenteType"/>
 *         &lt;element name="TerzoIntermediarioOSoggettoEmittente" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}TerzoIntermediarioSoggettoEmittenteType" minOccurs="0"/>
 *         &lt;element name="SoggettoEmittente" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}SoggettoEmittenteType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FatturaElettronicaHeaderType", propOrder = {
    "datiTrasmissione",
    "cedentePrestatore",
    "rappresentanteFiscale",
    "cessionarioCommittente",
    "terzoIntermediarioOSoggettoEmittente",
    "soggettoEmittente"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class FatturaElettronicaHeaderType {

    @XmlElement(name = "DatiTrasmissione", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected DatiTrasmissioneType datiTrasmissione;
    @XmlElement(name = "CedentePrestatore", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected CedentePrestatoreType cedentePrestatore;
    @XmlElement(name = "RappresentanteFiscale")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected RappresentanteFiscaleType rappresentanteFiscale;
    @XmlElement(name = "CessionarioCommittente", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected CessionarioCommittenteType cessionarioCommittente;
    @XmlElement(name = "TerzoIntermediarioOSoggettoEmittente")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected TerzoIntermediarioSoggettoEmittenteType terzoIntermediarioOSoggettoEmittente;
    @XmlElement(name = "SoggettoEmittente")
    @XmlSchemaType(name = "string")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected SoggettoEmittenteType soggettoEmittente;

    /**
     * Gets the value of the datiTrasmissione property.
     * 
     * @return
     *     possible object is
     *     {@link DatiTrasmissioneType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public DatiTrasmissioneType getDatiTrasmissione() {
        return datiTrasmissione;
    }

    /**
     * Sets the value of the datiTrasmissione property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiTrasmissioneType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDatiTrasmissione(DatiTrasmissioneType value) {
        this.datiTrasmissione = value;
    }

    /**
     * Gets the value of the cedentePrestatore property.
     * 
     * @return
     *     possible object is
     *     {@link CedentePrestatoreType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public CedentePrestatoreType getCedentePrestatore() {
        return cedentePrestatore;
    }

    /**
     * Sets the value of the cedentePrestatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link CedentePrestatoreType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCedentePrestatore(CedentePrestatoreType value) {
        this.cedentePrestatore = value;
    }

    /**
     * Gets the value of the rappresentanteFiscale property.
     * 
     * @return
     *     possible object is
     *     {@link RappresentanteFiscaleType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public RappresentanteFiscaleType getRappresentanteFiscale() {
        return rappresentanteFiscale;
    }

    /**
     * Sets the value of the rappresentanteFiscale property.
     * 
     * @param value
     *     allowed object is
     *     {@link RappresentanteFiscaleType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setRappresentanteFiscale(RappresentanteFiscaleType value) {
        this.rappresentanteFiscale = value;
    }

    /**
     * Gets the value of the cessionarioCommittente property.
     * 
     * @return
     *     possible object is
     *     {@link CessionarioCommittenteType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public CessionarioCommittenteType getCessionarioCommittente() {
        return cessionarioCommittente;
    }

    /**
     * Sets the value of the cessionarioCommittente property.
     * 
     * @param value
     *     allowed object is
     *     {@link CessionarioCommittenteType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCessionarioCommittente(CessionarioCommittenteType value) {
        this.cessionarioCommittente = value;
    }

    /**
     * Gets the value of the terzoIntermediarioOSoggettoEmittente property.
     * 
     * @return
     *     possible object is
     *     {@link TerzoIntermediarioSoggettoEmittenteType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public TerzoIntermediarioSoggettoEmittenteType getTerzoIntermediarioOSoggettoEmittente() {
        return terzoIntermediarioOSoggettoEmittente;
    }

    /**
     * Sets the value of the terzoIntermediarioOSoggettoEmittente property.
     * 
     * @param value
     *     allowed object is
     *     {@link TerzoIntermediarioSoggettoEmittenteType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setTerzoIntermediarioOSoggettoEmittente(TerzoIntermediarioSoggettoEmittenteType value) {
        this.terzoIntermediarioOSoggettoEmittente = value;
    }

    /**
     * Gets the value of the soggettoEmittente property.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoEmittenteType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public SoggettoEmittenteType getSoggettoEmittente() {
        return soggettoEmittente;
    }

    /**
     * Sets the value of the soggettoEmittente property.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoEmittenteType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSoggettoEmittente(SoggettoEmittenteType value) {
        this.soggettoEmittente = value;
    }

}
