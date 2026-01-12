package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.NameValue;

public class NameValueJsonSerializer extends AbstractKmipJsonSerializer<NameValue, String> {

    public NameValueJsonSerializer() {
        super(NameValue::getValue);
    }
}