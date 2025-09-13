package org.purpleBean.kmip.codec.json.serializer.kmip;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ProtocolVersionMinorJsonSerializer extends KmipDataTypeJsonSerializer<ProtocolVersion.ProtocolVersionMinor> {

    @Override
    public void serialize(ProtocolVersion.ProtocolVersionMinor minor, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!minor.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(minor.getKmipTag());
        jsonGenerator.writeStringField("type", minor.getEncodingType().getDescription());
        jsonGenerator.writeNumberField("value", minor.getValue());
        jsonGenerator.writeEndObject();
    }
}


