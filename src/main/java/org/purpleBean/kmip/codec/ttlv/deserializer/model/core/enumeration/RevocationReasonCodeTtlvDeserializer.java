package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.RevocationReasonCode;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RevocationReasonCodeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RevocationReasonCode, RevocationReasonCode.RevocationReasonCodeBuilder> {

    public RevocationReasonCodeTtlvDeserializer() {
        super(RevocationReasonCode.kmipTag, RevocationReasonCode.encodingType);
    }

    @Override
    protected RevocationReasonCode.RevocationReasonCodeBuilder createBuilder() {
        return RevocationReasonCode.builder();
    }

    @Override
    protected void setValue(RevocationReasonCode.RevocationReasonCodeBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(RevocationReasonCode.fromValue(value));
    }

    @Override
    protected RevocationReasonCode build(RevocationReasonCode.RevocationReasonCodeBuilder builder) {
        return builder.build();
    }
}
