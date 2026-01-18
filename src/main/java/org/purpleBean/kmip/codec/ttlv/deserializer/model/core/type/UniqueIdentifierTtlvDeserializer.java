package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class UniqueIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UniqueIdentifier, String> {

    public UniqueIdentifierTtlvDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType, String.class, value -> UniqueIdentifier.builder().value(value).build());
    }
}