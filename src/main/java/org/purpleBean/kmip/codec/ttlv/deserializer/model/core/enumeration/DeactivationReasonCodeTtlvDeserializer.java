package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.DeactivationReasonCode;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DeactivationReasonCodeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DeactivationReasonCode, DeactivationReasonCode.DeactivationReasonCodeBuilder> {

    public DeactivationReasonCodeTtlvDeserializer() {
        super(DeactivationReasonCode.kmipTag, DeactivationReasonCode.encodingType);
    }

    @Override
    protected DeactivationReasonCode.DeactivationReasonCodeBuilder createBuilder() {
        return DeactivationReasonCode.builder();
    }

    @Override
    protected void setValue(DeactivationReasonCode.DeactivationReasonCodeBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(DeactivationReasonCode.fromValue(value));
    }

    @Override
    protected DeactivationReasonCode build(DeactivationReasonCode.DeactivationReasonCodeBuilder builder) {
        return builder.build();
    }
}
