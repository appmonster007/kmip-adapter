package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.BatchCount;

public class BatchCountJsonDeserializer extends AbstractKmipJsonDeserializer<BatchCount, Integer> {

    public BatchCountJsonDeserializer() {
        super(BatchCount.kmipTag, BatchCount.encodingType, Integer.class, value -> BatchCount.builder().value(value).build());
    }
}