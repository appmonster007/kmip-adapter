package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11OutputParameters;

public class Pkcs11OutputParametersBenchmarkSubject
    extends KmipBenchmarkSubject<Pkcs11OutputParameters> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public Pkcs11OutputParametersBenchmarkSubject() throws Exception {
    Pkcs11OutputParameters subject =
        Pkcs11OutputParameters.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
    initialize(subject, Pkcs11OutputParameters.class);
  }

  @Override
  public String name() {
    return "Pkcs11OutputParameters";
  }
}