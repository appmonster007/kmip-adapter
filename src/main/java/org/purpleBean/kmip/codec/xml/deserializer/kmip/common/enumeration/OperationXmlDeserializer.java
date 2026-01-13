package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.Operation;

public class OperationXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Operation, String> {

    public OperationXmlDeserializer() {
        super(Operation.kmipTag, Operation.encodingType, String.class, value -> new Operation(Operation.fromName(value)));
    }
}