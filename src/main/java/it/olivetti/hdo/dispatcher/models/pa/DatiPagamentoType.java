
package it.olivetti.hdo.dispatcher.models.pa;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Blocco relativo ai dati di Pagamento della Fattura	Elettronica
 * 			
 * 
 * <p>Java class for DatiPagamentoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatiPagamentoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CondizioniPagamento" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}CondizioniPagamentoType"/>
 *         &lt;element name="DettaglioPagamento" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}DettaglioPagamentoType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiPagamentoType", propOrder = {
    "condizioniPagamento",
    "dettaglioPagamento"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class DatiPagamentoType {

    @XmlElement(name = "CondizioniPagamento", required = true)
    @XmlSchemaType(name = "string")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected CondizioniPagamentoType condizioniPagamento;
    @XmlElement(name = "DettaglioPagamento", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<DettaglioPagamentoType> dettaglioPagamento;

    /**
     * Gets the value of the condizioniPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link CondizioniPagamentoType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public CondizioniPagamentoType getCondizioniPagamento() {
        return condizioniPagamento;
    }

    /**
     * Sets the value of the condizioniPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link CondizioniPagamentoType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCondizioniPagamento(CondizioniPagamentoType value) {
        this.condizioniPagamento = value;
    }

    /**
     * Gets the value of the dettaglioPagamento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dettaglioPagamento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDettaglioPagamento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DettaglioPagamentoType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<DettaglioPagamentoType> getDettaglioPagamento() {
        if (dettaglioPagamento == null) {
            dettaglioPagamento = new ArrayList<DettaglioPagamentoType>();
        }
        return this.dettaglioPagamento;
    }

}
