package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;

public class LinkedObjectIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<LinkedObjectIdentifier, String> {

    public LinkedObjectIdentifierJsonSerializer() {
        super(LinkedObjectIdentifier::getValue);
    }
}