package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.UniqueIdentifier;

public class UniqueIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<UniqueIdentifier, Integer> {

    public UniqueIdentifierTtlvSerializer() {
        super(UniqueIdentifier::getValue);
    }
}