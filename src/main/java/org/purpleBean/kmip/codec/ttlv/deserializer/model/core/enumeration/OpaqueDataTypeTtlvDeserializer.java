package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;

public class OpaqueDataTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<OpaqueDataType, OpaqueDataType.OpaqueDataTypeBuilder> {

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
