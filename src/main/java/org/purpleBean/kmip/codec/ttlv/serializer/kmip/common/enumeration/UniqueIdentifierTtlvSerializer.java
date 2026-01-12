package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.UniqueIdentifier;

public class UniqueIdentifierTtlvSerializer extends AbstractKmipTtlvSerializer<UniqueIdentifier, Integer> {

    public UniqueIdentifierTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}