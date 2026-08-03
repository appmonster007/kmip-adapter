package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.AddAttributeOpResponsePayload;

/**
 * JSON deserializer for {@link AddAttributeOpResponsePayload}.
 */
public class AddAttributeOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AddAttributeOpResponsePayload,
        AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link AddAttributeOpResponsePayloadJsonDeserializer}.
   */
  public AddAttributeOpResponsePayloadJsonDeserializer() {
    super(AddAttributeOpResponsePayload.kmipTag, AddAttributeOpResponsePayload.encodingType);
  }

  @Override
  protected AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder createBuilder() {
    return AddAttributeOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AddAttributeOpResponsePayload build(
      AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}