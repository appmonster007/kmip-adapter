package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.AttributeIndex;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.DeleteAttributeOpRequestPayload;

public class DeleteAttributeOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DeleteAttributeOpRequestPayload,
        DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder> {

  public DeleteAttributeOpRequestPayloadJsonDeserializer() {
    super(DeleteAttributeOpRequestPayload.kmipTag, DeleteAttributeOpRequestPayload.encodingType);
  }

  @Override
  protected DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder createBuilder() {
    return DeleteAttributeOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTE_NAME ->
          builder.attributeName(ctxt.readValue(p, AttributeName.class));
      case KmipTag.Standard.ATTRIBUTE_INDEX ->
          builder.attributeIndex(ctxt.readValue(p, AttributeIndex.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DeleteAttributeOpRequestPayload build(
      DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
