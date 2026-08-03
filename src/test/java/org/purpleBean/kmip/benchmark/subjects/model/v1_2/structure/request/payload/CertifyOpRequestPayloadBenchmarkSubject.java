package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.CertificateRequest;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CertifyOpRequestPayload;

public class CertifyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CertifyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertifyOpRequestPayloadBenchmarkSubject() throws Exception {
    CertifyOpRequestPayload subject = CertifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid")
            .build())
        .certificateRequestType(CertificateRequestType.Standard.PKCS_10.inst())
        .certificateRequest(CertificateRequest.of(new byte[] {0x01, 0x02, 0x03}))
        .templateAttribute(TemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .build();
    initialize(subject, CertifyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CertifyOpRequestPayload";
  }

  @Override
  public void setup() throws Exception {
    KmipContext.setSpec(spec);
  }

  @Override
  public void tearDown() {
    KmipContext.clear();
  }
}