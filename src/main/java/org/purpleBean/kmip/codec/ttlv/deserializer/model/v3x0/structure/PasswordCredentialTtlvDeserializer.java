package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3x0.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.IterationCount;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.v3x0.structure.PasswordCredential;
import org.purpleBean.kmip.model.v3x0.type.PasswordSalt;
import org.purpleBean.kmip.model.v3x0.type.PasswordSaltAlgorithm;
import org.purpleBean.kmip.model.v3x0.type.SaltedPassword;

public class PasswordCredentialTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PasswordCredential,
        PasswordCredential.PasswordCredentialBuilder> {

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