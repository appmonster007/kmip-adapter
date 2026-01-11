package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.AlternativeNameValue;

public class AlternativeNameValueJsonDeserializer extends AbstractKmipJsonDeserializer<AlternativeNameValue, String> {

    public AlternativeNameValueJsonDeserializer() {
        super(AlternativeNameValue.kmipTag, AlternativeNameValue.encodingType, String.class, value -> AlternativeNameValue.builder().value(value).build());
    }
}