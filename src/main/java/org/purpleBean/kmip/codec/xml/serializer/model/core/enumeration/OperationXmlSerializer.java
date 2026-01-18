package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.Operation;

public class OperationXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Operation, String> {

    public OperationXmlSerializer() {
        super(Operation::getDescription);
    }
}