package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.Operation;

public class OperationJsonDeserializer extends AbstractKmipJsonDeserializer<Operation, String> {

    public OperationJsonDeserializer() {
        super(Operation.kmipTag, Operation.encodingType, String.class, value -> new Operation(Operation.fromName(value)));
    }
}