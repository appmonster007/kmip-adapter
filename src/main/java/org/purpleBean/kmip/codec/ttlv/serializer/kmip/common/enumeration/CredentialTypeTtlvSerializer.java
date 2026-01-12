package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.CredentialType;

public class CredentialTypeTtlvSerializer extends AbstractKmipTtlvSerializer<CredentialType, Integer> {

    public CredentialTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}