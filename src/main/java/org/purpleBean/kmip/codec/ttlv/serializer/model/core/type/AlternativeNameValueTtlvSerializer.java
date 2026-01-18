package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;

public class AlternativeNameValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AlternativeNameValue, String> {

    public AlternativeNameValueTtlvSerializer() {
        super(AlternativeNameValue::getValue);
    }
}