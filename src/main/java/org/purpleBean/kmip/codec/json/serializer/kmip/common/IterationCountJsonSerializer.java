package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.IterationCount;

public class IterationCountJsonSerializer extends AbstractKmipDataTypeJsonSerializer<IterationCount, Integer> {

    public IterationCountJsonSerializer() {
        super(IterationCount::getValue);
    }
}