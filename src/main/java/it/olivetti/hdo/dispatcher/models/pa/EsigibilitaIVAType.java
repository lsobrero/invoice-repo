
package it.olivetti.hdo.dispatcher.models.pa;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EsigibilitaIVAType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EsigibilitaIVAType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;minLength value="1"/>
 *     &lt;maxLength value="1"/>
 *     &lt;enumeration value="D"/>
 *     &lt;enumeration value="I"/>
 *     &lt;enumeration value="S"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EsigibilitaIVAType")
@XmlEnum
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public enum EsigibilitaIVAType {


    /**
     * esigibilità differita
     * 
     */
    D,

    /**
     * esigibilità immediata
     * 
     */
    I,

    /**
     * scissione dei pagamenti
     * 
     */
    S;

    public String value() {
        return name();
    }

    public static EsigibilitaIVAType fromValue(String v) {
        return valueOf(v);
    }

}
