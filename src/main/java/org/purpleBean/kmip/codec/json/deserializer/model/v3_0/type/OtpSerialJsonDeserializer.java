package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v3_0.type.OtpSerial;

import java.io.IOException;

public class OtpSerialJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<OtpSerial, OtpSerial.OtpSerialBuilder> {

    public OtpSerialJsonDeserializer() {
        super(OtpSerial.kmipTag, OtpSerial.encodingType);
    }

    @Override
    protected OtpSerial.OtpSerialBuilder createBuilder() {
        return OtpSerial.builder();
    }

    @Override
    protected void setValue(OtpSerial.OtpSerialBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected OtpSerial build(OtpSerial.OtpSerialBuilder builder) {
        return builder.build();
    }
}
