package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.RotateOffset;

public class RotateOffsetTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<RotateOffset, RotateOffset.RotateOffsetBuilder> {

  public RotateOffsetTtlvDeserializer() {
    super(RotateOffset.kmipTag, RotateOffset.encodingType);
  }

  @Override
  protected RotateOffset.RotateOffsetBuilder createBuilder() {
    return RotateOffset.builder();
  }

  @Override
  protected void setValue(RotateOffset.RotateOffsetBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Long.class));
  }

  @Override
  protected RotateOffset build(RotateOffset.RotateOffsetBuilder builder) {
    return builder.build();
  }
}