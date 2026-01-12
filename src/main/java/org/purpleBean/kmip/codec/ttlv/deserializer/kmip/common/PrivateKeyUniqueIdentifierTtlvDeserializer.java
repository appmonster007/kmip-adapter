package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.PrivateKeyUniqueIdentifier;

public class PrivateKeyUniqueIdentifierTtlvDeserializer extends AbstractKmipTtlvDeserializer<PrivateKeyUniqueIdentifier, String> {

    public PrivateKeyUniqueIdentifierTtlvDeserializer() {
        super(PrivateKeyUniqueIdentifier.kmipTag, PrivateKeyUniqueIdentifier.encodingType, String.class, value -> PrivateKeyUniqueIdentifier.builder().value(value).build());
    }
}