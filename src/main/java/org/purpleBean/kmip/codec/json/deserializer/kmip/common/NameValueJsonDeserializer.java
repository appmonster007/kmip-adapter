package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.NameValue;

public class NameValueJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NameValue, String> {

    public NameValueJsonDeserializer() {
        super(NameValue.kmipTag, NameValue.encodingType, String.class, value -> NameValue.builder().value(value).build());
    }
}
