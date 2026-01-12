package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierJsonSerializer extends AbstractKmipJsonSerializer<PublicKeyUniqueIdentifier, String> {

    public PublicKeyUniqueIdentifierJsonSerializer() {
        super(PublicKeyUniqueIdentifier::getValue);
    }
}