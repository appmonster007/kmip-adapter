package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class UniqueIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<UniqueIdentifier, String> {

    public UniqueIdentifierJsonSerializer() {
        super(UniqueIdentifier::getValue);
    }
}