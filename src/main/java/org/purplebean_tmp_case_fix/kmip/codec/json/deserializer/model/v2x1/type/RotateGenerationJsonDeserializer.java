package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.RotateGeneration;

public class RotateGenerationJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RotateGeneration,
        RotateGeneration.RotateGenerationBuilder> {

  public RotateGenerationJsonDeserializer() {
    super(RotateGeneration.kmipTag, RotateGeneration.encodingType);
  }

  @Override
  protected RotateGeneration.RotateGenerationBuilder createBuilder() {
    return RotateGeneration.builder();
  }

  @Override
  protected void setValue(RotateGeneration.RotateGenerationBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected RotateGeneration build(RotateGeneration.RotateGenerationBuilder builder) {
    return builder.build();
  }
}