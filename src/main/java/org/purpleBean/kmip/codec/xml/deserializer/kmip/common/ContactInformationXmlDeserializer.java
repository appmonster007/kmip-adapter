package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.ContactInformation;

public class ContactInformationXmlDeserializer extends AbstractKmipXmlDeserializer<ContactInformation, String> {

    public ContactInformationXmlDeserializer() {
        super(ContactInformation.kmipTag, ContactInformation.encodingType, String.class, value -> ContactInformation.builder().value(value).build());
    }
}