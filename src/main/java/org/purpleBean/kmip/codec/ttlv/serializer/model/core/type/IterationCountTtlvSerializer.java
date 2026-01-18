package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.IterationCount;

public class IterationCountTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<IterationCount, Integer> {

    public IterationCountTtlvSerializer() {
        super(IterationCount::getValue);
    }
}