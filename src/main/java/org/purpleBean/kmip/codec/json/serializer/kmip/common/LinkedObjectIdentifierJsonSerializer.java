package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;

public class LinkedObjectIdentifierJsonSerializer extends AbstractKmipJsonSerializer<LinkedObjectIdentifier, String> {

    public LinkedObjectIdentifierJsonSerializer() {
        super(LinkedObjectIdentifier::getValue);
    }
}