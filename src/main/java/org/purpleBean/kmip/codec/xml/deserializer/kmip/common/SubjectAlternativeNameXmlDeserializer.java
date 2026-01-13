package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.SubjectAlternativeName;

import java.nio.ByteBuffer;

public class SubjectAlternativeNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SubjectAlternativeName, ByteBuffer> {

    public SubjectAlternativeNameXmlDeserializer() {
        super(SubjectAlternativeName.kmipTag, SubjectAlternativeName.encodingType, ByteBuffer.class, value -> SubjectAlternativeName.builder().value(value).build());
    }
}