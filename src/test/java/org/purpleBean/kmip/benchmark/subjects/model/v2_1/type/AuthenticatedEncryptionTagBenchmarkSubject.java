package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.AuthenticatedEncryptionTag;

public class AuthenticatedEncryptionTagBenchmarkSubject
    extends KmipBenchmarkSubject<AuthenticatedEncryptionTag> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public AuthenticatedEncryptionTagBenchmarkSubject() throws Exception {
    AuthenticatedEncryptionTag subject =
        AuthenticatedEncryptionTag.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
    initialize(subject, AuthenticatedEncryptionTag.class);
  }

  @Override
  public String name() {
    return "AuthenticatedEncryptionTag";
  }
}