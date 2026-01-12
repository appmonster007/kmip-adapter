package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.KeyPartIdentifier;

public class KeyPartIdentifierJsonSerializer extends AbstractKmipJsonSerializer<KeyPartIdentifier, Integer> {

    public KeyPartIdentifierJsonSerializer() {
        super(KeyPartIdentifier::getValue);
    }
}