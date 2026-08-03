package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.InitialCounterValue;

/**
 * TTLV deserializer for {@link InitialCounterValue}.
 */
public class InitialCounterValueTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<InitialCounterValue,
        InitialCounterValue.InitialCounterValueBuilder> {

  /**
   * Constructs a new {@link InitialCounterValueTtlvDeserializer}.
   */
  public InitialCounterValueTtlvDeserializer() {
    super(InitialCounterValue.kmipTag, InitialCounterValue.encodingType);
  }

  @Override
  protected InitialCounterValue.InitialCounterValueBuilder createBuilder() {
    return InitialCounterValue.builder();
  }

  @Override
  protected void setValue(InitialCounterValue.InitialCounterValueBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected InitialCounterValue build(InitialCounterValue.InitialCounterValueBuilder builder) {
    return builder.build();
  }
}
