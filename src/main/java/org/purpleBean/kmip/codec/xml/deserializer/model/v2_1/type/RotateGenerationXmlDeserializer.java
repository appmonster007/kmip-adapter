package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.RotateGeneration;

public class RotateGenerationXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RotateGeneration,
        RotateGeneration.RotateGenerationBuilder> {

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