package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.UsageLimits;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.v2x1.structure.Rights;
import org.purplebean.kmip.model.v2x1.structure.request.payload.DelegatedLoginOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.RequestCount;

/**
 * TTLV deserializer for {@link DelegatedLoginOpRequestPayload}.
 */
public class DelegatedLoginOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DelegatedLoginOpRequestPayload,
        DelegatedLoginOpRequestPayload.DelegatedLoginOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link DelegatedLoginOpRequestPayloadTtlvDeserializer}.
   */
  public DelegatedLoginOpRequestPayloadTtlvDeserializer() {
    super(DelegatedLoginOpRequestPayload.kmipTag, DelegatedLoginOpRequestPayload.encodingType);
  }

  @Override
  protected DelegatedLoginOpRequestPayload.DelegatedLoginOpRequestPayloadBuilder createBuilder() {
    return DelegatedLoginOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      DelegatedLoginOpRequestPayload.DelegatedLoginOpRequestPayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.LEASE_TIME -> builder.leaseTime(mapper.readValue(p, LeaseTime.class));
      case KmipTag.Standard.REQUEST_COUNT ->
          builder.requestCount(mapper.readValue(p, RequestCount.class));
      case KmipTag.Standard.USAGE_LIMITS ->
          builder.usageLimits(mapper.readValue(p, UsageLimits.class));
      case KmipTag.Standard.RIGHTS -> builder.rights(mapper.readValue(p, Rights.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DelegatedLoginOpRequestPayload build(
      DelegatedLoginOpRequestPayload.DelegatedLoginOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
