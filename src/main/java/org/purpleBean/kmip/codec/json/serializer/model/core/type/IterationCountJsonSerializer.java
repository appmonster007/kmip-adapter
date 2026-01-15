package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.IterationCount;

public class IterationCountJsonSerializer extends AbstractKmipDataTypeJsonSerializer<IterationCount, Integer> {

    public IterationCountJsonSerializer() {
        super(IterationCount::getValue);
    }
}