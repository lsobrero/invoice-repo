
package it.olivetti.hdo.dispatcher.models.pa;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FatturaElettronicaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FatturaElettronicaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FatturaElettronicaHeader" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}FatturaElettronicaHeaderType"/>
 *         &lt;element name="FatturaElettronicaBody" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}FatturaElettronicaBodyType" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="versione" use="required" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}FormatoTrasmissioneType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FatturaElettronicaType", propOrder = {
    "fatturaElettronicaHeader",
    "fatturaElettronicaBody",
    "signature"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class FatturaElettronicaType {

    @XmlElement(name = "FatturaElettronicaHeader", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected FatturaElettronicaHeaderType fatturaElettronicaHeader;
    @XmlElement(name = "FatturaElettronicaBody", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<FatturaElettronicaBodyType> fatturaElettronicaBody;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected SignatureType signature;
    @XmlAttribute(name = "versione", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected FormatoTrasmissioneType versione;

    /**
     * Gets the value of the fatturaElettronicaHeader property.
     * 
     * @return
     *     possible object is
     *     {@link FatturaElettronicaHeaderType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public FatturaElettronicaHeaderType getFatturaElettronicaHeader() {
        return fatturaElettronicaHeader;
    }

    /**
     * Sets the value of the fatturaElettronicaHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link FatturaElettronicaHeaderType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setFatturaElettronicaHeader(FatturaElettronicaHeaderType value) {
        this.fatturaElettronicaHeader = value;
    }

    /**
     * Gets the value of the fatturaElettronicaBody property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fatturaElettronicaBody property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFatturaElettronicaBody().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FatturaElettronicaBodyType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<FatturaElettronicaBodyType> getFatturaElettronicaBody() {
        if (fatturaElettronicaBody == null) {
            fatturaElettronicaBody = new ArrayList<FatturaElettronicaBodyType>();
        }
        return this.fatturaElettronicaBody;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

    /**
     * Gets the value of the versione property.
     * 
     * @return
     *     possible object is
     *     {@link FormatoTrasmissioneType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public FormatoTrasmissioneType getVersione() {
        return versione;
    }

    /**
     * Sets the value of the versione property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormatoTrasmissioneType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setVersione(FormatoTrasmissioneType value) {
        this.versione = value;
    }

}
