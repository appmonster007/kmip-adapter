package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.AttributeReference;
import org.purplebean.kmip.model.v2x1.structure.request.payload.DeleteAttributeOpRequestPayload;

/**
 * JSON deserializer for {@link DeleteAttributeOpRequestPayload}.
 */
public class DeleteAttributeOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DeleteAttributeOpRequestPayload,
        DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link DeleteAttributeOpRequestPayloadJsonDeserializer}.
   */
  public DeleteAttributeOpRequestPayloadJsonDeserializer() {
    super(DeleteAttributeOpRequestPayload.kmipTag, DeleteAttributeOpRequestPayload.encodingType);
  }

  @Override
  protected DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder createBuilder() {
    return DeleteAttributeOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder builder,
      String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTE_REFERENCE ->
          builder.attributeReference(ctxt.readValue(p, AttributeReference.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DeleteAttributeOpRequestPayload build(
      DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
