package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.RandomIv;

/**
 * TTLV deserializer for {@link RandomIv}.
 */
public class RandomIvTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<RandomIv, RandomIv.RandomIvBuilder> {

  /**
   * Constructs a new {@link RandomIvTtlvDeserializer}.
   */
  public RandomIvTtlvDeserializer() {
    super(RandomIv.kmipTag, RandomIv.encodingType);
  }

  @Override
  protected RandomIv.RandomIvBuilder createBuilder() {
    return RandomIv.builder();
  }

  @Override
  protected void setValue(RandomIv.RandomIvBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Boolean.class));
  }

  @Override
  protected RandomIv build(RandomIv.RandomIvBuilder builder) {
    return builder.build();
  }
}
