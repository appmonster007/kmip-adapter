package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.UniqueIdentifier;

/**
 * TTLV deserializer for {@link UniqueIdentifier}.
 */
public class UniqueIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<UniqueIdentifier,
        UniqueIdentifier.UniqueIdentifierBuilder> {

  /**
   * Constructs a new {@link UniqueIdentifierTtlvDeserializer}.
   */
  public UniqueIdentifierTtlvDeserializer() {
    super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType);
  }

  @Override
  protected UniqueIdentifier.UniqueIdentifierBuilder createBuilder() {
    return UniqueIdentifier.builder();
  }

  @Override
  protected void setValue(UniqueIdentifier.UniqueIdentifierBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(UniqueIdentifier.fromValue(mapper.readValue(p, Integer.class)));
  }

  @Override
  protected UniqueIdentifier build(UniqueIdentifier.UniqueIdentifierBuilder builder) {
    return builder.build();
  }
}