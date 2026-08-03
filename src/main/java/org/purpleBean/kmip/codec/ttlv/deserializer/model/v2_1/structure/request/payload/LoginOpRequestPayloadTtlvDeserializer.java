package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LoginOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.type.RequestCount;

public class LoginOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<LoginOpRequestPayload,
        LoginOpRequestPayload.LoginOpRequestPayloadBuilder> {

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