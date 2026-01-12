package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.ContactInformation;

public class ContactInformationJsonSerializer extends AbstractKmipJsonSerializer<ContactInformation, String> {

    public ContactInformationJsonSerializer() {
        super(ContactInformation::getValue);
    }
}