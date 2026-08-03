package org.purpleBean.kmip.codec.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class BooleanJsonSerializer extends JsonSerializer<Boolean> {

  @Override
  public void serialize(Boolean value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    gen.writeString(value.toString());
  }
}