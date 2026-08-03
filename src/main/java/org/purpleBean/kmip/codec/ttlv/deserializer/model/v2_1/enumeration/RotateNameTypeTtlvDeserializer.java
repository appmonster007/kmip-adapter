package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.enumeration.RotateNameType;

public class RotateNameTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RotateNameType, RotateNameType.RotateNameTypeBuilder> {

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
