package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;

public class LinkedObjectIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<LinkedObjectIdentifier, String> {

    public LinkedObjectIdentifierJsonSerializer() {
        super(LinkedObjectIdentifier::getValue);
    }
}