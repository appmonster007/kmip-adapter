package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.EncodingOption;

public class EncodingOptionXmlDeserializer extends AbstractKmipXmlDeserializer<EncodingOption, String> {

    public EncodingOptionXmlDeserializer() {
        super(EncodingOption.kmipTag, EncodingOption.encodingType, String.class, value -> new EncodingOption(EncodingOption.fromName(value)));
    }
}