package org.purplebean.kmip.codec.json.deserializer.model.v3x0.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.response.payload.DeactivateOpResponsePayload;

/**
 * JSON deserializer for {@link DeactivateOpResponsePayload}.
 */
public class DeactivateOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DeactivateOpResponsePayload,
        DeactivateOpResponsePayload.DeactivateOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link DeactivateOpResponsePayloadJsonDeserializer}.
   */
  public DeactivateOpResponsePayloadJsonDeserializer() {
    super(DeactivateOpResponsePayload.kmipTag, DeactivateOpResponsePayload.encodingType);
  }

  @Override
  protected DeactivateOpResponsePayload.DeactivateOpResponsePayloadBuilder createBuilder() {
    return DeactivateOpResponsePayload.builder();
  }

  @Override
  protected void setValue(DeactivateOpResponsePayload.DeactivateOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DeactivateOpResponsePayload build(
      DeactivateOpResponsePayload.DeactivateOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}