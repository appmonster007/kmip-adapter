package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PublicKeyUniqueIdentifier, String> {

    public PublicKeyUniqueIdentifierJsonSerializer() {
        super(PublicKeyUniqueIdentifier::getValue);
    }
}