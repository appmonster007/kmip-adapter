package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DeactivationMessage;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DeactivationMessageTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DeactivationMessage, DeactivationMessage.DeactivationMessageBuilder> {

    public DeactivationMessageTtlvDeserializer() {
        super(DeactivationMessage.kmipTag, DeactivationMessage.encodingType);
    }

    @Override
    protected DeactivationMessage.DeactivationMessageBuilder createBuilder() {
        return DeactivationMessage.builder();
    }

    @Override
    protected void setValue(DeactivationMessage.DeactivationMessageBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected DeactivationMessage build(DeactivationMessage.DeactivationMessageBuilder builder) {
        return builder.build();
    }
}
