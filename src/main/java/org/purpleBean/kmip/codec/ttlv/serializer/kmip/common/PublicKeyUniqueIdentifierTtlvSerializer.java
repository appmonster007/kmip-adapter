package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PublicKeyUniqueIdentifier, String> {

    public PublicKeyUniqueIdentifierTtlvSerializer() {
        super(PublicKeyUniqueIdentifier::getValue);
    }
}