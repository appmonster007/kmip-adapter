package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.KeyValueLocationValue;

public class KeyValueLocationValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyValueLocationValue, String> {

    public KeyValueLocationValueJsonSerializer() {
        super(KeyValueLocationValue::getValue);
    }
}