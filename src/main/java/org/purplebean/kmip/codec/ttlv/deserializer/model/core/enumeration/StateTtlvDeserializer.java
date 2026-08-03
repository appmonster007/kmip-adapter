package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.State;

/**
 * TTLV deserializer for {@link State}.
 */
public class StateTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<State, State.StateBuilder> {

  /**
   * Constructs a new {@link StateTtlvDeserializer}.
   */
  public StateTtlvDeserializer() {
    super(State.kmipTag, State.encodingType);
  }

  @Override
  protected State.StateBuilder createBuilder() {
    return State.builder();
  }

  @Override
  protected void setValue(State.StateBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(State.fromValue(value));
  }

  @Override
  protected State build(State.StateBuilder builder) {
    return builder.build();
  }
}
