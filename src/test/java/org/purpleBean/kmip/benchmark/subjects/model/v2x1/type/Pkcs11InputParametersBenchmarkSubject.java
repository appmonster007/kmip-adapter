package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.Pkcs11InputParameters;

public class Pkcs11InputParametersBenchmarkSubject
    extends KmipBenchmarkSubject<Pkcs11InputParameters> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public Pkcs11InputParametersBenchmarkSubject() throws Exception {
    Pkcs11InputParameters subject =
        Pkcs11InputParameters.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
    initialize(subject, Pkcs11InputParameters.class);
  }

  @Override
  public String name() {
    return "Pkcs11InputParameters";
  }
}