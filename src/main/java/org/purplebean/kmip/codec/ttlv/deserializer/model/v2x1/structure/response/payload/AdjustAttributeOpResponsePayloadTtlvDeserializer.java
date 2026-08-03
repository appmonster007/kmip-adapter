package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.NewAttribute;
import org.purplebean.kmip.model.v2x1.structure.response.payload.AdjustAttributeOpResponsePayload;

/**
 * TTLV deserializer for {@link AdjustAttributeOpResponsePayload}.
 */
public class AdjustAttributeOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AdjustAttributeOpResponsePayload,
        AdjustAttributeOpResponsePayload.AdjustAttributeOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link AdjustAttributeOpResponsePayloadTtlvDeserializer}.
   */
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