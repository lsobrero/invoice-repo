package net.anet.invoice.repo.fattel;

import it.olivetti.hdo.dispatcher.models.pa.FatturaElettronicaHeaderType;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import it.olivetti.hdo.dispatcher.models.pa.FatturaElettronicaType;
import org.apache.commons.io.input.BOMInputStream;

/**
 * Created by claudio on 25/07/16.
 */
public class XMLParser {

    private XMLParser(){}

    public static Object parseXML(String fileToParse) throws Exception {

        Object obj = null;
        boolean isValidated = true;
        XMLInputFactory xif = XMLInputFactory.newInstance();

        JAXBContext jaxbContext = null;

        Unmarshaller jaxbUnmarshaller = null;

        if (isValidated == true) {
            FileInputStream in=new FileInputStream(new File(fileToParse));
            BOMInputStream bomIS = new BOMInputStream(in);
            XMLEventReader xer = xif.createXMLEventReader(bomIS);
            while (xer.hasNext()) {
                XMLEvent xe = xer.peek();

                if (xe.isStartElement()) {
                    StartElement startElement = xe.asStartElement();
                    if (startElement.getName().getLocalPart()
                        .equals("FatturaElettronica")) {
                        jaxbContext = JAXBContext
                            .newInstance(FatturaElettronicaType.class);

                        jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                        JAXBElement<FatturaElettronicaType> jax_b = jaxbUnmarshaller
                            .unmarshal(xer, FatturaElettronicaType.class);
                        obj = jax_b.getValue();
                        break;
                    }
/**
                    else if (startElement.getName().getLocalPart()
                        .equals("FatturaElettronicaSemplificata")) {
                        jaxbContext = JAXBContext
                            .newInstance(FatturaElettronicaSemplificata.class);

                        jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                        JAXBElement<FatturaElettronicaSemplificata> jax_b = jaxbUnmarshaller
                            .unmarshal(xer,
                                FatturaElettronicaSemplificata.class);
                        obj = jax_b.getValue();
                        break;
                    }
 */
                }
                xer.next();
            }
            if(bomIS!=null)
                bomIS.close();
        }
        return obj;
    }

/*
    public static boolean isSemplificata(Object obj) {

        if (obj.getClass().isInstance(new FatturaElettronicaSemplificata()))
            return true;
        return false;

    }

 */
}

