package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CancellationResult;

/**
 * Benchmark subject for {@link CancellationResult}.
 */
public class CancellationResultBenchmarkSubject extends KmipBenchmarkSubject<CancellationResult> {

  /**
   * Constructs a new {@link CancellationResultBenchmarkSubject}.
   */
  public CancellationResultBenchmarkSubject() throws Exception {
    CancellationResult cancellationResult = CancellationResult.Standard.UNABLE_TO_CANCEL.inst();
    initialize(cancellationResult, CancellationResult.class);
  }

  @Override
  public String name() {
    return "CancellationResult";
  }

}
