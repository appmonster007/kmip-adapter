package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.KeyPartIdentifier;

public class KeyPartIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyPartIdentifier, Integer> {

    public KeyPartIdentifierJsonSerializer() {
        super(KeyPartIdentifier::getValue);
    }
}