package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.Fresh;

public class FreshTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Fresh, Boolean> {

    public FreshTtlvDeserializer() {
        super(Fresh.kmipTag, Fresh.encodingType, Boolean.class, value -> Fresh.builder().value(value).build());
    }
}