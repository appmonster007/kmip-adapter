package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RevocationReasonCode;

public class RevocationReasonCodeBenchmarkSubject
    extends KmipBenchmarkSubject<RevocationReasonCode> {

  public RevocationReasonCodeBenchmarkSubject() throws Exception {
    RevocationReasonCode revocationReasonCode = RevocationReasonCode.Standard.KEY_COMPROMISE.inst();
    initialize(revocationReasonCode, RevocationReasonCode.class);
  }

  @Override
  public String name() {
    return "RevocationReasonCode";
  }

}
