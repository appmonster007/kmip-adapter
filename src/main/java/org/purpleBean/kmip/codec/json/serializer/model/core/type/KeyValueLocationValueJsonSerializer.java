package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.KeyValueLocationValue;

public class KeyValueLocationValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyValueLocationValue, String> {

    public KeyValueLocationValueJsonSerializer() {
        super(KeyValueLocationValue::getValue);
    }
}