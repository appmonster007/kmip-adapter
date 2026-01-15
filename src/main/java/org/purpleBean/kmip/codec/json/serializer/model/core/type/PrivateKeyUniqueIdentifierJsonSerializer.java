package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;

public class PrivateKeyUniqueIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PrivateKeyUniqueIdentifier, String> {

    public PrivateKeyUniqueIdentifierJsonSerializer() {
        super(PrivateKeyUniqueIdentifier::getValue);
    }
}