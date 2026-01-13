package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.Operation;

public class OperationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Operation, String> {

    public OperationJsonDeserializer() {
        super(Operation.kmipTag, Operation.encodingType, String.class, value -> new Operation(Operation.fromName(value)));
    }
}