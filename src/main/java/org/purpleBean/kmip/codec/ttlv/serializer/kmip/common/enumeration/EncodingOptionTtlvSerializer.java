package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.EncodingOption;

public class EncodingOptionTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<EncodingOption, Integer> {

    public EncodingOptionTtlvSerializer() {
        super(EncodingOption::getValue);
    }
}