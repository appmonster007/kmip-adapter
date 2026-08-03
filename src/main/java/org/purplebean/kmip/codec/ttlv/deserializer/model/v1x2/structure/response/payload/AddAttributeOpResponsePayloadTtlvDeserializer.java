package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.AddAttributeOpResponsePayload;

public class AddAttributeOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AddAttributeOpResponsePayload,
        AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder> {

  public AddAttributeOpResponsePayloadTtlvDeserializer() {
    super(AddAttributeOpResponsePayload.kmipTag, AddAttributeOpResponsePayload.encodingType);
  }

  @Override
  protected AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder createBuilder() {
    return AddAttributeOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder builder, byte[] tag,
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
  protected AddAttributeOpResponsePayload build(
      AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
