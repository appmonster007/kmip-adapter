package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.NameValue;

public class NameValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<NameValue, String> {

    public NameValueJsonSerializer() {
        super(NameValue::getValue);
    }
}