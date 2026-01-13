package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.NameValue;

public class NameValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<NameValue, String> {

    public NameValueTtlvSerializer() {
        super(NameValue::getValue);
    }
}