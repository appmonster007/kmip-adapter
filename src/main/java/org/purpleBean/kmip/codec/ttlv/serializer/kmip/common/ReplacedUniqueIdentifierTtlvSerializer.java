package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierTtlvSerializer extends AbstractKmipTtlvSerializer<ReplacedUniqueIdentifier, String> {

    public ReplacedUniqueIdentifierTtlvSerializer() {
        super(ReplacedUniqueIdentifier::getValue);
    }
}