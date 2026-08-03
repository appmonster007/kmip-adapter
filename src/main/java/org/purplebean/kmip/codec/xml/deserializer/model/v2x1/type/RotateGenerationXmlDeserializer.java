package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.RotateGeneration;

/**
 * XML deserializer for {@link RotateGeneration}.
 */
public class RotateGenerationXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RotateGeneration,
        RotateGeneration.RotateGenerationBuilder> {

  /**
   * Constructs a new {@link RotateGenerationXmlDeserializer}.
   */
  public RotateGenerationXmlDeserializer() {
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