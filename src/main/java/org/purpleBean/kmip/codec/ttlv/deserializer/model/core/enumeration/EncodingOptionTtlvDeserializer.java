package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;

public class EncodingOptionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<EncodingOption, Integer> {

    public EncodingOptionTtlvDeserializer() {
        super(EncodingOption.kmipTag, EncodingOption.encodingType, Integer.class, value -> EncodingOption.fromValue(value).inst());
    }
}