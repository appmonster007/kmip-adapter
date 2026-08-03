package org.purplebean.kmip.codec.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

/**
 * JSON deserializer for {@link Boolean}.
 */
public class BooleanJsonDeserializer extends JsonDeserializer<Boolean> {

  @Override
  public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonNode node = p
        .getCodec()
        .readTree(p);
    return Boolean.parseBoolean(node.asText());
  }
}