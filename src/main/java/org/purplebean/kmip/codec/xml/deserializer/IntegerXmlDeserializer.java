package org.purplebean.kmip.codec.xml.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/**
 * XML deserializer for {@link Integer}.
 */
public class IntegerXmlDeserializer extends JsonDeserializer<Integer> {

  @Override
  public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return Integer.parseInt(p.getText());
  }
}