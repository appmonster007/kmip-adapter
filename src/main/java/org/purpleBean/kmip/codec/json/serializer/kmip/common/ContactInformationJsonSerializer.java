package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ContactInformation;

public class ContactInformationJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ContactInformation, String> {

    public ContactInformationJsonSerializer() {
        super(ContactInformation::getValue);
    }
}