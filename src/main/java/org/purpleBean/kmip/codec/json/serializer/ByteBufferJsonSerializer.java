package org.purpleBean.kmip.codec.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HexFormat;

public class ByteBufferJsonSerializer extends JsonSerializer<ByteBuffer> {
    @Override
    public void serialize(ByteBuffer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        HexFormat hexFormat = HexFormat.of();
        // Convert byte[] to hex string or any other string format instead of base64
        String hexString = hexFormat.formatHex(value.array());

        // Output as JSON string
        gen.writeString(hexString);
    }
}