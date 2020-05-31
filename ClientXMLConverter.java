package com.converters;

import com.models.Car;
import com.models.Client;
import java.io.ByteArrayOutputStream;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ClientXMLConverter {
    private ByteArrayOutputStream bos;


    public ByteArrayOutputStream convertClient(Client client) {

        bos = new ByteArrayOutputStream();

        try {
            JAXBContext context = JAXBContext.newInstance(Client.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(client, bos);

        } catch (JAXBException ex) {
            System.err.println(ex.toString());
        }

        return bos;
    }
}
