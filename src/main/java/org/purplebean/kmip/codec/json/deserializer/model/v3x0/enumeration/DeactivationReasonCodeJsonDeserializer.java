package org.purplebean.kmip.codec.json.deserializer.model.v3x0.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v3x0.enumeration.DeactivationReasonCode;

/**
 * JSON deserializer for {@link DeactivationReasonCode}.
 */
public class DeactivationReasonCodeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DeactivationReasonCode,
        DeactivationReasonCode.DeactivationReasonCodeBuilder> {

  /**
   * Constructs a new {@link DeactivationReasonCodeJsonDeserializer}.
   */
  public DeactivationReasonCodeJsonDeserializer() {
    super(DeactivationReasonCode.kmipTag, DeactivationReasonCode.encodingType);
  }

  @Override
  protected DeactivationReasonCode.DeactivationReasonCodeBuilder createBuilder() {
    return DeactivationReasonCode.builder();
  }

  @Override
  protected void setValue(DeactivationReasonCode.DeactivationReasonCodeBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(DeactivationReasonCode.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected DeactivationReasonCode build(
      DeactivationReasonCode.DeactivationReasonCodeBuilder builder) {
    return builder.build();
  }
}
