package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ShortUniqueIdentifier;

/**
 * TTLV deserializer for {@link ShortUniqueIdentifier}.
 */
public class ShortUniqueIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ShortUniqueIdentifier,
        ShortUniqueIdentifier.ShortUniqueIdentifierBuilder> {

  /**
   * Constructs a new {@link ShortUniqueIdentifierTtlvDeserializer}.
   */
  public ShortUniqueIdentifierTtlvDeserializer() {
    super(ShortUniqueIdentifier.kmipTag, ShortUniqueIdentifier.encodingType);
  }

  @Override
  protected ShortUniqueIdentifier.ShortUniqueIdentifierBuilder createBuilder() {
    return ShortUniqueIdentifier.builder();
  }

  @Override
  protected void setValue(ShortUniqueIdentifier.ShortUniqueIdentifierBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected ShortUniqueIdentifier build(
      ShortUniqueIdentifier.ShortUniqueIdentifierBuilder builder) {
    return builder.build();
  }
}