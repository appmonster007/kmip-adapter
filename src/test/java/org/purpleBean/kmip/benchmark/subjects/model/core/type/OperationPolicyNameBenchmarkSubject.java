package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.OperationPolicyName;

public class OperationPolicyNameBenchmarkSubject extends KmipBenchmarkSubject<OperationPolicyName> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public OperationPolicyNameBenchmarkSubject() throws Exception {
    OperationPolicyName operationPolicyName = OperationPolicyName
        .builder()
        .value("test")
        .build();
    initialize(operationPolicyName, OperationPolicyName.class);
  }

  @Override
  public String name() {
    return "OperationPolicyName";
  }

}
