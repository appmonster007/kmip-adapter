package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.UniqueIdentifier;

public class UniqueIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<UniqueIdentifier, String> {

    public UniqueIdentifierTtlvSerializer() {
        super(UniqueIdentifier::getValue);
    }
}