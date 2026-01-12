package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.Operation;

public class OperationTtlvSerializer extends AbstractKmipTtlvSerializer<Operation, Integer> {

    public OperationTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}