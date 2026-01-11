package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.NameValue;

public class NameValueJsonDeserializer extends AbstractKmipJsonDeserializer<NameValue, String> {

    public NameValueJsonDeserializer() {
        super(NameValue.kmipTag, NameValue.encodingType, String.class, value -> NameValue.builder().value(value).build());
    }
}
