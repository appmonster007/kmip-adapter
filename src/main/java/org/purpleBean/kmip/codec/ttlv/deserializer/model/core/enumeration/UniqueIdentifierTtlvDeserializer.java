package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.UniqueIdentifier;

public class UniqueIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UniqueIdentifier, Integer> {

    public UniqueIdentifierTtlvDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType, Integer.class, value -> new UniqueIdentifier(UniqueIdentifier.fromValue(value)));
    }
}