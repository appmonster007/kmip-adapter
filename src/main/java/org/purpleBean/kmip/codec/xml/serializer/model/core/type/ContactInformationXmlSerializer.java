package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.ContactInformation;

public class ContactInformationXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ContactInformation, String> {

    public ContactInformationXmlSerializer() {
        super(ContactInformation::getValue);
    }
}