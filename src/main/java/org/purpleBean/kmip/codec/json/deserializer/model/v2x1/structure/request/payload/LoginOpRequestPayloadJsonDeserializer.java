package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.LoginOpRequestPayload;
import org.purpleBean.kmip.model.v2x1.type.RequestCount;

public class LoginOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<LoginOpRequestPayload,
        LoginOpRequestPayload.LoginOpRequestPayloadBuilder> {

  public LoginOpRequestPayloadJsonDeserializer() {
    super(LoginOpRequestPayload.kmipTag, LoginOpRequestPayload.encodingType);
  }

  @Override
  protected LoginOpRequestPayload.LoginOpRequestPayloadBuilder createBuilder() {
    return LoginOpRequestPayload.builder();
  }

  @Override
  protected void setValue(LoginOpRequestPayload.LoginOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.LEASE_TIME -> builder.leaseTime(ctxt.readValue(p, LeaseTime.class));
      case KmipTag.Standard.REQUEST_COUNT ->
          builder.requestCount(ctxt.readValue(p, RequestCount.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected LoginOpRequestPayload build(
      LoginOpRequestPayload.LoginOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}