package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;

public class EncodingOptionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<EncodingOption, String> {

    public EncodingOptionJsonDeserializer() {
        super(EncodingOption.kmipTag, EncodingOption.encodingType, String.class, value -> EncodingOption.fromName(value).inst());
    }
}