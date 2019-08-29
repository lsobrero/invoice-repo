
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
 * <p>Java class for DatiRiepilogoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatiRiepilogoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AliquotaIVA" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}RateType"/>
 *         &lt;element name="Natura" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}NaturaType" minOccurs="0"/>
 *         &lt;element name="SpeseAccessorie" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}Amount2DecimalType" minOccurs="0"/>
 *         &lt;element name="Arrotondamento" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}Amount8DecimalType" minOccurs="0"/>
 *         &lt;element name="ImponibileImporto" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}Amount2DecimalType"/>
 *         &lt;element name="Imposta" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}Amount2DecimalType"/>
 *         &lt;element name="EsigibilitaIVA" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}EsigibilitaIVAType" minOccurs="0"/>
 *         &lt;element name="RiferimentoNormativo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}String100LatinType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiRiepilogoType", propOrder = {
    "aliquotaIVA",
    "natura",
    "speseAccessorie",
    "arrotondamento",
    "imponibileImporto",
    "imposta",
    "esigibilitaIVA",
    "riferimentoNormativo"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class DatiRiepilogoType {

    @XmlElement(name = "AliquotaIVA", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigDecimal aliquotaIVA;
    @XmlElement(name = "Natura")
    @XmlSchemaType(name = "string")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected NaturaType natura;
    @XmlElement(name = "SpeseAccessorie")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigDecimal speseAccessorie;
    @XmlElement(name = "Arrotondamento")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigDecimal arrotondamento;
    @XmlElement(name = "ImponibileImporto", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigDecimal imponibileImporto;
    @XmlElement(name = "Imposta", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigDecimal imposta;
    @XmlElement(name = "EsigibilitaIVA")
    @XmlSchemaType(name = "string")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected EsigibilitaIVAType esigibilitaIVA;
    @XmlElement(name = "RiferimentoNormativo")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String riferimentoNormativo;

    /**
     * Gets the value of the aliquotaIVA property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigDecimal getAliquotaIVA() {
        return aliquotaIVA;
    }

    /**
     * Sets the value of the aliquotaIVA property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAliquotaIVA(BigDecimal value) {
        this.aliquotaIVA = value;
    }

    /**
     * Gets the value of the natura property.
     * 
     * @return
     *     possible object is
     *     {@link NaturaType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public NaturaType getNatura() {
        return natura;
    }

    /**
     * Sets the value of the natura property.
     * 
     * @param value
     *     allowed object is
     *     {@link NaturaType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setNatura(NaturaType value) {
        this.natura = value;
    }

    /**
     * Gets the value of the speseAccessorie property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigDecimal getSpeseAccessorie() {
        return speseAccessorie;
    }

    /**
     * Sets the value of the speseAccessorie property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSpeseAccessorie(BigDecimal value) {
        this.speseAccessorie = value;
    }

    /**
     * Gets the value of the arrotondamento property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigDecimal getArrotondamento() {
        return arrotondamento;
    }

    /**
     * Sets the value of the arrotondamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setArrotondamento(BigDecimal value) {
        this.arrotondamento = value;
    }

    /**
     * Gets the value of the imponibileImporto property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigDecimal getImponibileImporto() {
        return imponibileImporto;
    }

    /**
     * Sets the value of the imponibileImporto property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setImponibileImporto(BigDecimal value) {
        this.imponibileImporto = value;
    }

    /**
     * Gets the value of the imposta property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigDecimal getImposta() {
        return imposta;
    }

    /**
     * Sets the value of the imposta property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setImposta(BigDecimal value) {
        this.imposta = value;
    }

    /**
     * Gets the value of the esigibilitaIVA property.
     * 
     * @return
     *     possible object is
     *     {@link EsigibilitaIVAType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public EsigibilitaIVAType getEsigibilitaIVA() {
        return esigibilitaIVA;
    }

    /**
     * Sets the value of the esigibilitaIVA property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsigibilitaIVAType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setEsigibilitaIVA(EsigibilitaIVAType value) {
        this.esigibilitaIVA = value;
    }

    /**
     * Gets the value of the riferimentoNormativo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getRiferimentoNormativo() {
        return riferimentoNormativo;
    }

    /**
     * Sets the value of the riferimentoNormativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setRiferimentoNormativo(String value) {
        this.riferimentoNormativo = value;
    }

}
