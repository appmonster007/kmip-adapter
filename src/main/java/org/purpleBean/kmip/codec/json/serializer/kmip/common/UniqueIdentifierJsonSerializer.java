package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.UniqueIdentifier;

public class UniqueIdentifierJsonSerializer extends AbstractKmipJsonSerializer<UniqueIdentifier, String> {

    public UniqueIdentifierJsonSerializer() {
        super(UniqueIdentifier::getValue);
    }
}