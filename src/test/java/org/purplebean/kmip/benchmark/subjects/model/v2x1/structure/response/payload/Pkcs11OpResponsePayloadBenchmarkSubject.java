package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11Function;
import org.purplebean.kmip.model.v2x1.structure.response.payload.Pkcs11OpResponsePayload;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;

public class Pkcs11OpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<Pkcs11OpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public Pkcs11OpResponsePayloadBenchmarkSubject() throws Exception {
    Pkcs11OpResponsePayload subject = Pkcs11OpResponsePayload
        .builder()
        .pkcs11Interface(org.purplebean.kmip.model.v2x1.type.Pkcs11Interface.of("V3.0"))
        .pkcs11Function(Pkcs11Function.of(Pkcs11Function.Standard.INITIALIZE))
        .pkcs11ReturnCode(org.purplebean.kmip.model.v2x1.enumeration.Pkcs11ReturnCode.of(org.purplebean.kmip.model.v2x1.enumeration.Pkcs11ReturnCode.Standard.OK))
        .correlationValue(CorrelationValue.of(ByteBuffer.wrap(new byte[] {0x01, 0x02})))
        .build();
    initialize(subject, Pkcs11OpResponsePayload.class);
  }

  @Override
  public String name() {
    return "Pkcs11OpResponsePayload";
  }
}