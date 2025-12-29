package org.purpleBean.kmip.codec.xml.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HexFormat;

public class ByteBufferXmlDeserializer extends JsonDeserializer<ByteBuffer> {
    @Override
    public ByteBuffer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // For XML, we need to handle the text content properly
        String hexString = p.getCodec().readValue(p, String.class);

        // Parse hex string to bytes
        HexFormat hexFormat = HexFormat.of();
        byte[] data = hexFormat.parseHex(hexString);

        return ByteBuffer.wrap(data);
    }
}