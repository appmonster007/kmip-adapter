package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.structure.KeyMaterialStructure;
import org.purplebean.kmip.model.core.type.ActivationDate;

/**
 * Benchmark subject for {@link KeyMaterialStructure}.
 */
public class KeyMaterialStructureBenchmarkSubject
    extends KmipBenchmarkSubject<KeyMaterialStructure> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link KeyMaterialStructureBenchmarkSubject}.
   */
  public KeyMaterialStructureBenchmarkSubject() throws Exception {
    var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    ActivationDate activationDate = ActivationDate
        .builder()
        .value(fixed)
        .build();
    State state = State.Standard.ACTIVE.inst();
    KeyMaterialStructure keyMaterialStructure =
        KeyMaterialStructure.of(List.of(activationDate, state));
    initialize(keyMaterialStructure, KeyMaterialStructure.class);
  }

  @Override
  public String name() {
    return "KeyMaterialStructure";
  }

}