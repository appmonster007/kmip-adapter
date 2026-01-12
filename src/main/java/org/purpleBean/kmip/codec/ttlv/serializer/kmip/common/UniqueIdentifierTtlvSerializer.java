package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.UniqueIdentifier;

public class UniqueIdentifierTtlvSerializer extends AbstractKmipTtlvSerializer<UniqueIdentifier, String> {

    public UniqueIdentifierTtlvSerializer() {
        super(UniqueIdentifier::getValue);
    }
}