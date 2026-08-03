package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.TagLength;

/**
 * TTLV deserializer for {@link TagLength}.
 */
public class TagLengthTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<TagLength, TagLength.TagLengthBuilder> {

  /**
   * Constructs a new {@link TagLengthTtlvDeserializer}.
   */
  public TagLengthTtlvDeserializer() {
    super(TagLength.kmipTag, TagLength.encodingType);
  }

  @Override
  protected TagLength.TagLengthBuilder createBuilder() {
    return TagLength.builder();
  }

  @Override
  protected void setValue(TagLength.TagLengthBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected TagLength build(TagLength.TagLengthBuilder builder) {
    return builder.build();
  }
}
