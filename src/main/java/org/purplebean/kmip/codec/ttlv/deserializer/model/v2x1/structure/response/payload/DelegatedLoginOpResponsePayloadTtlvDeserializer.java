package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.Ticket;
import org.purplebean.kmip.model.v2x1.structure.response.payload.DelegatedLoginOpResponsePayload;

/**
 * TTLV deserializer for {@link DelegatedLoginOpResponsePayload}.
 */
public class DelegatedLoginOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DelegatedLoginOpResponsePayload,
        DelegatedLoginOpResponsePayload.DelegatedLoginOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link DelegatedLoginOpResponsePayloadTtlvDeserializer}.
   */
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