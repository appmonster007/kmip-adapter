package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;

import java.nio.ByteBuffer;

public class SubjectAlternativeNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SubjectAlternativeName, ByteBuffer> {

    public SubjectAlternativeNameJsonDeserializer() {
        super(SubjectAlternativeName.kmipTag, SubjectAlternativeName.encodingType, ByteBuffer.class, value -> SubjectAlternativeName.builder().value(value).build());
    }
}