package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.BatchCount;

public class BatchCountTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<BatchCount, Integer> {

    public BatchCountTtlvSerializer() {
        super(BatchCount::getValue);
    }
}