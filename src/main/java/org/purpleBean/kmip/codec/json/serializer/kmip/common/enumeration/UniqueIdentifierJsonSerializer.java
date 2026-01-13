package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.UniqueIdentifier;

public class UniqueIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<UniqueIdentifier, String> {

    public UniqueIdentifierJsonSerializer() {
        super(UniqueIdentifier::getDescription);
    }
}