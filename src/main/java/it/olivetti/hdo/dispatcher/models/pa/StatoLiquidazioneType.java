
package it.olivetti.hdo.dispatcher.models.pa;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatoLiquidazioneType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StatoLiquidazioneType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LS"/>
 *     &lt;enumeration value="LN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StatoLiquidazioneType")
@XmlEnum
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public enum StatoLiquidazioneType {


    /**
     * in liquidazione
     * 
     */
    LS,

    /**
     * non in liquidazione
     * 
     */
    LN;

    public String value() {
        return name();
    }

    public static StatoLiquidazioneType fromValue(String v) {
        return valueOf(v);
    }

}
