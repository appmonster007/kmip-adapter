package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.PrivateKeyUniqueIdentifier;

public class PrivateKeyUniqueIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PrivateKeyUniqueIdentifier, String> {

    public PrivateKeyUniqueIdentifierJsonDeserializer() {
        super(PrivateKeyUniqueIdentifier.kmipTag, PrivateKeyUniqueIdentifier.encodingType, String.class, value -> PrivateKeyUniqueIdentifier.builder().value(value).build());
    }
}