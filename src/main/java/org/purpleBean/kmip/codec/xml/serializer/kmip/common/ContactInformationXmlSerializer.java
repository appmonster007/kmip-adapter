package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.ContactInformation;

public class ContactInformationXmlSerializer extends AbstractKmipXmlSerializer<ContactInformation, String> {

    public ContactInformationXmlSerializer() {
        super(ContactInformation::getValue);
    }
}