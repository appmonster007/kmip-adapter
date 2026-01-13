package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AlternativeNameValue;

public class AlternativeNameValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AlternativeNameValue, String> {

    public AlternativeNameValueJsonSerializer() {
        super(AlternativeNameValue::getValue);
    }
}