package org.purplebean.kmip.benchmark.subjects.model.v2x1.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.ProcessingStage;

/**
 * Benchmark subject for {@link ProcessingStage}.
 */
public class ProcessingStageBenchmarkSubject extends KmipBenchmarkSubject<ProcessingStage> {

  /**
   * Constructs a new {@link ProcessingStageBenchmarkSubject}.
   */
  public ProcessingStageBenchmarkSubject() throws Exception {
    ProcessingStage processingStage = ProcessingStage.Standard.SUBMITTED.inst();
    initialize(processingStage, ProcessingStage.class);
  }

  @Override
  public String name() {
    return "ProcessingStage";
  }

}
