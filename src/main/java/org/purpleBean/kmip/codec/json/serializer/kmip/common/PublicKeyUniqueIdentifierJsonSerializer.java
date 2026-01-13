package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PublicKeyUniqueIdentifier, String> {

    public PublicKeyUniqueIdentifierJsonSerializer() {
        super(PublicKeyUniqueIdentifier::getValue);
    }
}