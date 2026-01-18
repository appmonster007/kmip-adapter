package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;

public class EncodingOptionTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<EncodingOption, Integer> {

    public EncodingOptionTtlvSerializer() {
        super(EncodingOption::getValue);
    }
}