package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.Operation;

public class OperationJsonSerializer extends AbstractKmipJsonSerializer<Operation, String> {

    public OperationJsonSerializer() {
        super(Operation::getDescription);
    }
}