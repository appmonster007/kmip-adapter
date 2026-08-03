package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DataInteger;

public class DataIntegerTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<DataInteger, DataInteger.DataIntegerBuilder> {

  public DataIntegerTtlvDeserializer() {
    super(DataInteger.kmipTag, DataInteger.encodingType);
  }

  @Override
  protected DataInteger.DataIntegerBuilder createBuilder() {
    return DataInteger.builder();
  }

  @Override
  protected void setValue(DataInteger.DataIntegerBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected DataInteger build(DataInteger.DataIntegerBuilder builder) {
    return builder.build();
  }
}
