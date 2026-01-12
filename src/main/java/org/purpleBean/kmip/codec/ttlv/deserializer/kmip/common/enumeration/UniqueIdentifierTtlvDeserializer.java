package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.UniqueIdentifier;

public class UniqueIdentifierTtlvDeserializer extends AbstractKmipTtlvDeserializer<UniqueIdentifier, Integer> {

    public UniqueIdentifierTtlvDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType, Integer.class, value -> new UniqueIdentifier(UniqueIdentifier.fromValue(value)));
    }
}