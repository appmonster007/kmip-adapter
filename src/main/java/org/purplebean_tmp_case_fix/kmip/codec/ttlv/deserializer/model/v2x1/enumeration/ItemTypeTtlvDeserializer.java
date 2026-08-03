package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.ItemType;

public class ItemTypeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ItemType, ItemType.ItemTypeBuilder> {

  public ItemTypeTtlvDeserializer() {
    super(ItemType.kmipTag, ItemType.encodingType);
  }

  @Override
  protected ItemType.ItemTypeBuilder createBuilder() {
    return ItemType.builder();
  }

  @Override
  protected void setValue(ItemType.ItemTypeBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    builder.value(ItemType.fromValue(mapper.readValue(p, Integer.class)));
  }

  @Override
  protected ItemType build(ItemType.ItemTypeBuilder builder) {
    return builder.build();
  }
}