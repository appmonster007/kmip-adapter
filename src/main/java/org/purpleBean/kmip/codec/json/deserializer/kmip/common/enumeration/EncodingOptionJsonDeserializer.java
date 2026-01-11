package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.EncodingOption;

public class EncodingOptionJsonDeserializer extends AbstractKmipJsonDeserializer<EncodingOption, String> {

    public EncodingOptionJsonDeserializer() {
        super(EncodingOption.kmipTag, EncodingOption.encodingType, String.class, value -> new EncodingOption(EncodingOption.fromName(value)));
    }
}