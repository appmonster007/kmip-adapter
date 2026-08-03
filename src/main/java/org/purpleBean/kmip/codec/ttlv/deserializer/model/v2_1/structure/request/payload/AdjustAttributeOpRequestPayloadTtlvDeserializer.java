package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.enumeration.AdjustmentType;
import org.purpleBean.kmip.model.v2_1.structure.CurrentAttribute;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.AdjustAttributeOpRequestPayload;

public class AdjustAttributeOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AdjustAttributeOpRequestPayload,
        AdjustAttributeOpRequestPayload.AdjustAttributeOpRequestPayloadBuilder> {

  public AdjustAttributeOpRequestPayloadTtlvDeserializer() {
    super(AdjustAttributeOpRequestPayload.kmipTag, AdjustAttributeOpRequestPayload.encodingType);
  }

  @Override
  protected AdjustAttributeOpRequestPayload.AdjustAttributeOpRequestPayloadBuilder createBuilder() {
    return AdjustAttributeOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      AdjustAttributeOpRequestPayload.AdjustAttributeOpRequestPayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CURRENT_ATTRIBUTE ->
          builder.currentAttribute(mapper.readValue(p, CurrentAttribute.class));
      case KmipTag.Standard.ADJUSTMENT_TYPE ->
          builder.adjustmentType(mapper.readValue(p, AdjustmentType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AdjustAttributeOpRequestPayload build(
      AdjustAttributeOpRequestPayload.AdjustAttributeOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}