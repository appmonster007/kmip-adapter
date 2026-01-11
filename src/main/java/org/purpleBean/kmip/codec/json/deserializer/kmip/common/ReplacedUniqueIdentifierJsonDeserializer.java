package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierJsonDeserializer extends AbstractKmipJsonDeserializer<ReplacedUniqueIdentifier, String> {

    public ReplacedUniqueIdentifierJsonDeserializer() {
        super(ReplacedUniqueIdentifier.kmipTag, ReplacedUniqueIdentifier.encodingType, String.class, value -> ReplacedUniqueIdentifier.builder().value(value).build());
    }
}