package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.CredentialType;

public class CredentialTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CredentialType, Integer> {

    public CredentialTypeTtlvSerializer() {
        super(CredentialType::getValue);
    }
}