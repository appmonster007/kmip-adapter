package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.MachineIdentifier;

/**
 * Benchmark subject for {@link MachineIdentifier}.
 */
public class MachineIdentifierBenchmarkSubject extends KmipBenchmarkSubject<MachineIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link MachineIdentifierBenchmarkSubject}.
   */
  public MachineIdentifierBenchmarkSubject() throws Exception {
    MachineIdentifier machineIdentifier = MachineIdentifier
        .builder()
        .value("test-machine-id")
        .build();
    initialize(machineIdentifier, MachineIdentifier.class);
  }

  @Override
  public String name() {
    return "MachineIdentifier";
  }

}