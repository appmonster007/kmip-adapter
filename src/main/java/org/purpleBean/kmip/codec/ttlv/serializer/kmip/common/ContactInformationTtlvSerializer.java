package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ContactInformation;

public class ContactInformationTtlvSerializer extends AbstractKmipTtlvSerializer<ContactInformation, String> {

    public ContactInformationTtlvSerializer() {
        super(ContactInformation::getValue);
    }
}