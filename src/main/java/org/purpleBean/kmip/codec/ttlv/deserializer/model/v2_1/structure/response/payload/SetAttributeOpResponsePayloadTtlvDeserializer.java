package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetAttributeOpResponsePayload;

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