
package it.olivetti.hdo.dispatcher.models.pa;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Blocco relativo ai Dati Generali della Fattura Elettronica
 * 			
 * 
 * <p>Java class for DatiGeneraliType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatiGeneraliType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatiGeneraliDocumento" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}DatiGeneraliDocumentoType"/>
 *         &lt;element name="DatiOrdineAcquisto" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}DatiDocumentiCorrelatiType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DatiContratto" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}DatiDocumentiCorrelatiType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DatiConvenzione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}DatiDocumentiCorrelatiType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DatiRicezione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}DatiDocumentiCorrelatiType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DatiFattureCollegate" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}DatiDocumentiCorrelatiType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DatiSAL" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}DatiSALType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DatiDDT" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}DatiDDTType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DatiTrasporto" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}DatiTrasportoType" minOccurs="0"/>
 *         &lt;element name="FatturaPrincipale" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}FatturaPrincipaleType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiGeneraliType", propOrder = {
    "datiGeneraliDocumento",
    "datiOrdineAcquisto",
    "datiContratto",
    "datiConvenzione",
    "datiRicezione",
    "datiFattureCollegate",
    "datiSAL",
    "datiDDT",
    "datiTrasporto",
    "fatturaPrincipale"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class DatiGeneraliType {

    @XmlElement(name = "DatiGeneraliDocumento", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected DatiGeneraliDocumentoType datiGeneraliDocumento;
    @XmlElement(name = "DatiOrdineAcquisto")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<DatiDocumentiCorrelatiType> datiOrdineAcquisto;
    @XmlElement(name = "DatiContratto")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<DatiDocumentiCorrelatiType> datiContratto;
    @XmlElement(name = "DatiConvenzione")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<DatiDocumentiCorrelatiType> datiConvenzione;
    @XmlElement(name = "DatiRicezione")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<DatiDocumentiCorrelatiType> datiRicezione;
    @XmlElement(name = "DatiFattureCollegate")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<DatiDocumentiCorrelatiType> datiFattureCollegate;
    @XmlElement(name = "DatiSAL")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<DatiSALType> datiSAL;
    @XmlElement(name = "DatiDDT")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<DatiDDTType> datiDDT;
    @XmlElement(name = "DatiTrasporto")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected DatiTrasportoType datiTrasporto;
    @XmlElement(name = "FatturaPrincipale")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected FatturaPrincipaleType fatturaPrincipale;

    /**
     * Gets the value of the datiGeneraliDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link DatiGeneraliDocumentoType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public DatiGeneraliDocumentoType getDatiGeneraliDocumento() {
        return datiGeneraliDocumento;
    }

    /**
     * Sets the value of the datiGeneraliDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiGeneraliDocumentoType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDatiGeneraliDocumento(DatiGeneraliDocumentoType value) {
        this.datiGeneraliDocumento = value;
    }

    /**
     * Gets the value of the datiOrdineAcquisto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datiOrdineAcquisto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatiOrdineAcquisto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatiDocumentiCorrelatiType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<DatiDocumentiCorrelatiType> getDatiOrdineAcquisto() {
        if (datiOrdineAcquisto == null) {
            datiOrdineAcquisto = new ArrayList<DatiDocumentiCorrelatiType>();
        }
        return this.datiOrdineAcquisto;
    }

    /**
     * Gets the value of the datiContratto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datiContratto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatiContratto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatiDocumentiCorrelatiType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<DatiDocumentiCorrelatiType> getDatiContratto() {
        if (datiContratto == null) {
            datiContratto = new ArrayList<DatiDocumentiCorrelatiType>();
        }
        return this.datiContratto;
    }

    /**
     * Gets the value of the datiConvenzione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datiConvenzione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatiConvenzione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatiDocumentiCorrelatiType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<DatiDocumentiCorrelatiType> getDatiConvenzione() {
        if (datiConvenzione == null) {
            datiConvenzione = new ArrayList<DatiDocumentiCorrelatiType>();
        }
        return this.datiConvenzione;
    }

    /**
     * Gets the value of the datiRicezione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datiRicezione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatiRicezione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatiDocumentiCorrelatiType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<DatiDocumentiCorrelatiType> getDatiRicezione() {
        if (datiRicezione == null) {
            datiRicezione = new ArrayList<DatiDocumentiCorrelatiType>();
        }
        return this.datiRicezione;
    }

    /**
     * Gets the value of the datiFattureCollegate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datiFattureCollegate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatiFattureCollegate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatiDocumentiCorrelatiType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<DatiDocumentiCorrelatiType> getDatiFattureCollegate() {
        if (datiFattureCollegate == null) {
            datiFattureCollegate = new ArrayList<DatiDocumentiCorrelatiType>();
        }
        return this.datiFattureCollegate;
    }

    /**
     * Gets the value of the datiSAL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datiSAL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatiSAL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatiSALType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<DatiSALType> getDatiSAL() {
        if (datiSAL == null) {
            datiSAL = new ArrayList<DatiSALType>();
        }
        return this.datiSAL;
    }

    /**
     * Gets the value of the datiDDT property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datiDDT property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatiDDT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatiDDTType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<DatiDDTType> getDatiDDT() {
        if (datiDDT == null) {
            datiDDT = new ArrayList<DatiDDTType>();
        }
        return this.datiDDT;
    }

    /**
     * Gets the value of the datiTrasporto property.
     * 
     * @return
     *     possible object is
     *     {@link DatiTrasportoType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public DatiTrasportoType getDatiTrasporto() {
        return datiTrasporto;
    }

    /**
     * Sets the value of the datiTrasporto property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiTrasportoType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDatiTrasporto(DatiTrasportoType value) {
        this.datiTrasporto = value;
    }

    /**
     * Gets the value of the fatturaPrincipale property.
     * 
     * @return
     *     possible object is
     *     {@link FatturaPrincipaleType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public FatturaPrincipaleType getFatturaPrincipale() {
        return fatturaPrincipale;
    }

    /**
     * Sets the value of the fatturaPrincipale property.
     * 
     * @param value
     *     allowed object is
     *     {@link FatturaPrincipaleType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setFatturaPrincipale(FatturaPrincipaleType value) {
        this.fatturaPrincipale = value;
    }

}
