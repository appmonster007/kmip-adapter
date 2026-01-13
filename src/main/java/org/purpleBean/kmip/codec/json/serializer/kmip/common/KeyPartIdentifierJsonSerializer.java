package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.KeyPartIdentifier;

public class KeyPartIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyPartIdentifier, Integer> {

    public KeyPartIdentifierJsonSerializer() {
        super(KeyPartIdentifier::getValue);
    }
}