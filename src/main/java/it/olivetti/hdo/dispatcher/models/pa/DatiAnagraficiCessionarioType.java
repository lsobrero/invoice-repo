
package it.olivetti.hdo.dispatcher.models.pa;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DatiAnagraficiCessionarioType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatiAnagraficiCessionarioType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdFiscaleIVA" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}IdFiscaleType" minOccurs="0"/>
 *         &lt;element name="CodiceFiscale" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}CodiceFiscaleType" minOccurs="0"/>
 *         &lt;element name="Anagrafica" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}AnagraficaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiAnagraficiCessionarioType", propOrder = {
    "idFiscaleIVA",
    "codiceFiscale",
    "anagrafica"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class DatiAnagraficiCessionarioType {

    @XmlElement(name = "IdFiscaleIVA")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected IdFiscaleType idFiscaleIVA;
    @XmlElement(name = "CodiceFiscale")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String codiceFiscale;
    @XmlElement(name = "Anagrafica", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected AnagraficaType anagrafica;

    /**
     * Gets the value of the idFiscaleIVA property.
     * 
     * @return
     *     possible object is
     *     {@link IdFiscaleType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public IdFiscaleType getIdFiscaleIVA() {
        return idFiscaleIVA;
    }

    /**
     * Sets the value of the idFiscaleIVA property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdFiscaleType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setIdFiscaleIVA(IdFiscaleType value) {
        this.idFiscaleIVA = value;
    }

    /**
     * Gets the value of the codiceFiscale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Sets the value of the codiceFiscale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    /**
     * Gets the value of the anagrafica property.
     * 
     * @return
     *     possible object is
     *     {@link AnagraficaType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public AnagraficaType getAnagrafica() {
        return anagrafica;
    }

    /**
     * Sets the value of the anagrafica property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnagraficaType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAnagrafica(AnagraficaType value) {
        this.anagrafica = value;
    }

}
