package org.purpleBean.kmip.codec.json.serializer.kmip;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ProtocolVersionMajorJsonSerializer extends KmipDataTypeJsonSerializer<ProtocolVersion.ProtocolVersionMajor> {

    @Override
    public void serialize(ProtocolVersion.ProtocolVersionMajor major, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!major.isSupported()) {
            throw new UnsupportedEncodingException();
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(major.getKmipTag());
        jsonGenerator.writeStringField("type", major.getEncodingType().getDescription());
        jsonGenerator.writeNumberField("value", major.getValue());
        jsonGenerator.writeEndObject();
    }
}


