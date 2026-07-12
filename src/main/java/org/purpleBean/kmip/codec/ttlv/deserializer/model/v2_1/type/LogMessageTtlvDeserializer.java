package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.LogMessage;

import java.io.IOException;
import java.nio.ByteBuffer;

public class LogMessageTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<LogMessage, LogMessage.LogMessageBuilder> {

    public LogMessageTtlvDeserializer() {
        super(LogMessage.kmipTag, LogMessage.encodingType);
    }

    @Override
    protected LogMessage.LogMessageBuilder createBuilder() {
        return LogMessage.builder();
    }

    @Override
    protected void setValue(LogMessage.LogMessageBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected LogMessage build(LogMessage.LogMessageBuilder builder) {
        return builder.build();
    }
}
