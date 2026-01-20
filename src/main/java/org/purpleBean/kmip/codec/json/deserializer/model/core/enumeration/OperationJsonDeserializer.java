package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Operation;

public class OperationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Operation, String> {

    public OperationJsonDeserializer() {
        super(Operation.kmipTag, Operation.encodingType, String.class, value -> Operation.fromName(value).inst());
    }
}