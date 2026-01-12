package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.PrivateKeyUniqueIdentifier;

public class PrivateKeyUniqueIdentifierJsonSerializer extends AbstractKmipJsonSerializer<PrivateKeyUniqueIdentifier, String> {

    public PrivateKeyUniqueIdentifierJsonSerializer() {
        super(PrivateKeyUniqueIdentifier::getValue);
    }
}