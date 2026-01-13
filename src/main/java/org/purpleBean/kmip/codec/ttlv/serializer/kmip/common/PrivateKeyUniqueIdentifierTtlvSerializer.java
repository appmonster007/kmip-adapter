package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.PrivateKeyUniqueIdentifier;

public class PrivateKeyUniqueIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PrivateKeyUniqueIdentifier, String> {

    public PrivateKeyUniqueIdentifierTtlvSerializer() {
        super(PrivateKeyUniqueIdentifier::getValue);
    }
}