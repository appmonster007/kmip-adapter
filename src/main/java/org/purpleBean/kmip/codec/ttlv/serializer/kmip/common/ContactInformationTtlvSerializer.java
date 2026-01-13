package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ContactInformation;

public class ContactInformationTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ContactInformation, String> {

    public ContactInformationTtlvSerializer() {
        super(ContactInformation::getValue);
    }
}