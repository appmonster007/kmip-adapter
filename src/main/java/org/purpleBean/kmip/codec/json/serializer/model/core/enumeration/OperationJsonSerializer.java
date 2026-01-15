package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.Operation;

public class OperationJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Operation, String> {

    public OperationJsonSerializer() {
        super(Operation::getDescription);
    }
}