package org.purplebean.kmip.codec.xml.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * XML serializer for {@link Boolean}.
 */
public class BooleanXmlSerializer extends JsonSerializer<Boolean> {

  @Override
  public void serialize(Boolean value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    gen.writeString(Boolean.toString(value));
  }
}