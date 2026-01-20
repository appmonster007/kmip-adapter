package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;

public class EncodingOptionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<EncodingOption, String> {

    public EncodingOptionXmlDeserializer() {
        super(EncodingOption.kmipTag, EncodingOption.encodingType, String.class, value -> EncodingOption.fromName(value).inst());
    }
}