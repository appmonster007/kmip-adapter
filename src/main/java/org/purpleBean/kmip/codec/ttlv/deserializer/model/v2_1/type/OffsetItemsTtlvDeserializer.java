package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.OffsetItems;

public class OffsetItemsTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<OffsetItems, OffsetItems.OffsetItemsBuilder> {

  public OffsetItemsTtlvDeserializer() {
    super(OffsetItems.kmipTag, OffsetItems.encodingType);
  }

  @Override
  protected OffsetItems.OffsetItemsBuilder createBuilder() {
    return OffsetItems.builder();
  }

  @Override
  protected void setValue(OffsetItems.OffsetItemsBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Integer.class));
  }

  @Override
  protected OffsetItems build(OffsetItems.OffsetItemsBuilder builder) {
    return builder.build();
  }
}
