package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SubjectDistinguishedNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SubjectDistinguishedName, SubjectDistinguishedName.SubjectDistinguishedNameBuilder> {

    public SubjectDistinguishedNameTtlvDeserializer() {
        super(SubjectDistinguishedName.kmipTag, SubjectDistinguishedName.encodingType);
    }

    @Override
    protected SubjectDistinguishedName.SubjectDistinguishedNameBuilder createBuilder() {
        return SubjectDistinguishedName.builder();
    }

    @Override
    protected void setValue(SubjectDistinguishedName.SubjectDistinguishedNameBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected SubjectDistinguishedName build(SubjectDistinguishedName.SubjectDistinguishedNameBuilder builder) {
        return builder.build();
    }
}
