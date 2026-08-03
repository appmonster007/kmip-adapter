package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.AttestationAssertion;

public class AttestationAssertionBenchmarkSubject
    extends KmipBenchmarkSubject<AttestationAssertion> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public AttestationAssertionBenchmarkSubject() throws Exception {
    AttestationAssertion subject =
        AttestationAssertion.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
    initialize(subject, AttestationAssertion.class);
  }

  @Override
  public String name() {
    return "AttestationAssertion";
  }

}