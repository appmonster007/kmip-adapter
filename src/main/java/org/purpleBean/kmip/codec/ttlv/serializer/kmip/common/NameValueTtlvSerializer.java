package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.NameValue;

public class NameValueTtlvSerializer extends AbstractKmipTtlvSerializer<NameValue, String> {

    public NameValueTtlvSerializer() {
        super(NameValue::getValue);
    }
}