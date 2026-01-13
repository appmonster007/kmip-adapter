package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.AlternativeNameValue;

public class AlternativeNameValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AlternativeNameValue, String> {

    public AlternativeNameValueTtlvDeserializer() {
        super(AlternativeNameValue.kmipTag, AlternativeNameValue.encodingType, String.class, value -> AlternativeNameValue.builder().value(value).build());
    }
}