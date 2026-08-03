package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeIndex;

public class AttributeIndexTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AttributeIndex, AttributeIndex.AttributeIndexBuilder> {

  public AttributeIndexTtlvDeserializer() {
    super(AttributeIndex.kmipTag, AttributeIndex.encodingType);
  }

  @Override
  protected AttributeIndex.AttributeIndexBuilder createBuilder() {
    return AttributeIndex.builder();
  }

  @Override
  protected void setValue(AttributeIndex.AttributeIndexBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected AttributeIndex build(AttributeIndex.AttributeIndexBuilder builder) {
    return builder.build();
  }
}