package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.ExtensionTag;

/**
 * TTLV deserializer for {@link ExtensionTag}.
 */
public class ExtensionTagTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ExtensionTag, ExtensionTag.ExtensionTagBuilder> {

  /**
   * Constructs a new {@link ExtensionTagTtlvDeserializer}.
   */
  public ExtensionTagTtlvDeserializer() {
    super(ExtensionTag.kmipTag, ExtensionTag.encodingType);
  }

  @Override
  protected ExtensionTag.ExtensionTagBuilder createBuilder() {
    return ExtensionTag.builder();
  }

  @Override
  protected void setValue(ExtensionTag.ExtensionTagBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected ExtensionTag build(ExtensionTag.ExtensionTagBuilder builder) {
    return builder.build();
  }
}
