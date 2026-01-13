package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.Operation;

public class OperationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Operation, Integer> {

    public OperationTtlvDeserializer() {
        super(Operation.kmipTag, Operation.encodingType, Integer.class, value -> new Operation(Operation.fromValue(value)));
    }
}