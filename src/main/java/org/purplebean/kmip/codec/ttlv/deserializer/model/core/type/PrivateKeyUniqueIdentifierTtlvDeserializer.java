package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;

/**
 * TTLV deserializer for {@link PrivateKeyUniqueIdentifier}.
 */
public class PrivateKeyUniqueIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PrivateKeyUniqueIdentifier,
        PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder> {

  /**
   * Constructs a new {@link PrivateKeyUniqueIdentifierTtlvDeserializer}.
   */
  public PrivateKeyUniqueIdentifierTtlvDeserializer() {
    super(PrivateKeyUniqueIdentifier.kmipTag, PrivateKeyUniqueIdentifier.encodingType);
  }

  @Override
  protected PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder createBuilder() {
    return PrivateKeyUniqueIdentifier.builder();
  }

  @Override
  protected void setValue(PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder builder,
                          byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected PrivateKeyUniqueIdentifier build(
      PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder builder) {
    return builder.build();
  }
}
