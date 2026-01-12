package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.EncodingOption;

public class EncodingOptionTtlvDeserializer extends AbstractKmipTtlvDeserializer<EncodingOption, Integer> {

    public EncodingOptionTtlvDeserializer() {
        super(EncodingOption.kmipTag, EncodingOption.encodingType, Integer.class, value -> new EncodingOption(EncodingOption.fromValue(value)));
    }
}