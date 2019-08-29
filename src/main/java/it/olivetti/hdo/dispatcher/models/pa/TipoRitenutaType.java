
package it.olivetti.hdo.dispatcher.models.pa;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoRitenutaType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoRitenutaType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="4"/>
 *     &lt;enumeration value="RT01"/>
 *     &lt;enumeration value="RT02"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoRitenutaType")
@XmlEnum
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public enum TipoRitenutaType {


    /**
     * Ritenuta di acconto persone fisiche
     * 
     */
    @XmlEnumValue("RT01")
    RT_01("RT01"),

    /**
     * Ritenuta di acconto persone giuridiche
     * 
     */
    @XmlEnumValue("RT02")
    RT_02("RT02");
    private final String value;

    TipoRitenutaType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoRitenutaType fromValue(String v) {
        for (TipoRitenutaType c: TipoRitenutaType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
