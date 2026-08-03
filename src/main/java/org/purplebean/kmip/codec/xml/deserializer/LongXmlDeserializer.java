package org.purplebean.kmip.codec.xml.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * XML deserializer for {@link Long}.
 */
public class LongXmlDeserializer extends JsonDeserializer<Long> {

  @Override
  public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return Long.parseLong(p.getText());
  }
}