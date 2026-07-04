package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.OtpDigits;

import java.io.IOException;
import java.nio.ByteBuffer;

public class OtpDigitsTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OtpDigits, OtpDigits.OtpDigitsBuilder> {

    public OtpDigitsTtlvDeserializer() {
        super(OtpDigits.kmipTag, OtpDigits.encodingType);
    }

    @Override
    protected OtpDigits.OtpDigitsBuilder createBuilder() {
        return OtpDigits.builder();
    }

    @Override
    protected void setValue(OtpDigits.OtpDigitsBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected OtpDigits build(OtpDigits.OtpDigitsBuilder builder) {
        return builder.build();
    }
}
