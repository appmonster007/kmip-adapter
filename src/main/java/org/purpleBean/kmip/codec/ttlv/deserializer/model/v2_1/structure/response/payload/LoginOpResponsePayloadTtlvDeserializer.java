package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.LoginOpResponsePayload;

public class LoginOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<LoginOpResponsePayload,
        LoginOpResponsePayload.LoginOpResponsePayloadBuilder> {

  public LoginOpResponsePayloadTtlvDeserializer() {
    super(LoginOpResponsePayload.kmipTag, LoginOpResponsePayload.encodingType);
  }

  @Override
  protected LoginOpResponsePayload.LoginOpResponsePayloadBuilder createBuilder() {
    return LoginOpResponsePayload.builder();
  }

  @Override
  protected void setValue(LoginOpResponsePayload.LoginOpResponsePayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.TICKET -> builder.ticket(mapper.readValue(p, Ticket.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected LoginOpResponsePayload build(
      LoginOpResponsePayload.LoginOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}