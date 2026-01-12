package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.InvocationFieldLength;

public class InvocationFieldLengthXmlSerializer extends AbstractKmipXmlSerializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthXmlSerializer() {
        super(InvocationFieldLength::getValue);
    }
}