package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.NameValue;

public class NameValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<NameValue, String> {

    public NameValueTtlvSerializer() {
        super(NameValue::getValue);
    }
}