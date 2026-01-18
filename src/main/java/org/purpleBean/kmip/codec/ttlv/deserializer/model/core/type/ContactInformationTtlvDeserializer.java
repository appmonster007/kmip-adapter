package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.ContactInformation;

public class ContactInformationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ContactInformation, String> {

    public ContactInformationTtlvDeserializer() {
        super(ContactInformation.kmipTag, ContactInformation.encodingType, String.class, value -> ContactInformation.builder().value(value).build());
    }
}