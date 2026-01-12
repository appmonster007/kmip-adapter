package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.Operation;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class OperationXmlSerializer extends AbstractKmipXmlSerializer<Operation, String> {

    public OperationXmlSerializer() {
        super(Operation::getDescription);
    }
}