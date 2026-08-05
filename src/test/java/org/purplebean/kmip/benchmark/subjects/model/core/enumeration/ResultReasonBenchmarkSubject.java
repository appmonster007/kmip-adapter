package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ResultReason;

/**
 * Benchmark subject for {@link ResultReason}.
 */
public class ResultReasonBenchmarkSubject extends KmipBenchmarkSubject<ResultReason> {

  /**
   * Constructs a new {@link ResultReasonBenchmarkSubject}.
   */
  public ResultReasonBenchmarkSubject() throws Exception {
    ResultReason resultReason = ResultReason.Standard.ITEM_NOT_FOUND.inst();
    initialize(resultReason, ResultReason.class);
  }

  @Override
  public String name() {
    return "ResultReason";
  }

}
