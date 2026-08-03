package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.RotateNameType;

/**
 * TTLV deserializer for {@link RotateNameType}.
 */
public class RotateNameTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RotateNameType, RotateNameType.RotateNameTypeBuilder> {

  /**
   * Constructs a new {@link RotateNameTypeTtlvDeserializer}.
   */
  public RotateNameTypeTtlvDeserializer() {
    super(RotateNameType.kmipTag, RotateNameType.encodingType);
  }

  @Override
  protected RotateNameType.RotateNameTypeBuilder createBuilder() {
    return RotateNameType.builder();
  }

  @Override
  protected void setValue(RotateNameType.RotateNameTypeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(RotateNameType.fromValue(value));
  }

  @Override
  protected RotateNameType build(RotateNameType.RotateNameTypeBuilder builder) {
    return builder.build();
  }
}
