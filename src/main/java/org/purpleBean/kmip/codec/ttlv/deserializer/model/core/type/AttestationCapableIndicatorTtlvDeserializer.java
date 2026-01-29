package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttestationCapableIndicator;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttestationCapableIndicatorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttestationCapableIndicator, AttestationCapableIndicator.AttestationCapableIndicatorBuilder> {

    public AttestationCapableIndicatorTtlvDeserializer() {
        super(AttestationCapableIndicator.kmipTag, AttestationCapableIndicator.encodingType);
    }

    @Override
    protected AttestationCapableIndicator.AttestationCapableIndicatorBuilder createBuilder() {
        return AttestationCapableIndicator.builder();
    }

    @Override
    protected void setValue(AttestationCapableIndicator.AttestationCapableIndicatorBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Boolean.class));
    }

    @Override
    protected AttestationCapableIndicator build(AttestationCapableIndicator.AttestationCapableIndicatorBuilder builder) {
        return builder.build();
    }
}