package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.GetUsageAllocationOpResponsePayload;

/**
 * TTLV deserializer for {@link GetUsageAllocationOpResponsePayload}.
 */
public class GetUsageAllocationOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<GetUsageAllocationOpResponsePayload,
        GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link GetUsageAllocationOpResponsePayloadTtlvDeserializer}.
   */
  public GetUsageAllocationOpResponsePayloadTtlvDeserializer() {
    super(GetUsageAllocationOpResponsePayload.kmipTag,
        GetUsageAllocationOpResponsePayload.encodingType);
  }

  @Override
  protected GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder
      createBuilder() {
    return GetUsageAllocationOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder builder,
      byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
      builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetUsageAllocationOpResponsePayload build(
      GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
