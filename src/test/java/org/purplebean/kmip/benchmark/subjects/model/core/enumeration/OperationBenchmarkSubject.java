package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.Operation;

/**
 * Benchmark subject for {@link Operation}.
 */
public class OperationBenchmarkSubject extends KmipBenchmarkSubject<Operation> {

  /**
   * Constructs a new {@link OperationBenchmarkSubject}.
   */
  public OperationBenchmarkSubject() throws Exception {
    Operation operation = Operation.Standard.CREATE.inst();
    initialize(operation, Operation.class);
  }

  @Override
  public String name() {
    return "Operation";
  }

}
