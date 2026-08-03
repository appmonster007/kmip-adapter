package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.v2x1.structure.request.payload.LoginOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.RequestCount;

/**
 * TTLV deserializer for {@link LoginOpRequestPayload}.
 */
public class LoginOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<LoginOpRequestPayload,
        LoginOpRequestPayload.LoginOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link LoginOpRequestPayloadTtlvDeserializer}.
   */
  public LoginOpRequestPayloadTtlvDeserializer() {
    super(LoginOpRequestPayload.kmipTag, LoginOpRequestPayload.encodingType);
  }

  @Override
  protected LoginOpRequestPayload.LoginOpRequestPayloadBuilder createBuilder() {
    return LoginOpRequestPayload.builder();
  }

  @Override
  protected void setValue(LoginOpRequestPayload.LoginOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.LEASE_TIME -> builder.leaseTime(mapper.readValue(p, LeaseTime.class));
      case KmipTag.Standard.REQUEST_COUNT ->
          builder.requestCount(mapper.readValue(p, RequestCount.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected LoginOpRequestPayload build(
      LoginOpRequestPayload.LoginOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}