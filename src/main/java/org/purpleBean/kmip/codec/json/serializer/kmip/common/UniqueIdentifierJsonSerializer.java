package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.UniqueIdentifier;

public class UniqueIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<UniqueIdentifier, String> {

    public UniqueIdentifierJsonSerializer() {
        super(UniqueIdentifier::getValue);
    }
}