package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ReplacedUniqueIdentifier, String> {

    public ReplacedUniqueIdentifierTtlvDeserializer() {
        super(ReplacedUniqueIdentifier.kmipTag, ReplacedUniqueIdentifier.encodingType, String.class, value -> ReplacedUniqueIdentifier.builder().value(value).build());
    }
}