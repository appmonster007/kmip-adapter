package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.MaximumItems;

public class MaximumItemsTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<MaximumItems, MaximumItems.MaximumItemsBuilder> {

  public MaximumItemsTtlvDeserializer() {
    super(MaximumItems.kmipTag, MaximumItems.encodingType);
  }

  @Override
  protected MaximumItems.MaximumItemsBuilder createBuilder() {
    return MaximumItems.builder();
  }

  @Override
  protected void setValue(MaximumItems.MaximumItemsBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected MaximumItems build(MaximumItems.MaximumItemsBuilder builder) {
    return builder.build();
  }
}
