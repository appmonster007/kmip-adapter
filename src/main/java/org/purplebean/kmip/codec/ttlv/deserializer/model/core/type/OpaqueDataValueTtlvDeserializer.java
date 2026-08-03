package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.OpaqueDataValue;

/**
 * TTLV deserializer for {@link OpaqueDataValue}.
 */
public class OpaqueDataValueTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<OpaqueDataValue, OpaqueDataValue.OpaqueDataValueBuilder> {

  /**
   * Constructs a new {@link OpaqueDataValueTtlvDeserializer}.
   */
  public OpaqueDataValueTtlvDeserializer() {
    super(OpaqueDataValue.kmipTag, OpaqueDataValue.encodingType);
  }

  @Override
  protected OpaqueDataValue.OpaqueDataValueBuilder createBuilder() {
    return OpaqueDataValue.builder();
  }

  @Override
  protected void setValue(OpaqueDataValue.OpaqueDataValueBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected OpaqueDataValue build(OpaqueDataValue.OpaqueDataValueBuilder builder) {
    return builder.build();
  }
}