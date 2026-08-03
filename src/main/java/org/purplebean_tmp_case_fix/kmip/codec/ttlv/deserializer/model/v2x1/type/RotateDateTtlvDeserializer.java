package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.RotateDate;

public class RotateDateTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<RotateDate, RotateDate.RotateDateBuilder> {

  public RotateDateTtlvDeserializer() {
    super(RotateDate.kmipTag, RotateDate.encodingType);
  }

  @Override
  protected RotateDate.RotateDateBuilder createBuilder() {
    return RotateDate.builder();
  }

  @Override
  protected void setValue(RotateDate.RotateDateBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected RotateDate build(RotateDate.RotateDateBuilder builder) {
    return builder.build();
  }
}