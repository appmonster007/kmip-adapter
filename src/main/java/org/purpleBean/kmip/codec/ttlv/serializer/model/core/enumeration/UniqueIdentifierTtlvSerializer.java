package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.UniqueIdentifier;

public class UniqueIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<UniqueIdentifier, Integer> {

    public UniqueIdentifierTtlvSerializer() {
        super(UniqueIdentifier::getValue);
    }
}