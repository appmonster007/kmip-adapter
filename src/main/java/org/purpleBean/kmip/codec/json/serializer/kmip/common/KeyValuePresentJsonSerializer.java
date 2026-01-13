package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.KeyValuePresent;

public class KeyValuePresentJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyValuePresent, Boolean> {

    public KeyValuePresentJsonSerializer() {
        super(KeyValuePresent::getValue);
    }
}