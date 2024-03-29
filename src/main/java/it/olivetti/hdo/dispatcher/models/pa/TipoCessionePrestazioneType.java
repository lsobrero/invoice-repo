
package it.olivetti.hdo.dispatcher.models.pa;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoCessionePrestazioneType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoCessionePrestazioneType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="2"/>
 *     &lt;enumeration value="SC"/>
 *     &lt;enumeration value="PR"/>
 *     &lt;enumeration value="AB"/>
 *     &lt;enumeration value="AC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoCessionePrestazioneType")
@XmlEnum
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public enum TipoCessionePrestazioneType {


    /**
     * Sconto
     * 
     */
    SC,

    /**
     * Premio
     * 
     */
    PR,

    /**
     * Abbuono
     * 
     */
    AB,

    /**
     * Spesa accessoria
     * 
     */
    AC;

    public String value() {
        return name();
    }

    public static TipoCessionePrestazioneType fromValue(String v) {
        return valueOf(v);
    }

}
