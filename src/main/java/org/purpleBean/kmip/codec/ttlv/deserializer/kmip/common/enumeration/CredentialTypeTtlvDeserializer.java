package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.CredentialType;

public class CredentialTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<CredentialType, Integer> {

    public CredentialTypeTtlvDeserializer() {
        super(CredentialType.kmipTag, CredentialType.encodingType, Integer.class, value -> new CredentialType(CredentialType.fromValue(value)));
    }
}