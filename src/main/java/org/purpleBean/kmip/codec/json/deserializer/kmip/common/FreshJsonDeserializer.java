package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.Fresh;

public class FreshJsonDeserializer extends AbstractKmipJsonDeserializer<Fresh, Boolean> {

    public FreshJsonDeserializer() {
        super(Fresh.kmipTag, Fresh.encodingType, Boolean.class, value -> Fresh.builder().value(value).build());
    }
}