package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ContactInformation;

public class ContactInformationXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ContactInformation, String> {

    public ContactInformationXmlSerializer() {
        super(ContactInformation::getValue);
    }
}