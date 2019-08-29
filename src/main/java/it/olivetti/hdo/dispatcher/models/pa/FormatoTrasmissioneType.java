
package it.olivetti.hdo.dispatcher.models.pa;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FormatoTrasmissioneType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FormatoTrasmissioneType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="5"/>
 *     &lt;enumeration value="FPA12"/>
 *     &lt;enumeration value="FPR12"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FormatoTrasmissioneType")
@XmlEnum
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public enum FormatoTrasmissioneType {


    /**
     * Fattura verso PA
     * 
     */
    @XmlEnumValue("FPA12")
    FPA_12("FPA12"),

    /**
     * Fattura verso privati
     * 
     */
    @XmlEnumValue("FPR12")
    FPR_12("FPR12");
    private final String value;

    FormatoTrasmissioneType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FormatoTrasmissioneType fromValue(String v) {
        for (FormatoTrasmissioneType c: FormatoTrasmissioneType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
