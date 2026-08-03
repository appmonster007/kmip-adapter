package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionBenchmarkSubject
    extends KmipBenchmarkSubject<BatchErrorContinuationOption> {

  public BatchErrorContinuationOptionBenchmarkSubject() throws Exception {
    BatchErrorContinuationOption batchErrorContinuationOption =
        BatchErrorContinuationOption.Standard.CONTINUE.inst();
    initialize(batchErrorContinuationOption, BatchErrorContinuationOption.class);
  }

  @Override
  public String name() {
    return "BatchErrorContinuationOption";
  }

}
