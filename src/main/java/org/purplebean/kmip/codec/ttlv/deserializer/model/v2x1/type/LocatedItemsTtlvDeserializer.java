package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.LocatedItems;

/**
 * TTLV deserializer for {@link LocatedItems}.
 */
public class LocatedItemsTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<LocatedItems, LocatedItems.LocatedItemsBuilder> {

  /**
   * Constructs a new {@link LocatedItemsTtlvDeserializer}.
   */
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