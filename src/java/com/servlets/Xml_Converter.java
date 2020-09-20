package com.servlets;

import com.models.User;
import java.io.ByteArrayOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Xml_Converter {
    private ByteArrayOutputStream bos;


    public ByteArrayOutputStream convertClient(User user) {

        bos = new ByteArrayOutputStream();

        try {
            JAXBContext context = JAXBContext.newInstance(User.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(user, bos);

        } catch (JAXBException ex) {
            System.err.println(ex.toString());
        }

        return bos;
    }
}
