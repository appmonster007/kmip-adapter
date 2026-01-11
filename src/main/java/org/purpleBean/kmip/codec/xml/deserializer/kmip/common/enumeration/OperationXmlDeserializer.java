package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.Operation;

public class OperationXmlDeserializer extends AbstractKmipXmlDeserializer<Operation, String> {

    public OperationXmlDeserializer() {
        super(Operation.kmipTag, Operation.encodingType, String.class, value -> new Operation(Operation.fromName(value)));
    }
}