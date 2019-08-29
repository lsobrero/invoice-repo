
package it.olivetti.hdo.dispatcher.models.pa;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for SignaturePropertiesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SignaturePropertiesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}SignatureProperty" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignaturePropertiesType", namespace = "http://www.w3.org/2000/09/xmldsig#", propOrder = {
    "signatureProperty"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class SignaturePropertiesType {

    @XmlElement(name = "SignatureProperty", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<SignaturePropertyType> signatureProperty;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String id;

    /**
     * Gets the value of the signatureProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signatureProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignatureProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SignaturePropertyType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<SignaturePropertyType> getSignatureProperty() {
        if (signatureProperty == null) {
            signatureProperty = new ArrayList<SignaturePropertyType>();
        }
        return this.signatureProperty;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setId(String value) {
        this.id = value;
    }

}
