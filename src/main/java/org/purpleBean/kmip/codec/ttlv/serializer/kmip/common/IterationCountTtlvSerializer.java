package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.IterationCount;

public class IterationCountTtlvSerializer extends AbstractKmipTtlvSerializer<IterationCount, Integer> {

    public IterationCountTtlvSerializer() {
        super(IterationCount::getValue);
    }
}