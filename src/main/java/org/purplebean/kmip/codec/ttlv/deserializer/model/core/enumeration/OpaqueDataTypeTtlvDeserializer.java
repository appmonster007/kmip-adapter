package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.OpaqueDataType;

/**
 * TTLV deserializer for {@link OpaqueDataType}.
 */
public class OpaqueDataTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<OpaqueDataType, OpaqueDataType.OpaqueDataTypeBuilder> {

  /**
   * Constructs a new {@link OpaqueDataTypeTtlvDeserializer}.
   */
  public OpaqueDataTypeTtlvDeserializer() {
    super(OpaqueDataType.kmipTag, OpaqueDataType.encodingType);
  }

  @Override
  protected OpaqueDataType.OpaqueDataTypeBuilder createBuilder() {
    return OpaqueDataType.builder();
  }

  @Override
  protected void setValue(OpaqueDataType.OpaqueDataTypeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(OpaqueDataType.fromValue(value));
  }

  @Override
  protected OpaqueDataType build(OpaqueDataType.OpaqueDataTypeBuilder builder) {
    return builder.build();
  }
}
