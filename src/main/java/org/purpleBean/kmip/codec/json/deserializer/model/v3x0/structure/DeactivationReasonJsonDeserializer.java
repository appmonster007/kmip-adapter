package org.purplebean.kmip.codec.json.deserializer.model.v3x0.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v3x0.enumeration.DeactivationReasonCode;
import org.purplebean.kmip.model.v3x0.structure.DeactivationReason;
import org.purplebean.kmip.model.v3x0.type.DeactivationMessage;

public class DeactivationReasonJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DeactivationReason,
        DeactivationReason.DeactivationReasonBuilder> {

  public DeactivationReasonJsonDeserializer() {
    super(DeactivationReason.kmipTag, DeactivationReason.encodingType);
  }

  @Override
  protected DeactivationReason.DeactivationReasonBuilder createBuilder() {
    return DeactivationReason.builder();
  }

  @Override
  protected void setValue(DeactivationReason.DeactivationReasonBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    if (nodeTag == DeactivationReasonCode.kmipTag.getValue()) {
      builder.deactivationReasonCode(ctxt.readValue(p, DeactivationReasonCode.class));
    } else if (nodeTag == DeactivationMessage.kmipTag.getValue()) {
      builder.deactivationMessage(ctxt.readValue(p, DeactivationMessage.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DeactivationReason build(DeactivationReason.DeactivationReasonBuilder builder) {
    return builder.build();
  }
}