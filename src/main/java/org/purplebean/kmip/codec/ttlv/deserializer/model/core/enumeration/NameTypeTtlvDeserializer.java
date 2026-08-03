package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.NameType;

/**
 * TTLV deserializer for {@link NameType}.
 */
public class NameTypeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<NameType, NameType.NameTypeBuilder> {

  /**
   * Constructs a new {@link NameTypeTtlvDeserializer}.
   */
  public NameTypeTtlvDeserializer() {
    super(NameType.kmipTag, NameType.encodingType);
  }

  @Override
  protected NameType.NameTypeBuilder createBuilder() {
    return NameType.builder();
  }

  @Override
  protected void setValue(NameType.NameTypeBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(NameType.fromValue(value));
  }

  @Override
  protected NameType build(NameType.NameTypeBuilder builder) {
    return builder.build();
  }
}
