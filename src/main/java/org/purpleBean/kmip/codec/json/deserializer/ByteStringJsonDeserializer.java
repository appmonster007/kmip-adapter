package org.purpleBean.kmip.codec.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HexFormat;

public class ByteStringJsonDeserializer extends JsonDeserializer<ByteBuffer> {

  @Override
  public ByteBuffer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonNode node = p
        .getCodec()
        .readTree(p);
    return ByteBuffer.wrap(HexFormat
        .of()
        .parseHex(node.asText()));
  }
}