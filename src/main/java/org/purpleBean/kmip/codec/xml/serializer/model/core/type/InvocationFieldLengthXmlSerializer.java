package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.InvocationFieldLength;

public class InvocationFieldLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthXmlSerializer() {
        super(InvocationFieldLength::getValue);
    }
}