package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.InvocationFieldLength;

public class InvocationFieldLengthXmlDeserializer extends AbstractKmipXmlDeserializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthXmlDeserializer() {
        super(InvocationFieldLength.kmipTag, InvocationFieldLength.encodingType, Integer.class, value -> InvocationFieldLength.builder().value(value).build());
    }
}