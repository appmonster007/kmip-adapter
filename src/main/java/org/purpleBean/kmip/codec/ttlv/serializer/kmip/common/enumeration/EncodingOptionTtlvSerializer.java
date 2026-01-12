package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.EncodingOption;

public class EncodingOptionTtlvSerializer extends AbstractKmipTtlvSerializer<EncodingOption, Integer> {

    public EncodingOptionTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}