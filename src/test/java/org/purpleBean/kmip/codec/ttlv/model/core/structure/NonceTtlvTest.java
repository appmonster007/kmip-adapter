package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Nonce;
import org.purpleBean.kmip.model.core.type.NonceId;
import org.purpleBean.kmip.model.core.type.NonceValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Nonce Ttlv Serialization Tests")
class NonceTtlvTest extends AbstractTtlvSerializationTestSuite<Nonce> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<Nonce> type() {
    return Nonce.class;
  }

  @Override
  public Nonce createDefault() {
    return Nonce
        .builder()
        .nonceId(NonceId.of("test-id".getBytes()))
        .nonceValue(NonceValue.of(new byte[8]))
        .build();
  }

  @Override
  public Nonce createVariant() {
    return Nonce
        .builder()
        .nonceId(NonceId.of("test-id-variant".getBytes()))
        .nonceValue(NonceValue.of(new byte[16]))
        .build();
  }
}