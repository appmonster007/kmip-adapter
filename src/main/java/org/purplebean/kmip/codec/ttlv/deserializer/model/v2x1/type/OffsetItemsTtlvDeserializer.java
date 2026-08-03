package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.OffsetItems;

/**
 * TTLV deserializer for {@link OffsetItems}.
 */
public class OffsetItemsTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<OffsetItems, OffsetItems.OffsetItemsBuilder> {

  /**
   * Constructs a new {@link OffsetItemsTtlvDeserializer}.
   */
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
