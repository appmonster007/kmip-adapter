package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CancellationResult;

public class CancellationResultBenchmarkSubject extends KmipBenchmarkSubject<CancellationResult> {

  public CancellationResultBenchmarkSubject() throws Exception {
    CancellationResult cancellationResult = CancellationResult.Standard.UNABLE_TO_CANCEL.inst();
    initialize(cancellationResult, CancellationResult.class);
  }

  @Override
  public String name() {
    return "CancellationResult";
  }

}
