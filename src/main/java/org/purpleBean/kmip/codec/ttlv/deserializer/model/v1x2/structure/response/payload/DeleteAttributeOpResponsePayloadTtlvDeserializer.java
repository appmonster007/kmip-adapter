package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.DeleteAttributeOpResponsePayload;

public class DeleteAttributeOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DeleteAttributeOpResponsePayload,
        DeleteAttributeOpResponsePayload.DeleteAttributeOpResponsePayloadBuilder> {

  public DeleteAttributeOpResponsePayloadTtlvDeserializer() {
    super(DeleteAttributeOpResponsePayload.kmipTag, DeleteAttributeOpResponsePayload.encodingType);
  }

  @Override
  protected DeleteAttributeOpResponsePayload.DeleteAttributeOpResponsePayloadBuilder createBuilder() {
    return DeleteAttributeOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      DeleteAttributeOpResponsePayload.DeleteAttributeOpResponsePayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DeleteAttributeOpResponsePayload build(
      DeleteAttributeOpResponsePayload.DeleteAttributeOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
