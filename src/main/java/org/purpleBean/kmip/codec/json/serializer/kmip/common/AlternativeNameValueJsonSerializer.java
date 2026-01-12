package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.AlternativeNameValue;

public class AlternativeNameValueJsonSerializer extends AbstractKmipJsonSerializer<AlternativeNameValue, String> {

    public AlternativeNameValueJsonSerializer() {
        super(AlternativeNameValue::getValue);
    }
}