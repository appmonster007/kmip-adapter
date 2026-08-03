package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.SecretDataType;

public class SecretDataTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SecretDataType, SecretDataType.SecretDataTypeBuilder> {

  public SecretDataTypeTtlvDeserializer() {
    super(SecretDataType.kmipTag, SecretDataType.encodingType);
  }

  @Override
  protected SecretDataType.SecretDataTypeBuilder createBuilder() {
    return SecretDataType.builder();
  }

  @Override
  protected void setValue(SecretDataType.SecretDataTypeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(SecretDataType.fromValue(value));
  }

  @Override
  protected SecretDataType build(SecretDataType.SecretDataTypeBuilder builder) {
    return builder.build();
  }
}
