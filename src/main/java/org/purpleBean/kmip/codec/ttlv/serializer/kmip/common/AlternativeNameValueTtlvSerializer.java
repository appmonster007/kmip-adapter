package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.AlternativeNameValue;

public class AlternativeNameValueTtlvSerializer extends AbstractKmipTtlvSerializer<AlternativeNameValue, String> {

    public AlternativeNameValueTtlvSerializer() {
        super(AlternativeNameValue::getValue);
    }
}