package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.Ticket;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LoginOpResponsePayload;

/**
 * TTLV deserializer for {@link LoginOpResponsePayload}.
 */
public class LoginOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<LoginOpResponsePayload,
        LoginOpResponsePayload.LoginOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link LoginOpResponsePayloadTtlvDeserializer}.
   */
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