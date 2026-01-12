package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.KeyValuePresent;

public class KeyValuePresentJsonSerializer extends AbstractKmipJsonSerializer<KeyValuePresent, Boolean> {

    public KeyValuePresentJsonSerializer() {
        super(KeyValuePresent::getValue);
    }
}