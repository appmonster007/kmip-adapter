package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.Operation;

public class OperationJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Operation, String> {

    public OperationJsonSerializer() {
        super(Operation::getDescription);
    }
}