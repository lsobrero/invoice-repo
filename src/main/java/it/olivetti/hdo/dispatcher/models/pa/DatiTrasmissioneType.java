
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
 * Blocco relativo ai dati di trasmissione della Fattura Elettronica
 * 
 * <p>Java class for DatiTrasmissioneType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatiTrasmissioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdTrasmittente" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}IdFiscaleType"/>
 *         &lt;element name="ProgressivoInvio" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}String10Type"/>
 *         &lt;element name="FormatoTrasmissione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}FormatoTrasmissioneType"/>
 *         &lt;element name="CodiceDestinatario" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}CodiceDestinatarioType"/>
 *         &lt;element name="ContattiTrasmittente" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}ContattiTrasmittenteType" minOccurs="0"/>
 *         &lt;element name="PECDestinatario" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v1.2}EmailType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiTrasmissioneType", propOrder = {
    "idTrasmittente",
    "progressivoInvio",
    "formatoTrasmissione",
    "codiceDestinatario",
    "contattiTrasmittente",
    "pecDestinatario"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class DatiTrasmissioneType {

    @XmlElement(name = "IdTrasmittente", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected IdFiscaleType idTrasmittente;
    @XmlElement(name = "ProgressivoInvio", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String progressivoInvio;
    @XmlElement(name = "FormatoTrasmissione", required = true)
    @XmlSchemaType(name = "string")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected FormatoTrasmissioneType formatoTrasmissione;
    @XmlElement(name = "CodiceDestinatario", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String codiceDestinatario;
    @XmlElement(name = "ContattiTrasmittente")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected ContattiTrasmittenteType contattiTrasmittente;
    @XmlElement(name = "PECDestinatario")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String pecDestinatario;

    /**
     * Gets the value of the idTrasmittente property.
     * 
     * @return
     *     possible object is
     *     {@link IdFiscaleType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public IdFiscaleType getIdTrasmittente() {
        return idTrasmittente;
    }

    /**
     * Sets the value of the idTrasmittente property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdFiscaleType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setIdTrasmittente(IdFiscaleType value) {
        this.idTrasmittente = value;
    }

    /**
     * Gets the value of the progressivoInvio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getProgressivoInvio() {
        return progressivoInvio;
    }

    /**
     * Sets the value of the progressivoInvio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setProgressivoInvio(String value) {
        this.progressivoInvio = value;
    }

    /**
     * Gets the value of the formatoTrasmissione property.
     * 
     * @return
     *     possible object is
     *     {@link FormatoTrasmissioneType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public FormatoTrasmissioneType getFormatoTrasmissione() {
        return formatoTrasmissione;
    }

    /**
     * Sets the value of the formatoTrasmissione property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormatoTrasmissioneType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setFormatoTrasmissione(FormatoTrasmissioneType value) {
        this.formatoTrasmissione = value;
    }

    /**
     * Gets the value of the codiceDestinatario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getCodiceDestinatario() {
        return codiceDestinatario;
    }

    /**
     * Sets the value of the codiceDestinatario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCodiceDestinatario(String value) {
        this.codiceDestinatario = value;
    }

    /**
     * Gets the value of the contattiTrasmittente property.
     * 
     * @return
     *     possible object is
     *     {@link ContattiTrasmittenteType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public ContattiTrasmittenteType getContattiTrasmittente() {
        return contattiTrasmittente;
    }

    /**
     * Sets the value of the contattiTrasmittente property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContattiTrasmittenteType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setContattiTrasmittente(ContattiTrasmittenteType value) {
        this.contattiTrasmittente = value;
    }

    /**
     * Gets the value of the pecDestinatario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getPECDestinatario() {
        return pecDestinatario;
    }

    /**
     * Sets the value of the pecDestinatario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPECDestinatario(String value) {
        this.pecDestinatario = value;
    }

}
