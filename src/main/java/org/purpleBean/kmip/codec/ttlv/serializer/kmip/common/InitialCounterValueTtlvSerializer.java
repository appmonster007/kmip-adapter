package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.InitialCounterValue;

public class InitialCounterValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<InitialCounterValue, Integer> {

    public InitialCounterValueTtlvSerializer() {
        super(InitialCounterValue::getValue);
    }
}