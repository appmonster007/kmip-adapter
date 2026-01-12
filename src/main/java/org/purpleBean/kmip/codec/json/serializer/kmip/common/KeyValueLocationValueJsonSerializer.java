package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.KeyValueLocationValue;

public class KeyValueLocationValueJsonSerializer extends AbstractKmipJsonSerializer<KeyValueLocationValue, String> {

    public KeyValueLocationValueJsonSerializer() {
        super(KeyValueLocationValue::getValue);
    }
}