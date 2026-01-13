package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.InitialCounterValue;

public class InitialCounterValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<InitialCounterValue, Integer> {

    public InitialCounterValueJsonSerializer() {
        super(InitialCounterValue::getValue);
    }
}