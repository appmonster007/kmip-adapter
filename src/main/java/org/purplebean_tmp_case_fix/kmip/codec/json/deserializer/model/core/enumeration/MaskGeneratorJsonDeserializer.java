package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.MaskGenerator;

public class MaskGeneratorJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<MaskGenerator, MaskGenerator.MaskGeneratorBuilder> {

  public MaskGeneratorJsonDeserializer() {
    super(MaskGenerator.kmipTag, MaskGenerator.encodingType);
  }

  @Override
  protected MaskGenerator.MaskGeneratorBuilder createBuilder() {
    return MaskGenerator.builder();
  }

  @Override
  protected void setValue(MaskGenerator.MaskGeneratorBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(MaskGenerator.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected MaskGenerator build(MaskGenerator.MaskGeneratorBuilder builder) {
    return builder.build();
  }
}
