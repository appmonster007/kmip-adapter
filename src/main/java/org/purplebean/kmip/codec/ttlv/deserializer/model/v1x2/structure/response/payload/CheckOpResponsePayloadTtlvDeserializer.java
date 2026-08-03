package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.CryptographicUsageMask;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;
import org.purplebean.kmip.model.v1x2.structure.response.payload.CheckOpResponsePayload;

/**
 * TTLV deserializer for {@link CheckOpResponsePayload}.
 */
public class CheckOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CheckOpResponsePayload,
        CheckOpResponsePayload.CheckOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link CheckOpResponsePayloadTtlvDeserializer}.
   */
  public CheckOpResponsePayloadTtlvDeserializer() {
    super(CheckOpResponsePayload.kmipTag, CheckOpResponsePayload.encodingType);
  }

  @Override
  protected CheckOpResponsePayload.CheckOpResponsePayloadBuilder createBuilder() {
    return CheckOpResponsePayload.builder();
  }

  @Override
  protected void setValue(CheckOpResponsePayload.CheckOpResponsePayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.USAGE_LIMITS_COUNT ->
          builder.usageLimitsCount(mapper.readValue(p, UsageLimitsCount.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_USAGE_MASK ->
          builder.cryptographicUsageMask(mapper.readValue(p, CryptographicUsageMask.class));
      case KmipTag.Standard.LEASE_TIME -> builder.leaseTime(mapper.readValue(p, LeaseTime.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CheckOpResponsePayload build(
      CheckOpResponsePayload.CheckOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
