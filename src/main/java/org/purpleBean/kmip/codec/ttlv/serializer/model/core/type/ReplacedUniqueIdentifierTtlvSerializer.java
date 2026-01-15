package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ReplacedUniqueIdentifier, String> {

    public ReplacedUniqueIdentifierTtlvSerializer() {
        super(ReplacedUniqueIdentifier::getValue);
    }
}