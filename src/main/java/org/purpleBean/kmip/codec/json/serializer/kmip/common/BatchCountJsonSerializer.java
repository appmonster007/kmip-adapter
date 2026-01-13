package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.BatchCount;

public class BatchCountJsonSerializer extends AbstractKmipDataTypeJsonSerializer<BatchCount, Integer> {

    public BatchCountJsonSerializer() {
        super(BatchCount::getValue);
    }
}