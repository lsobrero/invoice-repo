
package it.olivetti.hdo.dispatcher.models.pa;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoScontoMaggiorazioneType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoScontoMaggiorazioneType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="2"/>
 *     &lt;enumeration value="SC"/>
 *     &lt;enumeration value="MG"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoScontoMaggiorazioneType")
@XmlEnum
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public enum TipoScontoMaggiorazioneType {


    /**
     * 
     * 						SC = Sconto
     * 					
     * 
     */
    SC,

    /**
     * 
     * 						MG = Maggiorazione
     * 					
     * 
     */
    MG;

    public String value() {
        return name();
    }

    public static TipoScontoMaggiorazioneType fromValue(String v) {
        return valueOf(v);
    }

}
