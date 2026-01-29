package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttestationTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttestationType, AttestationType.AttestationTypeBuilder> {

    public AttestationTypeTtlvDeserializer() {
        super(AttestationType.kmipTag, AttestationType.encodingType);
    }

    @Override
    protected AttestationType.AttestationTypeBuilder createBuilder() {
        return AttestationType.builder();
    }

    @Override
    protected void setValue(AttestationType.AttestationTypeBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(AttestationType.fromValue(value));
    }

    @Override
    protected AttestationType build(AttestationType.AttestationTypeBuilder builder) {
        return builder.build();
    }
}
