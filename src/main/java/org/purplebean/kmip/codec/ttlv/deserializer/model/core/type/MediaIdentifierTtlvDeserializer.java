package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.MediaIdentifier;

/**
 * TTLV deserializer for {@link MediaIdentifier}.
 */
public class MediaIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<MediaIdentifier, MediaIdentifier.MediaIdentifierBuilder> {

  /**
   * Constructs a new {@link MediaIdentifierTtlvDeserializer}.
   */
  public MediaIdentifierTtlvDeserializer() {
    super(MediaIdentifier.kmipTag, MediaIdentifier.encodingType);
  }

  @Override
  protected MediaIdentifier.MediaIdentifierBuilder createBuilder() {
    return MediaIdentifier.builder();
  }

  @Override
  protected void setValue(MediaIdentifier.MediaIdentifierBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected MediaIdentifier build(MediaIdentifier.MediaIdentifierBuilder builder) {
    return builder.build();
  }
}
