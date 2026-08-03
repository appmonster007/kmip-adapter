package org.purplebean.kmip.codec.xml.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HexFormat;

public class ByteStringXmlDeserializer extends JsonDeserializer<ByteBuffer> {

  @Override
  public ByteBuffer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return ByteBuffer.wrap(HexFormat
        .of()
        .parseHex(p.getText()));
  }
}