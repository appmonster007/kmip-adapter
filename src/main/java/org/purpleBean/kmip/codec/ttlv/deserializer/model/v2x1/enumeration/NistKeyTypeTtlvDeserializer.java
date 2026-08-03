package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.enumeration.NistKeyType;

public class NistKeyTypeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<NistKeyType, NistKeyType.NistKeyTypeBuilder> {

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
