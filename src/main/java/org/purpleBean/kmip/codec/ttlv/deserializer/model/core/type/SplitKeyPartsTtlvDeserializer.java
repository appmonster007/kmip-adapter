package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;

public class SplitKeyPartsTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SplitKeyParts, SplitKeyParts.SplitKeyPartsBuilder> {

  public SplitKeyPartsTtlvDeserializer() {
    super(SplitKeyParts.kmipTag, SplitKeyParts.encodingType);
  }

  @Override
  protected SplitKeyParts.SplitKeyPartsBuilder createBuilder() {
    return SplitKeyParts.builder();
  }

  @Override
  protected void setValue(SplitKeyParts.SplitKeyPartsBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected SplitKeyParts build(SplitKeyParts.SplitKeyPartsBuilder builder) {
    return builder.build();
  }
}
