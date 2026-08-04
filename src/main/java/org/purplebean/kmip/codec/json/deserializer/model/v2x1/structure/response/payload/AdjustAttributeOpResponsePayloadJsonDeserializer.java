package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.AdjustAttributeOpResponsePayload;

/**
 * JSON deserializer for {@link AdjustAttributeOpResponsePayload}.
 */
public class AdjustAttributeOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AdjustAttributeOpResponsePayload,
        AdjustAttributeOpResponsePayload.AdjustAttributeOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link AdjustAttributeOpResponsePayloadJsonDeserializer}.
   */
  public AdjustAttributeOpResponsePayloadJsonDeserializer() {
    super(AdjustAttributeOpResponsePayload.kmipTag, AdjustAttributeOpResponsePayload.encodingType);
  }

  @Override
  protected AdjustAttributeOpResponsePayload.AdjustAttributeOpResponsePayloadBuilder
      createBuilder() {
    return AdjustAttributeOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      AdjustAttributeOpResponsePayload.AdjustAttributeOpResponsePayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AdjustAttributeOpResponsePayload build(
      AdjustAttributeOpResponsePayload.AdjustAttributeOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}