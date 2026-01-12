package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.InvocationFieldLength;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class InvocationFieldLengthXmlSerializer extends AbstractKmipXmlSerializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthXmlSerializer() {
        super(InvocationFieldLength::getValue);
    }
}