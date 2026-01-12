package org.purpleBean.kmip.codec.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HexFormat;

public class ByteStringJsonSerializer extends JsonSerializer<ByteBuffer> {

    @Override
    public void serialize(ByteBuffer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(HexFormat.of().formatHex(value.array()));
    }
}