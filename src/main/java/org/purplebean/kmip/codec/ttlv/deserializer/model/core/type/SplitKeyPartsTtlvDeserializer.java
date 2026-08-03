package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.SplitKeyParts;

/**
 * TTLV deserializer for {@link SplitKeyParts}.
 */
public class SplitKeyPartsTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SplitKeyParts, SplitKeyParts.SplitKeyPartsBuilder> {

  /**
   * Constructs a new {@link SplitKeyPartsTtlvDeserializer}.
   */
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
