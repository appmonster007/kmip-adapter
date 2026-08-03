package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DataLength;

public class DataLengthTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<DataLength, DataLength.DataLengthBuilder> {

  public DataLengthTtlvDeserializer() {
    super(DataLength.kmipTag, DataLength.encodingType);
  }

  @Override
  protected DataLength.DataLengthBuilder createBuilder() {
    return DataLength.builder();
  }

  @Override
  protected void setValue(DataLength.DataLengthBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected DataLength build(DataLength.DataLengthBuilder builder) {
    return builder.build();
  }
}
