package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

public class CredentialTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CredentialType, Integer> {

    public CredentialTypeTtlvDeserializer() {
        super(CredentialType.kmipTag, CredentialType.encodingType, Integer.class, value -> new CredentialType(CredentialType.fromValue(value)));
    }
}