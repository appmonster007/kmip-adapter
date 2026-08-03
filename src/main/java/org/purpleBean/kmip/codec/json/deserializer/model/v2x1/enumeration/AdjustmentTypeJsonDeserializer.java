package org.purplebean.kmip.codec.json.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.AdjustmentType;

public class AdjustmentTypeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AdjustmentType, AdjustmentType.AdjustmentTypeBuilder> {

  public AdjustmentTypeJsonDeserializer() {
    super(AdjustmentType.kmipTag, AdjustmentType.encodingType);
  }

  @Override
  protected AdjustmentType.AdjustmentTypeBuilder createBuilder() {
    return AdjustmentType.builder();
  }

  @Override
  protected void setValue(AdjustmentType.AdjustmentTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(AdjustmentType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected AdjustmentType build(AdjustmentType.AdjustmentTypeBuilder builder) {
    return builder.build();
  }
}