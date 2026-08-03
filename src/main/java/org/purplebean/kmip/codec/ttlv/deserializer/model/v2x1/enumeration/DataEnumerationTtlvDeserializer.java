package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.DataEnumeration;

/**
 * TTLV deserializer for {@link DataEnumeration}.
 */
public class DataEnumerationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DataEnumeration, DataEnumeration.DataEnumerationBuilder> {

  /**
   * Constructs a new {@link DataEnumerationTtlvDeserializer}.
   */
  public DataEnumerationTtlvDeserializer() {
    super(DataEnumeration.kmipTag, DataEnumeration.encodingType);
  }

  @Override
  protected DataEnumeration.DataEnumerationBuilder createBuilder() {
    return DataEnumeration.builder();
  }

  @Override
  protected void setValue(DataEnumeration.DataEnumerationBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(DataEnumeration.fromValue(mapper.readValue(p, Integer.class)));
  }

  @Override
  protected DataEnumeration build(DataEnumeration.DataEnumerationBuilder builder) {
    return builder.build();
  }
}