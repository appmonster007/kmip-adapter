package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ReplacedUniqueIdentifier, String> {

    public ReplacedUniqueIdentifierTtlvSerializer() {
        super(ReplacedUniqueIdentifier::getValue);
    }
}