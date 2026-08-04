package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.structure.link.CredentialLink;

/**
 * TTLV deserializer for {@link CredentialLink}.
 */
public class CredentialLinkTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CredentialLink, CredentialLink.CredentialLinkBuilder> {

  /**
   * Constructs a new {@link CredentialLinkTtlvDeserializer}.
   */
  public CredentialLinkTtlvDeserializer() {
    super(CredentialLink.kmipTag, CredentialLink.encodingType);
  }

  @Override
  protected CredentialLink.CredentialLinkBuilder createBuilder() {
    return CredentialLink.builder();
  }

  @Override
  protected void setValue(CredentialLink.CredentialLinkBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected CredentialLink build(CredentialLink.CredentialLinkBuilder builder) {
    return builder.build();
  }
}
