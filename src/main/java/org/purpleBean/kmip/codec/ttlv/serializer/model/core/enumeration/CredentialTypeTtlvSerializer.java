package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

public class CredentialTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CredentialType, Integer> {

    public CredentialTypeTtlvSerializer() {
        super(CredentialType::getValue);
    }
}