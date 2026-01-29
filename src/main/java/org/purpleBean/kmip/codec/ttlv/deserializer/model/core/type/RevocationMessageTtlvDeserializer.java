package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.RevocationMessage;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RevocationMessageTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RevocationMessage, RevocationMessage.RevocationMessageBuilder> {

    public RevocationMessageTtlvDeserializer() {
        super(RevocationMessage.kmipTag, RevocationMessage.encodingType);
    }

    @Override
    protected RevocationMessage.RevocationMessageBuilder createBuilder() {
        return RevocationMessage.builder();
    }

    @Override
    protected void setValue(RevocationMessage.RevocationMessageBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected RevocationMessage build(RevocationMessage.RevocationMessageBuilder builder) {
        return builder.build();
    }
}
