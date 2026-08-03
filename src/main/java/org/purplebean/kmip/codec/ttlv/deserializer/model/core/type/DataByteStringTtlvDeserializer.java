package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.DataByteString;

/**
 * TTLV deserializer for {@link DataByteString}.
 */
public class DataByteStringTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DataByteString, DataByteString.DataByteStringBuilder> {

  /**
   * Constructs a new {@link DataByteStringTtlvDeserializer}.
   */
  public DataByteStringTtlvDeserializer() {
    super(DataByteString.kmipTag, DataByteString.encodingType);
  }

  @Override
  protected DataByteString.DataByteStringBuilder createBuilder() {
    return DataByteString.builder();
  }

  @Override
  protected void setValue(DataByteString.DataByteStringBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected DataByteString build(DataByteString.DataByteStringBuilder builder) {
    return builder.build();
  }
}
