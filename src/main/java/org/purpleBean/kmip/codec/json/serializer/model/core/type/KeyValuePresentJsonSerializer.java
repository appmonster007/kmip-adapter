package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.KeyValuePresent;

public class KeyValuePresentJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyValuePresent, Boolean> {

    public KeyValuePresentJsonSerializer() {
        super(KeyValuePresent::getValue);
    }
}