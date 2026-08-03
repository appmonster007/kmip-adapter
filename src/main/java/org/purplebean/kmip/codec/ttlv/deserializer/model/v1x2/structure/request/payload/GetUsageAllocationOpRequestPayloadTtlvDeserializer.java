package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;
import org.purplebean.kmip.model.v1x2.structure.request.payload.GetUsageAllocationOpRequestPayload;

/**
 * TTLV deserializer for {@link GetUsageAllocationOpRequestPayload}.
 */
public class GetUsageAllocationOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<GetUsageAllocationOpRequestPayload,
        GetUsageAllocationOpRequestPayload.GetUsageAllocationOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link GetUsageAllocationOpRequestPayloadTtlvDeserializer}.
   */
  public GetUsageAllocationOpRequestPayloadTtlvDeserializer() {
    super(GetUsageAllocationOpRequestPayload.kmipTag,
        GetUsageAllocationOpRequestPayload.encodingType);
  }

  @Override
  protected GetUsageAllocationOpRequestPayload.GetUsageAllocationOpRequestPayloadBuilder createBuilder() {
    return GetUsageAllocationOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      GetUsageAllocationOpRequestPayload.GetUsageAllocationOpRequestPayloadBuilder builder,
      byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.USAGE_LIMITS_COUNT ->
          builder.usageLimitsCount(mapper.readValue(p, UsageLimitsCount.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetUsageAllocationOpRequestPayload build(
      GetUsageAllocationOpRequestPayload.GetUsageAllocationOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
