package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.IterationCount;
import org.purplebean.kmip.model.core.type.Password;
import org.purplebean.kmip.model.v3x0.structure.PasswordCredential;
import org.purplebean.kmip.model.v3x0.type.PasswordSalt;
import org.purplebean.kmip.model.v3x0.type.PasswordSaltAlgorithm;
import org.purplebean.kmip.model.v3x0.type.SaltedPassword;

/**
 * TTLV deserializer for {@link PasswordCredential}.
 */
public class PasswordCredentialTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PasswordCredential,
        PasswordCredential.PasswordCredentialBuilder> {

  /**
   * Constructs a new {@link PasswordCredentialTtlvDeserializer}.
   */
  public PasswordCredentialTtlvDeserializer() {
    super(PasswordCredential.kmipTag, PasswordCredential.encodingType);
  }

  @Override
  protected PasswordCredential.PasswordCredentialBuilder createBuilder() {
    return PasswordCredential.builder();
  }

  @Override
  protected void setValue(PasswordCredential.PasswordCredentialBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag == Password.kmipTag.getValue()) {
      builder.password(mapper.readValue(p, Password.class));
    } else if (nodeTag == PasswordSalt.kmipTag.getValue()) {
      builder.passwordSalt(mapper.readValue(p, PasswordSalt.class));
    } else if (nodeTag == PasswordSaltAlgorithm.kmipTag.getValue()) {
      builder.passwordSaltAlgorithm(mapper.readValue(p, PasswordSaltAlgorithm.class));
    } else if (nodeTag == SaltedPassword.kmipTag.getValue()) {
      builder.saltedPassword(mapper.readValue(p, SaltedPassword.class));
    } else if (nodeTag == IterationCount.kmipTag.getValue()) {
      builder.iterationCount(mapper.readValue(p, IterationCount.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected PasswordCredential build(PasswordCredential.PasswordCredentialBuilder builder) {
    return builder.build();
  }
}