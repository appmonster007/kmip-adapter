package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.IterationCount;

public class IterationCountTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<IterationCount, Integer> {

    public IterationCountTtlvSerializer() {
        super(IterationCount::getValue);
    }
}