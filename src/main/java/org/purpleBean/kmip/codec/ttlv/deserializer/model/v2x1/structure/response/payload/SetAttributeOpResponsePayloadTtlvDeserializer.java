package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SetAttributeOpResponsePayload;

public class SetAttributeOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SetAttributeOpResponsePayload,
        SetAttributeOpResponsePayload.SetAttributeOpResponsePayloadBuilder> {

  public SetAttributeOpResponsePayloadTtlvDeserializer() {
    super(SetAttributeOpResponsePayload.kmipTag, SetAttributeOpResponsePayload.encodingType);
  }

  @Override
  protected SetAttributeOpResponsePayload.SetAttributeOpResponsePayloadBuilder createBuilder() {
    return SetAttributeOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      SetAttributeOpResponsePayload.SetAttributeOpResponsePayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SetAttributeOpResponsePayload build(
      SetAttributeOpResponsePayload.SetAttributeOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}