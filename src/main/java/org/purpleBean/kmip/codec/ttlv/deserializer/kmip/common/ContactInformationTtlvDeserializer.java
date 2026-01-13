package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.ContactInformation;

public class ContactInformationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ContactInformation, String> {

    public ContactInformationTtlvDeserializer() {
        super(ContactInformation.kmipTag, ContactInformation.encodingType, String.class, value -> ContactInformation.builder().value(value).build());
    }
}