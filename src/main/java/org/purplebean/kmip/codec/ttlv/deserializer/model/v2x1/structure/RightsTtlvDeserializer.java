package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.Right;
import org.purplebean.kmip.model.v2x1.structure.Rights;

/**
 * TTLV deserializer for {@link Rights}.
 */
public class RightsTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Rights, Rights.RightsBuilder> {

  /**
   * Constructs a new {@link RightsTtlvDeserializer}.
   */
  public RightsTtlvDeserializer() {
    super(Rights.kmipTag, Rights.encodingType);
  }

  @Override
  protected Rights.RightsBuilder createBuilder() {
    return Rights.builder();
  }

  @Override
  protected void setValue(Rights.RightsBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.RIGHT -> builder.right(mapper.readValue(p, Right.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Rights build(Rights.RightsBuilder builder) {
    return builder.build();
  }
}