package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.ContactInformation;

public class ContactInformationJsonDeserializer extends AbstractKmipJsonDeserializer<ContactInformation, String> {

    public ContactInformationJsonDeserializer() {
        super(ContactInformation.kmipTag, ContactInformation.encodingType, String.class, value -> ContactInformation.builder().value(value).build());
    }
}