package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.BatchCount;

public class BatchCountTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<BatchCount, Integer> {

    public BatchCountTtlvDeserializer() {
        super(BatchCount.kmipTag, BatchCount.encodingType, Integer.class, value -> BatchCount.builder().value(value).build());
    }
}