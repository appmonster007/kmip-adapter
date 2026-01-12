package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.ContactInformation;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ContactInformationXmlSerializer extends AbstractKmipXmlSerializer<ContactInformation, String> {

    public ContactInformationXmlSerializer() {
        super(ContactInformation::getValue);
    }
}