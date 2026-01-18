package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.IterationCount;

public class IterationCountJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<IterationCount, Integer> {

    public IterationCountJsonDeserializer() {
        super(IterationCount.kmipTag, IterationCount.encodingType, Integer.class, value -> IterationCount.builder().value(value).build());
    }
}