package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.InvocationFieldLength;

public class InvocationFieldLengthXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<InvocationFieldLength, Integer> {

    public InvocationFieldLengthXmlDeserializer() {
        super(InvocationFieldLength.kmipTag, InvocationFieldLength.encodingType, Integer.class, value -> InvocationFieldLength.builder().value(value).build());
    }
}