package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.IterationCount;

public class IterationCountJsonSerializer extends AbstractKmipJsonSerializer<IterationCount, Integer> {

    public IterationCountJsonSerializer() {
        super(IterationCount::getValue);
    }
}