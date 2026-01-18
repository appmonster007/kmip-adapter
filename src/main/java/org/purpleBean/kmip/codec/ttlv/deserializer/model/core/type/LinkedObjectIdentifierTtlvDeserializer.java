package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;

public class LinkedObjectIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<LinkedObjectIdentifier, String> {

    public LinkedObjectIdentifierTtlvDeserializer() {
        super(LinkedObjectIdentifier.kmipTag, LinkedObjectIdentifier.encodingType, String.class, value -> LinkedObjectIdentifier.builder().value(value).build());
    }
}