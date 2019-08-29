
package it.olivetti.hdo.dispatcher.models.pa;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 				Blocco relativo ai dati del Cedente / Prestatore
 * 			
 * 
 * <p>Java class for CedentePrestatoreType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CedentePrestatoreType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatiAnagrafici" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}DatiAnagraficiCedenteType"/>
 *         &lt;element name="Sede" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}IndirizzoType"/>
 *         &lt;element name="StabileOrganizzazione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}IndirizzoType" minOccurs="0"/>
 *         &lt;element name="IscrizioneREA" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}IscrizioneREAType" minOccurs="0"/>
 *         &lt;element name="Contatti" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}ContattiType" minOccurs="0"/>
 *         &lt;element name="RiferimentoAmministrazione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}String20Type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CedentePrestatoreType", propOrder = {
    "datiAnagrafici",
    "sede",
    "stabileOrganizzazione",
    "iscrizioneREA",
    "contatti",
    "riferimentoAmministrazione"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class CedentePrestatoreType {

    @XmlElement(name = "DatiAnagrafici", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected DatiAnagraficiCedenteType datiAnagrafici;
    @XmlElement(name = "Sede", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected IndirizzoType sede;
    @XmlElement(name = "StabileOrganizzazione")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected IndirizzoType stabileOrganizzazione;
    @XmlElement(name = "IscrizioneREA")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected IscrizioneREAType iscrizioneREA;
    @XmlElement(name = "Contatti")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected ContattiType contatti;
    @XmlElement(name = "RiferimentoAmministrazione")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String riferimentoAmministrazione;

    /**
     * Gets the value of the datiAnagrafici property.
     * 
     * @return
     *     possible object is
     *     {@link DatiAnagraficiCedenteType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public DatiAnagraficiCedenteType getDatiAnagrafici() {
        return datiAnagrafici;
    }

    /**
     * Sets the value of the datiAnagrafici property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiAnagraficiCedenteType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDatiAnagrafici(DatiAnagraficiCedenteType value) {
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
     * Gets the value of the iscrizioneREA property.
     * 
     * @return
     *     possible object is
     *     {@link IscrizioneREAType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public IscrizioneREAType getIscrizioneREA() {
        return iscrizioneREA;
    }

    /**
     * Sets the value of the iscrizioneREA property.
     * 
     * @param value
     *     allowed object is
     *     {@link IscrizioneREAType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setIscrizioneREA(IscrizioneREAType value) {
        this.iscrizioneREA = value;
    }

    /**
     * Gets the value of the contatti property.
     * 
     * @return
     *     possible object is
     *     {@link ContattiType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public ContattiType getContatti() {
        return contatti;
    }

    /**
     * Sets the value of the contatti property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContattiType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setContatti(ContattiType value) {
        this.contatti = value;
    }

    /**
     * Gets the value of the riferimentoAmministrazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getRiferimentoAmministrazione() {
        return riferimentoAmministrazione;
    }

    /**
     * Sets the value of the riferimentoAmministrazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setRiferimentoAmministrazione(String value) {
        this.riferimentoAmministrazione = value;
    }

}
