package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;

public class LinkedObjectIdentifierTtlvDeserializer extends AbstractKmipTtlvDeserializer<LinkedObjectIdentifier, String> {

    public LinkedObjectIdentifierTtlvDeserializer() {
        super(LinkedObjectIdentifier.kmipTag, LinkedObjectIdentifier.encodingType, String.class, value -> LinkedObjectIdentifier.builder().value(value).build());
    }
}