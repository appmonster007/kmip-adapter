package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.DelegatedLoginOpResponsePayload;

public class DelegatedLoginOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DelegatedLoginOpResponsePayload,
        DelegatedLoginOpResponsePayload.DelegatedLoginOpResponsePayloadBuilder> {

  public DelegatedLoginOpResponsePayloadTtlvDeserializer() {
    super(DelegatedLoginOpResponsePayload.kmipTag, DelegatedLoginOpResponsePayload.encodingType);
  }

  @Override
  protected DelegatedLoginOpResponsePayload.DelegatedLoginOpResponsePayloadBuilder createBuilder() {
    return DelegatedLoginOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      DelegatedLoginOpResponsePayload.DelegatedLoginOpResponsePayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.TICKET -> builder.ticket(mapper.readValue(p, Ticket.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DelegatedLoginOpResponsePayload build(
      DelegatedLoginOpResponsePayload.DelegatedLoginOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}