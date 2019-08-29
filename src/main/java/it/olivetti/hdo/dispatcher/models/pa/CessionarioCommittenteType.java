
package it.olivetti.hdo.dispatcher.models.pa;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Blocco relativo ai dati del Cessionario / Committente
 * 
 * <p>Java class for CessionarioCommittenteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CessionarioCommittenteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatiAnagrafici" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}DatiAnagraficiCessionarioType"/>
 *         &lt;element name="Sede" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}IndirizzoType"/>
 *         &lt;element name="StabileOrganizzazione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}IndirizzoType" minOccurs="0"/>
 *         &lt;element name="RappresentanteFiscale" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}RappresentanteFiscaleCessionarioType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CessionarioCommittenteType", propOrder = {
    "datiAnagrafici",
    "sede",
    "stabileOrganizzazione",
    "rappresentanteFiscale"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class CessionarioCommittenteType {

    @XmlElement(name = "DatiAnagrafici", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected DatiAnagraficiCessionarioType datiAnagrafici;
    @XmlElement(name = "Sede", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected IndirizzoType sede;
    @XmlElement(name = "StabileOrganizzazione")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected IndirizzoType stabileOrganizzazione;
    @XmlElement(name = "RappresentanteFiscale")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected RappresentanteFiscaleCessionarioType rappresentanteFiscale;

    /**
     * Gets the value of the datiAnagrafici property.
     * 
     * @return
     *     possible object is
     *     {@link DatiAnagraficiCessionarioType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public DatiAnagraficiCessionarioType getDatiAnagrafici() {
        return datiAnagrafici;
    }

    /**
     * Sets the value of the datiAnagrafici property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiAnagraficiCessionarioType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDatiAnagrafici(DatiAnagraficiCessionarioType value) {
        this.datiAnagrafici = value;
    }

    /**
     * Gets the value of the sede property.
     * 
     * @return
     *     possible object is
     *     {@link IndirizzoType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public IndirizzoType getSede() {
        return sede;
    }

    /**
     * Sets the value of the sede property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndirizzoType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSede(IndirizzoType value) {
        this.sede = value;
    }

    /**
     * Gets the value of the stabileOrganizzazione property.
     * 
     * @return
     *     possible object is
     *     {@link IndirizzoType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public IndirizzoType getStabileOrganizzazione() {
        return stabileOrganizzazione;
    }

    /**
     * Sets the value of the stabileOrganizzazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndirizzoType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setStabileOrganizzazione(IndirizzoType value) {
        this.stabileOrganizzazione = value;
    }

    /**
     * Gets the value of the rappresentanteFiscale property.
     * 
     * @return
     *     possible object is
     *     {@link RappresentanteFiscaleCessionarioType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public RappresentanteFiscaleCessionarioType getRappresentanteFiscale() {
        return rappresentanteFiscale;
    }

    /**
     * Sets the value of the rappresentanteFiscale property.
     * 
     * @param value
     *     allowed object is
     *     {@link RappresentanteFiscaleCessionarioType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setRappresentanteFiscale(RappresentanteFiscaleCessionarioType value) {
        this.rappresentanteFiscale = value;
    }

}
