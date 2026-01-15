package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.InitialCounterValue;

public class InitialCounterValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<InitialCounterValue, Integer> {

    public InitialCounterValueTtlvSerializer() {
        super(InitialCounterValue::getValue);
    }
}