package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.AttributeIndex;

/**
 * TTLV deserializer for {@link AttributeIndex}.
 */
public class AttributeIndexTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AttributeIndex, AttributeIndex.AttributeIndexBuilder> {

  /**
   * Constructs a new {@link AttributeIndexTtlvDeserializer}.
   */
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