package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.OtpSerial;

import java.io.IOException;
import java.nio.ByteBuffer;

public class OtpSerialTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OtpSerial, OtpSerial.OtpSerialBuilder> {

    public OtpSerialTtlvDeserializer() {
        super(OtpSerial.kmipTag, OtpSerial.encodingType);
    }

    @Override
    protected OtpSerial.OtpSerialBuilder createBuilder() {
        return OtpSerial.builder();
    }

    @Override
    protected void setValue(OtpSerial.OtpSerialBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected OtpSerial build(OtpSerial.OtpSerialBuilder builder) {
        return builder.build();
    }
}
