package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.InitialCounterValue;

public class InitialCounterValueJsonSerializer extends AbstractKmipJsonSerializer<InitialCounterValue, Integer> {

    public InitialCounterValueJsonSerializer() {
        super(InitialCounterValue::getValue);
    }
}