package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.BatchCount;

public class BatchCountJsonSerializer extends AbstractKmipDataTypeJsonSerializer<BatchCount, Integer> {

    public BatchCountJsonSerializer() {
        super(BatchCount::getValue);
    }
}