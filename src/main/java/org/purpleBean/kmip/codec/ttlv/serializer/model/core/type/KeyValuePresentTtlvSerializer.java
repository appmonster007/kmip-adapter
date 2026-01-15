package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.KeyValuePresent;

public class KeyValuePresentTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<KeyValuePresent, Boolean> {

    public KeyValuePresentTtlvSerializer() {
        super(KeyValuePresent::getValue);
    }
}