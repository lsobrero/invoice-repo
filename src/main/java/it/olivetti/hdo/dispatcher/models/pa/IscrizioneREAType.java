
package it.olivetti.hdo.dispatcher.models.pa;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for IscrizioneREAType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IscrizioneREAType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Ufficio" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}ProvinciaType"/>
 *         &lt;element name="NumeroREA" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}String20Type"/>
 *         &lt;element name="CapitaleSociale" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}Amount2DecimalType" minOccurs="0"/>
 *         &lt;element name="SocioUnico" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}SocioUnicoType" minOccurs="0"/>
 *         &lt;element name="StatoLiquidazione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}StatoLiquidazioneType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IscrizioneREAType", propOrder = {
    "ufficio",
    "numeroREA",
    "capitaleSociale",
    "socioUnico",
    "statoLiquidazione"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class IscrizioneREAType {

    @XmlElement(name = "Ufficio", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String ufficio;
    @XmlElement(name = "NumeroREA", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String numeroREA;
    @XmlElement(name = "CapitaleSociale")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigDecimal capitaleSociale;
    @XmlElement(name = "SocioUnico")
    @XmlSchemaType(name = "string")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected SocioUnicoType socioUnico;
    @XmlElement(name = "StatoLiquidazione", required = true)
    @XmlSchemaType(name = "string")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected StatoLiquidazioneType statoLiquidazione;

    /**
     * Gets the value of the ufficio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getUfficio() {
        return ufficio;
    }

    /**
     * Sets the value of the ufficio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setUfficio(String value) {
        this.ufficio = value;
    }

    /**
     * Gets the value of the numeroREA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getNumeroREA() {
        return numeroREA;
    }

    /**
     * Sets the value of the numeroREA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setNumeroREA(String value) {
        this.numeroREA = value;
    }

    /**
     * Gets the value of the capitaleSociale property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigDecimal getCapitaleSociale() {
        return capitaleSociale;
    }

    /**
     * Sets the value of the capitaleSociale property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCapitaleSociale(BigDecimal value) {
        this.capitaleSociale = value;
    }

    /**
     * Gets the value of the socioUnico property.
     * 
     * @return
     *     possible object is
     *     {@link SocioUnicoType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public SocioUnicoType getSocioUnico() {
        return socioUnico;
    }

    /**
     * Sets the value of the socioUnico property.
     * 
     * @param value
     *     allowed object is
     *     {@link SocioUnicoType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSocioUnico(SocioUnicoType value) {
        this.socioUnico = value;
    }

    /**
     * Gets the value of the statoLiquidazione property.
     * 
     * @return
     *     possible object is
     *     {@link StatoLiquidazioneType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public StatoLiquidazioneType getStatoLiquidazione() {
        return statoLiquidazione;
    }

    /**
     * Sets the value of the statoLiquidazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatoLiquidazioneType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setStatoLiquidazione(StatoLiquidazioneType value) {
        this.statoLiquidazione = value;
    }

}
