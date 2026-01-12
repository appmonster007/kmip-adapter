package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierTtlvSerializer extends AbstractKmipTtlvSerializer<PublicKeyUniqueIdentifier, String> {

    public PublicKeyUniqueIdentifierTtlvSerializer() {
        super(PublicKeyUniqueIdentifier::getValue);
    }
}