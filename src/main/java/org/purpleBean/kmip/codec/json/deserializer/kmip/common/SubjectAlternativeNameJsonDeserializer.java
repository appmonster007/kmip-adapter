package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.SubjectAlternativeName;

import java.nio.ByteBuffer;

public class SubjectAlternativeNameJsonDeserializer extends AbstractKmipJsonDeserializer<SubjectAlternativeName, ByteBuffer> {

    public SubjectAlternativeNameJsonDeserializer() {
        super(SubjectAlternativeName.kmipTag, SubjectAlternativeName.encodingType, ByteBuffer.class, value -> SubjectAlternativeName.builder().value(value).build());
    }
}