package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.UniqueIdentifier;

public class UniqueIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UniqueIdentifier, String> {

    public UniqueIdentifierTtlvDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType, String.class, value -> UniqueIdentifier.builder().value(value).build());
    }
}