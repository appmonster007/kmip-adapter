package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.Fresh;

public class FreshTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Fresh, Boolean> {

    public FreshTtlvDeserializer() {
        super(Fresh.kmipTag, Fresh.encodingType, Boolean.class, value -> Fresh.builder().value(value).build());
    }
}