package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.RotateLatest;

public class RotateLatestTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<RotateLatest, RotateLatest.RotateLatestBuilder> {

  public RotateLatestTtlvDeserializer() {
    super(RotateLatest.kmipTag, RotateLatest.encodingType);
  }

  @Override
  protected RotateLatest.RotateLatestBuilder createBuilder() {
    return RotateLatest.builder();
  }

  @Override
  protected void setValue(RotateLatest.RotateLatestBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected RotateLatest build(RotateLatest.RotateLatestBuilder builder) {
    return builder.build();
  }
}