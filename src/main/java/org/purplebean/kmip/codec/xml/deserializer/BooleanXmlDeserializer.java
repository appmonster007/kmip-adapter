package org.purplebean.kmip.codec.xml.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/**
 * XML deserializer for {@link Boolean}.
 */
public class BooleanXmlDeserializer extends JsonDeserializer<Boolean> {

  @Override
  public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return Boolean.parseBoolean(p.getText());
  }
}