package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierTtlvDeserializer extends AbstractKmipTtlvDeserializer<ReplacedUniqueIdentifier, String> {

    public ReplacedUniqueIdentifierTtlvDeserializer() {
        super(ReplacedUniqueIdentifier.kmipTag, ReplacedUniqueIdentifier.encodingType, String.class, value -> ReplacedUniqueIdentifier.builder().value(value).build());
    }
}