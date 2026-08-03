package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.TicketType;

/**
 * TTLV deserializer for {@link TicketType}.
 */
public class TicketTypeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<TicketType, TicketType.TicketTypeBuilder> {

  /**
   * Constructs a new {@link TicketTypeTtlvDeserializer}.
   */
  public TicketTypeTtlvDeserializer() {
    super(TicketType.kmipTag, TicketType.encodingType);
  }

  @Override
  protected TicketType.TicketTypeBuilder createBuilder() {
    return TicketType.builder();
  }

  @Override
  protected void setValue(TicketType.TicketTypeBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(TicketType.fromValue(value));
  }

  @Override
  protected TicketType build(TicketType.TicketTypeBuilder builder) {
    return builder.build();
  }
}
