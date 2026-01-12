package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierTtlvDeserializer extends AbstractKmipTtlvDeserializer<PublicKeyUniqueIdentifier, String> {

    public PublicKeyUniqueIdentifierTtlvDeserializer() {
        super(PublicKeyUniqueIdentifier.kmipTag, PublicKeyUniqueIdentifier.encodingType, String.class, value -> PublicKeyUniqueIdentifier.builder().value(value).build());
    }
}