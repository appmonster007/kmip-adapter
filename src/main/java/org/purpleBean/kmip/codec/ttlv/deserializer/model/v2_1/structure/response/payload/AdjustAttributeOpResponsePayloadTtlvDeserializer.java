package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.AdjustAttributeOpResponsePayload;

public class AdjustAttributeOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AdjustAttributeOpResponsePayload,
        AdjustAttributeOpResponsePayload.AdjustAttributeOpResponsePayloadBuilder> {

  public AdjustAttributeOpResponsePayloadTtlvDeserializer() {
    super(AdjustAttributeOpResponsePayload.kmipTag, AdjustAttributeOpResponsePayload.encodingType);
  }

  @Override
  protected AdjustAttributeOpResponsePayload.AdjustAttributeOpResponsePayloadBuilder createBuilder() {
    return AdjustAttributeOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      AdjustAttributeOpResponsePayload.AdjustAttributeOpResponsePayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.NEW_ATTRIBUTE ->
          builder.newAttribute(mapper.readValue(p, NewAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AdjustAttributeOpResponsePayload build(
      AdjustAttributeOpResponsePayload.AdjustAttributeOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}