package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.UniqueIdentifier;

public class UniqueIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<UniqueIdentifier, String> {

    public UniqueIdentifierJsonSerializer() {
        super(UniqueIdentifier::getDescription);
    }
}