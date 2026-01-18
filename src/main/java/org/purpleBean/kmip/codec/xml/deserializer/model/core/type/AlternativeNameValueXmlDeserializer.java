package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;

public class AlternativeNameValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AlternativeNameValue, String> {

    public AlternativeNameValueXmlDeserializer() {
        super(AlternativeNameValue.kmipTag, AlternativeNameValue.encodingType, String.class, value -> AlternativeNameValue.builder().value(value).build());
    }
}