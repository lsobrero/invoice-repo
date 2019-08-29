
package it.olivetti.hdo.dispatcher.models.pa;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DatiRitenutaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatiRitenutaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoRitenuta" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}TipoRitenutaType"/>
 *         &lt;element name="ImportoRitenuta" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}Amount2DecimalType"/>
 *         &lt;element name="AliquotaRitenuta" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}RateType"/>
 *         &lt;element name="CausalePagamento" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}CausalePagamentoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiRitenutaType", propOrder = {
    "tipoRitenuta",
    "importoRitenuta",
    "aliquotaRitenuta",
    "causalePagamento"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class DatiRitenutaType {

    @XmlElement(name = "TipoRitenuta", required = true)
    @XmlSchemaType(name = "string")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected TipoRitenutaType tipoRitenuta;
    @XmlElement(name = "ImportoRitenuta", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigDecimal importoRitenuta;
    @XmlElement(name = "AliquotaRitenuta", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigDecimal aliquotaRitenuta;
    @XmlElement(name = "CausalePagamento", required = true)
    @XmlSchemaType(name = "string")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected CausalePagamentoType causalePagamento;

    /**
     * Gets the value of the tipoRitenuta property.
     * 
     * @return
     *     possible object is
     *     {@link TipoRitenutaType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public TipoRitenutaType getTipoRitenuta() {
        return tipoRitenuta;
    }

    /**
     * Sets the value of the tipoRitenuta property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoRitenutaType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setTipoRitenuta(TipoRitenutaType value) {
        this.tipoRitenuta = value;
    }

    /**
     * Gets the value of the importoRitenuta property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigDecimal getImportoRitenuta() {
        return importoRitenuta;
    }

    /**
     * Sets the value of the importoRitenuta property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setImportoRitenuta(BigDecimal value) {
        this.importoRitenuta = value;
    }

    /**
     * Gets the value of the aliquotaRitenuta property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigDecimal getAliquotaRitenuta() {
        return aliquotaRitenuta;
    }

    /**
     * Sets the value of the aliquotaRitenuta property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAliquotaRitenuta(BigDecimal value) {
        this.aliquotaRitenuta = value;
    }

    /**
     * Gets the value of the causalePagamento property.
     * 
     * @return
     *     possible object is
     *     {@link CausalePagamentoType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public CausalePagamentoType getCausalePagamento() {
        return causalePagamento;
    }

    /**
     * Sets the value of the causalePagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link CausalePagamentoType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCausalePagamento(CausalePagamentoType value) {
        this.causalePagamento = value;
    }

}
