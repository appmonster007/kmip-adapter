package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Operation;

public class OperationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Operation, Integer> {

    public OperationTtlvDeserializer() {
        super(Operation.kmipTag, Operation.encodingType, Integer.class, value -> Operation.fromValue(value).inst());
    }
}