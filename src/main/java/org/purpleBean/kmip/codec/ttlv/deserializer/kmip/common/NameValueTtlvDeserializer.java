package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.NameValue;

public class NameValueTtlvDeserializer extends AbstractKmipTtlvDeserializer<NameValue, String> {

    public NameValueTtlvDeserializer() {
        super(NameValue.kmipTag, NameValue.encodingType, String.class, value -> NameValue.builder().value(value).build());
    }
}