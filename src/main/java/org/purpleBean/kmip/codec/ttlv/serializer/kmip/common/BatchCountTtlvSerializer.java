package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.BatchCount;

public class BatchCountTtlvSerializer extends AbstractKmipTtlvSerializer<BatchCount, Integer> {

    public BatchCountTtlvSerializer() {
        super(BatchCount::getValue);
    }
}