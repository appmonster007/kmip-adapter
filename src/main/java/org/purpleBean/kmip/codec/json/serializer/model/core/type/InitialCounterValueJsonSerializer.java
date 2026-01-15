package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.InitialCounterValue;

public class InitialCounterValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<InitialCounterValue, Integer> {

    public InitialCounterValueJsonSerializer() {
        super(InitialCounterValue::getValue);
    }
}