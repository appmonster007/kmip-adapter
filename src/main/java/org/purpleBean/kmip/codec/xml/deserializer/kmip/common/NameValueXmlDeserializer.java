package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.NameValue;

public class NameValueXmlDeserializer extends AbstractKmipXmlDeserializer<NameValue, String> {

    public NameValueXmlDeserializer() {
        super(NameValue.kmipTag, NameValue.encodingType, String.class, value -> NameValue.builder().value(value).build());
    }
}