package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.Right;
import org.purpleBean.kmip.model.v2_1.structure.Rights;

public class RightsTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Rights, Rights.RightsBuilder> {

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