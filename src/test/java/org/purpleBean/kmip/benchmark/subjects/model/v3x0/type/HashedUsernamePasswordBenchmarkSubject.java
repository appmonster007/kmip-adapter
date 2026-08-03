package org.purpleBean.kmip.benchmark.subjects.model.v3x0.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3x0.type.HashedUsernamePassword;

public class HashedUsernamePasswordBenchmarkSubject
    extends KmipBenchmarkSubject<HashedUsernamePassword> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

  public HashedUsernamePasswordBenchmarkSubject() throws Exception {
    HashedUsernamePassword subject = HashedUsernamePassword.of(
        ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));  // TODO: Create a default instance
    initialize(subject, HashedUsernamePassword.class);
  }

  @Override
  public String name() {
    return "HashedUsernamePassword";
  }
}