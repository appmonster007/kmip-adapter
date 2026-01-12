package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.BatchCount;

public class BatchCountJsonSerializer extends AbstractKmipJsonSerializer<BatchCount, Integer> {

    public BatchCountJsonSerializer() {
        super(BatchCount::getValue);
    }
}