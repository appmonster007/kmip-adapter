package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ResultStatus;

/**
 * Benchmark subject for {@link ResultStatus}.
 */
public class ResultStatusBenchmarkSubject extends KmipBenchmarkSubject<ResultStatus> {

  /**
   * Constructs a new {@link ResultStatusBenchmarkSubject}.
   */
  public ResultStatusBenchmarkSubject() throws Exception {
    ResultStatus resultStatus = ResultStatus.Standard.OPERATION_FAILED.inst();
    initialize(resultStatus, ResultStatus.class);
  }

  @Override
  public String name() {
    return "ResultStatus";
  }

}
