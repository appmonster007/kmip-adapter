package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.BatchCount;

public class BatchCountJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<BatchCount, Integer> {

    public BatchCountJsonDeserializer() {
        super(BatchCount.kmipTag, BatchCount.encodingType, Integer.class, value -> BatchCount.builder().value(value).build());
    }
}