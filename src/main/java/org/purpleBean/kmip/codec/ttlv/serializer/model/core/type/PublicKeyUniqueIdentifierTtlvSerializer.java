package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PublicKeyUniqueIdentifier, String> {

    public PublicKeyUniqueIdentifierTtlvSerializer() {
        super(PublicKeyUniqueIdentifier::getValue);
    }
}