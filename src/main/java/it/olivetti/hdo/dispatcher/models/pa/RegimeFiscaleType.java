
package it.olivetti.hdo.dispatcher.models.pa;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegimeFiscaleType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RegimeFiscaleType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="4"/>
 *     &lt;enumeration value="RF01"/>
 *     &lt;enumeration value="RF02"/>
 *     &lt;enumeration value="RF04"/>
 *     &lt;enumeration value="RF05"/>
 *     &lt;enumeration value="RF06"/>
 *     &lt;enumeration value="RF07"/>
 *     &lt;enumeration value="RF08"/>
 *     &lt;enumeration value="RF09"/>
 *     &lt;enumeration value="RF10"/>
 *     &lt;enumeration value="RF11"/>
 *     &lt;enumeration value="RF12"/>
 *     &lt;enumeration value="RF13"/>
 *     &lt;enumeration value="RF14"/>
 *     &lt;enumeration value="RF15"/>
 *     &lt;enumeration value="RF16"/>
 *     &lt;enumeration value="RF17"/>
 *     &lt;enumeration value="RF19"/>
 *     &lt;enumeration value="RF18"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RegimeFiscaleType")
@XmlEnum
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-08-28T09:32:39+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public enum RegimeFiscaleType {


    /**
     *  Regime ordinario
     * 
     */
    @XmlEnumValue("RF01")
    RF_01("RF01"),

    /**
     * Regime dei contribuenti minimi (art. 1,c.96-117, L. 244/2007)
     * 
     */
    @XmlEnumValue("RF02")
    RF_02("RF02"),

    /**
     * Agricoltura e attività connesse e pesca (artt. 34 e 34-bis, D.P.R. 633/1972)
     * 
     */
    @XmlEnumValue("RF04")
    RF_04("RF04"),

    /**
     * Vendita sali e tabacchi (art. 74, c.1, D.P.R. 633/1972)
     * 
     */
    @XmlEnumValue("RF05")
    RF_05("RF05"),

    /**
     * Commercio dei fiammiferi (art. 74, c.1, D.P.R. 633/1972)
     * 
     */
    @XmlEnumValue("RF06")
    RF_06("RF06"),

    /**
     * Editoria (art. 74, c.1, D.P.R. 633/1972)
     * 
     */
    @XmlEnumValue("RF07")
    RF_07("RF07"),

    /**
     * Gestione di servizi di telefonia pubblica (art. 74, c.1, D.P.R. 633/1972)
     * 
     */
    @XmlEnumValue("RF08")
    RF_08("RF08"),

    /**
     * Rivendita di documenti di trasporto pubblico e di sosta (art. 74, c.1, D.P.R. 633/1972)
     * 
     */
    @XmlEnumValue("RF09")
    RF_09("RF09"),

    /**
     * Intrattenimenti, giochi e altre attività	di cui alla tariffa allegata al D.P.R. 640/72 (art. 74, c.6, D.P.R. 633/1972)
     * 					
     * 
     */
    @XmlEnumValue("RF10")
    RF_10("RF10"),

    /**
     * Agenzie di viaggi e turismo (art. 74-ter, D.P.R. 633/1972)
     * 
     */
    @XmlEnumValue("RF11")
    RF_11("RF11"),

    /**
     * Agriturismo (art. 5, c.2, L. 413/1991)
     * 
     */
    @XmlEnumValue("RF12")
    RF_12("RF12"),

    /**
     * Vendite a domicilio (art. 25-bis, c.6, D.P.R. 600/1973)
     * 
     */
    @XmlEnumValue("RF13")
    RF_13("RF13"),

    /**
     * Rivendita di beni usati, di oggetti	d’arte, d’antiquariato o da collezione (art.	36, D.L. 41/1995)
     * 
     */
    @XmlEnumValue("RF14")
    RF_14("RF14"),

    /**
     * Agenzie di vendite all’asta di oggetti d’arte, antiquariato o da collezione (art. 40-bis, D.L. 41/1995)
     * 
     */
    @XmlEnumValue("RF15")
    RF_15("RF15"),

    /**
     * IVA per cassa P.A. (art. 6, c.5, D.P.R. 633/1972)
     * 
     */
    @XmlEnumValue("RF16")
    RF_16("RF16"),

    /**
     * IVA per cassa (art. 32-bis, D.L. 83/2012)
     * 
     */
    @XmlEnumValue("RF17")
    RF_17("RF17"),

    /**
     * Regime forfettario
     * 
     */
    @XmlEnumValue("RF19")
    RF_19("RF19"),

    /**
     * Altro
     * 
     */
    @XmlEnumValue("RF18")
    RF_18("RF18");
    private final String value;

    RegimeFiscaleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RegimeFiscaleType fromValue(String v) {
        for (RegimeFiscaleType c: RegimeFiscaleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
