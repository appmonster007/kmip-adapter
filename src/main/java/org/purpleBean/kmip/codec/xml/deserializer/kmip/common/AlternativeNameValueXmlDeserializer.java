package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.AlternativeNameValue;

public class AlternativeNameValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AlternativeNameValue, String> {

    public AlternativeNameValueXmlDeserializer() {
        super(AlternativeNameValue.kmipTag, AlternativeNameValue.encodingType, String.class, value -> AlternativeNameValue.builder().value(value).build());
    }
}