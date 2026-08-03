package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.v3x0.structure.HashedPasswordCredential;
import org.purplebean.kmip.model.v3x0.type.HashedPasswordUsername;
import org.purplebean.kmip.model.v3x0.type.HashedUsernamePassword;

/**
 * TTLV deserializer for {@link HashedPasswordCredential}.
 */
public class HashedPasswordCredentialTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<HashedPasswordCredential,
        HashedPasswordCredential.HashedPasswordCredentialBuilder> {

  /**
   * Constructs a new {@link HashedPasswordCredentialTtlvDeserializer}.
   */
  public HashedPasswordCredentialTtlvDeserializer() {
    super(HashedPasswordCredential.kmipTag, HashedPasswordCredential.encodingType);
  }

  @Override
  protected HashedPasswordCredential.HashedPasswordCredentialBuilder createBuilder() {
    return HashedPasswordCredential.builder();
  }

  @Override
  protected void setValue(HashedPasswordCredential.HashedPasswordCredentialBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM ->
          builder.cryptographicAlgorithm(mapper.readValue(p, CryptographicAlgorithm.class));
      case KmipTag.Standard.HASHED_USERNAME_PASSWORD ->
          builder.hashedUsernamePassword(mapper.readValue(p, HashedUsernamePassword.class));
      case KmipTag.Standard.HASHED_PASSWORD_USERNAME ->
          builder.hashedPasswordUsername(mapper.readValue(p, HashedPasswordUsername.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected HashedPasswordCredential build(
      HashedPasswordCredential.HashedPasswordCredentialBuilder builder) {
    return builder.build();
  }
}