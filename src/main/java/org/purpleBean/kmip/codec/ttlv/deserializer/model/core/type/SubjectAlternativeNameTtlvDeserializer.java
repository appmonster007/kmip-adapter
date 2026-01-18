package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;

import java.nio.ByteBuffer;

public class SubjectAlternativeNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SubjectAlternativeName, ByteBuffer> {

    public SubjectAlternativeNameTtlvDeserializer() {
        super(SubjectAlternativeName.kmipTag, SubjectAlternativeName.encodingType, ByteBuffer.class, value -> SubjectAlternativeName.builder().value(value).build());
    }
}