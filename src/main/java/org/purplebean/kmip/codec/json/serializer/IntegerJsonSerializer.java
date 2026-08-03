package org.purplebean.kmip.codec.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * JSON serializer for {@link Integer}.
 */
public class IntegerJsonSerializer extends JsonSerializer<Integer> {

  @Override
  public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    gen.writeNumber(value);
  }
}