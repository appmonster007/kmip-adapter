package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PublicKeyUniqueIdentifier, String> {

    public PublicKeyUniqueIdentifierJsonDeserializer() {
        super(PublicKeyUniqueIdentifier.kmipTag, PublicKeyUniqueIdentifier.encodingType, String.class, value -> PublicKeyUniqueIdentifier.builder().value(value).build());
    }
}