package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.ReplacedUniqueIdentifier;

/**
 * TTLV deserializer for {@link ReplacedUniqueIdentifier}.
 */
public class ReplacedUniqueIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ReplacedUniqueIdentifier,
        ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder> {

  /**
   * Constructs a new {@link ReplacedUniqueIdentifierTtlvDeserializer}.
   */
  public ReplacedUniqueIdentifierTtlvDeserializer() {
    super(ReplacedUniqueIdentifier.kmipTag, ReplacedUniqueIdentifier.encodingType);
  }

  @Override
  protected ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder createBuilder() {
    return ReplacedUniqueIdentifier.builder();
  }

  @Override
  protected void setValue(ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder builder,
                          byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected ReplacedUniqueIdentifier build(
      ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder builder) {
    return builder.build();
  }
}
