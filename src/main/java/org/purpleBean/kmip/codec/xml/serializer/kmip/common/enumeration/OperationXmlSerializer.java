package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.Operation;

public class OperationXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Operation, String> {

    public OperationXmlSerializer() {
        super(Operation::getDescription);
    }
}