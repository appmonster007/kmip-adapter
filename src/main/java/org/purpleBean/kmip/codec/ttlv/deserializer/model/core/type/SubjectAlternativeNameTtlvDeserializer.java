package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SubjectAlternativeNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SubjectAlternativeName, SubjectAlternativeName.SubjectAlternativeNameBuilder> {

    public SubjectAlternativeNameTtlvDeserializer() {
        super(SubjectAlternativeName.kmipTag, SubjectAlternativeName.encodingType);
    }

    @Override
    protected SubjectAlternativeName.SubjectAlternativeNameBuilder createBuilder() {
        return SubjectAlternativeName.builder();
    }

    @Override
    protected void setValue(SubjectAlternativeName.SubjectAlternativeNameBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected SubjectAlternativeName build(SubjectAlternativeName.SubjectAlternativeNameBuilder builder) {
        return builder.build();
    }
}
