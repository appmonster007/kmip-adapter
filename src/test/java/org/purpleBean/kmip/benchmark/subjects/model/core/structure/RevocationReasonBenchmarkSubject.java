package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.model.core.structure.RevocationReason;
import org.purpleBean.kmip.model.core.type.RevocationMessage;

public class RevocationReasonBenchmarkSubject extends KmipBenchmarkSubject<RevocationReason> {

  public RevocationReasonBenchmarkSubject() throws Exception {
    RevocationReason revocationReason = RevocationReason
        .builder()
        .revocationReasonCode(RevocationReasonCode.Standard.KEY_COMPROMISE.inst())
        .revocationMessage(RevocationMessage.of("test-message"))
        .build();
    initialize(revocationReason, RevocationReason.class);
  }

  @Override
  public String name() {
    return "RevocationReason";
  }

}
