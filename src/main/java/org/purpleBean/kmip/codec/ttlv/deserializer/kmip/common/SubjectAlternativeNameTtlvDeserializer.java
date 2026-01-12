package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.SubjectAlternativeName;

import java.nio.ByteBuffer;

public class SubjectAlternativeNameTtlvDeserializer extends AbstractKmipTtlvDeserializer<SubjectAlternativeName, ByteBuffer> {

    public SubjectAlternativeNameTtlvDeserializer() {
        super(SubjectAlternativeName.kmipTag, SubjectAlternativeName.encodingType, ByteBuffer.class, value -> SubjectAlternativeName.builder().value(value).build());
    }
}