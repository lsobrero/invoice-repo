
package it.olivetti.hdo.dispatcher.models.pa;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Blocco relativo ai dati del Terzo Intermediario che
 * 				emette fattura elettronica per conto del
 * 				Cedente/Prestatore
 * 			
 * 
 * <p>Java class for TerzoIntermediarioSoggettoEmittenteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TerzoIntermediarioSoggettoEmittenteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatiAnagrafici" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}DatiAnagraficiTerzoIntermediarioType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TerzoIntermediarioSoggettoEmittenteType", propOrder = {
    "datiAnagrafici"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class TerzoIntermediarioSoggettoEmittenteType {

    @XmlElement(name = "DatiAnagrafici", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected DatiAnagraficiTerzoIntermediarioType datiAnagrafici;

    /**
     * Gets the value of the datiAnagrafici property.
     * 
     * @return
     *     possible object is
     *     {@link DatiAnagraficiTerzoIntermediarioType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public DatiAnagraficiTerzoIntermediarioType getDatiAnagrafici() {
        return datiAnagrafici;
    }

    /**
     * Sets the value of the datiAnagrafici property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiAnagraficiTerzoIntermediarioType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDatiAnagrafici(DatiAnagraficiTerzoIntermediarioType value) {
        this.datiAnagrafici = value;
    }

}
