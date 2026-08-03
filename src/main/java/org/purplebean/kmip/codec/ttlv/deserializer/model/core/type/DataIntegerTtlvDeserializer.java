package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.DataInteger;

/**
 * TTLV deserializer for {@link DataInteger}.
 */
public class DataIntegerTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<DataInteger, DataInteger.DataIntegerBuilder> {

  /**
   * Constructs a new {@link DataIntegerTtlvDeserializer}.
   */
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
