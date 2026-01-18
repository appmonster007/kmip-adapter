package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.BatchCount;

public class BatchCountTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<BatchCount, Integer> {

    public BatchCountTtlvSerializer() {
        super(BatchCount::getValue);
    }
}