package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.LocatedItems;

public class LocatedItemsTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<LocatedItems, LocatedItems.LocatedItemsBuilder> {

  public LocatedItemsTtlvDeserializer() {
    super(LocatedItems.kmipTag, LocatedItems.encodingType);
  }

  @Override
  protected LocatedItems.LocatedItemsBuilder createBuilder() {
    return LocatedItems.builder();
  }

  @Override
  protected void setValue(LocatedItems.LocatedItemsBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Integer.class));
  }

  @Override
  protected LocatedItems build(LocatedItems.LocatedItemsBuilder builder) {
    return builder.build();
  }
}