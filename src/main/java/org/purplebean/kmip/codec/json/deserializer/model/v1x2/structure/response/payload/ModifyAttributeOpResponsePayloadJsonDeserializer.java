package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.ModifyAttributeOpResponsePayload;

/**
 * JSON deserializer for {@link ModifyAttributeOpResponsePayload}.
 */
public class ModifyAttributeOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ModifyAttributeOpResponsePayload,
        ModifyAttributeOpResponsePayload.ModifyAttributeOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link ModifyAttributeOpResponsePayloadJsonDeserializer}.
   */
  public ModifyAttributeOpResponsePayloadJsonDeserializer() {
    super(ModifyAttributeOpResponsePayload.kmipTag, ModifyAttributeOpResponsePayload.encodingType);
  }

  @Override
  protected ModifyAttributeOpResponsePayload.ModifyAttributeOpResponsePayloadBuilder createBuilder() {
    return ModifyAttributeOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      ModifyAttributeOpResponsePayload.ModifyAttributeOpResponsePayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ModifyAttributeOpResponsePayload build(
      ModifyAttributeOpResponsePayload.ModifyAttributeOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
