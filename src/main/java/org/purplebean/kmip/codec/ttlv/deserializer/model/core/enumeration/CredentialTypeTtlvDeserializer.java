package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.CredentialType;

/**
 * TTLV deserializer for {@link CredentialType}.
 */
public class CredentialTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CredentialType, CredentialType.CredentialTypeBuilder> {

  /**
   * Constructs a new {@link CredentialTypeTtlvDeserializer}.
   */
  public CredentialTypeTtlvDeserializer() {
    super(CredentialType.kmipTag, CredentialType.encodingType);
  }

  @Override
  protected CredentialType.CredentialTypeBuilder createBuilder() {
    return CredentialType.builder();
  }

  @Override
  protected void setValue(CredentialType.CredentialTypeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(CredentialType.fromValue(value));
  }

  @Override
  protected CredentialType build(CredentialType.CredentialTypeBuilder builder) {
    return builder.build();
  }
}
