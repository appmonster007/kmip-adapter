package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.RotateAutomatic;

/**
 * JSON deserializer for {@link RotateAutomatic}.
 */
public class RotateAutomaticJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RotateAutomatic, RotateAutomatic.RotateAutomaticBuilder> {

  /**
   * Constructs a new {@link RotateAutomaticJsonDeserializer}.
   */
  public RotateAutomaticJsonDeserializer() {
    super(RotateAutomatic.kmipTag, RotateAutomatic.encodingType);
  }

  @Override
  protected RotateAutomatic.RotateAutomaticBuilder createBuilder() {
    return RotateAutomatic.builder();
  }

  @Override
  protected void setValue(RotateAutomatic.RotateAutomaticBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected RotateAutomatic build(RotateAutomatic.RotateAutomaticBuilder builder) {
    return builder.build();
  }
}