package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.enumeration.AdjustmentType;
import org.purplebean.kmip.model.v2x1.structure.AttributeReference;
import org.purplebean.kmip.model.v2x1.structure.request.payload.AdjustAttributeOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.AdjustmentValue;

/**
 * TTLV deserializer for {@link AdjustAttributeOpRequestPayload}.
 */
public class AdjustAttributeOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AdjustAttributeOpRequestPayload,
        AdjustAttributeOpRequestPayload.AdjustAttributeOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link AdjustAttributeOpRequestPayloadTtlvDeserializer}.
   */
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
      case KmipTag.Standard.ATTRIBUTE_REFERENCE ->
          builder.attributeReference(mapper.readValue(p, AttributeReference.class));
      case KmipTag.Standard.ADJUSTMENT_TYPE ->
          builder.adjustmentType(mapper.readValue(p, AdjustmentType.class));
      case KmipTag.Standard.ADJUSTMENT_VALUE ->
          builder.adjustmentValue(mapper.readValue(p, AdjustmentValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AdjustAttributeOpRequestPayload build(
      AdjustAttributeOpRequestPayload.AdjustAttributeOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}