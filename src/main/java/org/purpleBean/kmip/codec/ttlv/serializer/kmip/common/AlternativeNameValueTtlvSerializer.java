package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.AlternativeNameValue;

public class AlternativeNameValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AlternativeNameValue, String> {

    public AlternativeNameValueTtlvSerializer() {
        super(AlternativeNameValue::getValue);
    }
}