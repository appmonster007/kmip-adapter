package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.LinkedObjectIdentifier;

/**
 * TTLV deserializer for {@link LinkedObjectIdentifier}.
 */
public class LinkedObjectIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<LinkedObjectIdentifier,
        LinkedObjectIdentifier.LinkedObjectIdentifierBuilder> {

  /**
   * Constructs a new {@link LinkedObjectIdentifierTtlvDeserializer}.
   */
  public LinkedObjectIdentifierTtlvDeserializer() {
    super(LinkedObjectIdentifier.kmipTag, LinkedObjectIdentifier.encodingType);
  }

  @Override
  protected LinkedObjectIdentifier.LinkedObjectIdentifierBuilder createBuilder() {
    return LinkedObjectIdentifier.builder();
  }

  @Override
  protected void setValue(LinkedObjectIdentifier.LinkedObjectIdentifierBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected LinkedObjectIdentifier build(
      LinkedObjectIdentifier.LinkedObjectIdentifierBuilder builder) {
    return builder.build();
  }
}
