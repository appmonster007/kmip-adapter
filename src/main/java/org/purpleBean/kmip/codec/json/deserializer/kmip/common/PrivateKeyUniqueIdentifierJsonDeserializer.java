package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.PrivateKeyUniqueIdentifier;

public class PrivateKeyUniqueIdentifierJsonDeserializer extends AbstractKmipJsonDeserializer<PrivateKeyUniqueIdentifier, String> {

    public PrivateKeyUniqueIdentifierJsonDeserializer() {
        super(PrivateKeyUniqueIdentifier.kmipTag, PrivateKeyUniqueIdentifier.encodingType, String.class, value -> PrivateKeyUniqueIdentifier.builder().value(value).build());
    }
}