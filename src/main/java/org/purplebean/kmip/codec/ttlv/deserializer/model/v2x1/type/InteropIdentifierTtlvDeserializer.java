package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.InteropIdentifier;

/**
 * TTLV deserializer for {@link InteropIdentifier}.
 */
public class InteropIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<InteropIdentifier,
        InteropIdentifier.InteropIdentifierBuilder> {

  /**
   * Constructs a new {@link InteropIdentifierTtlvDeserializer}.
   */
  public InteropIdentifierTtlvDeserializer() {
    super(InteropIdentifier.kmipTag, InteropIdentifier.encodingType);
  }

  @Override
  protected InteropIdentifier.InteropIdentifierBuilder createBuilder() {
    return InteropIdentifier.builder();
  }

  @Override
  protected void setValue(InteropIdentifier.InteropIdentifierBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected InteropIdentifier build(InteropIdentifier.InteropIdentifierBuilder builder) {
    return builder.build();
  }
}
