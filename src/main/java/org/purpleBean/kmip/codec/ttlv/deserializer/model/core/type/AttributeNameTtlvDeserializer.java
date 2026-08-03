package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.AttributeName;

public class AttributeNameTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AttributeName, AttributeName.AttributeNameBuilder> {

  public AttributeNameTtlvDeserializer() {
    super(AttributeName.kmipTag, AttributeName.encodingType);
  }

  @Override
  protected AttributeName.AttributeNameBuilder createBuilder() {
    return AttributeName.builder();
  }

  @Override
  protected void setValue(AttributeName.AttributeNameBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected AttributeName build(AttributeName.AttributeNameBuilder builder) {
    return builder.build();
  }
}