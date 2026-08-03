package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.NistKeyType;

/**
 * TTLV deserializer for {@link NistKeyType}.
 */
public class NistKeyTypeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<NistKeyType, NistKeyType.NistKeyTypeBuilder> {

  /**
   * Constructs a new {@link NistKeyTypeTtlvDeserializer}.
   */
  public NistKeyTypeTtlvDeserializer() {
    super(NistKeyType.kmipTag, NistKeyType.encodingType);
  }

  @Override
  protected NistKeyType.NistKeyTypeBuilder createBuilder() {
    return NistKeyType.builder();
  }

  @Override
  protected void setValue(NistKeyType.NistKeyTypeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(NistKeyType.fromValue(value));
  }

  @Override
  protected NistKeyType build(NistKeyType.NistKeyTypeBuilder builder) {
    return builder.build();
  }
}
