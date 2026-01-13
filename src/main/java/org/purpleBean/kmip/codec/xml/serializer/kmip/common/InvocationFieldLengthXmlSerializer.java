package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.InvocationFieldLength;

public class InvocationFieldLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthXmlSerializer() {
        super(InvocationFieldLength::getValue);
    }
}