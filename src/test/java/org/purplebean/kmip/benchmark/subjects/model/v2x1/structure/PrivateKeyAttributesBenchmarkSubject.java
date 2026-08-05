package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import java.util.Collections;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.PrivateKeyAttributes;

/**
 * Benchmark subject for {@link PrivateKeyAttributes}.
 */
public class PrivateKeyAttributesBenchmarkSubject
    extends KmipBenchmarkSubject<PrivateKeyAttributes> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link PrivateKeyAttributesBenchmarkSubject}.
   */
  public PrivateKeyAttributesBenchmarkSubject() throws Exception {
    PrivateKeyAttributes subject = PrivateKeyAttributes.of(Collections.emptyList());
    initialize(subject, PrivateKeyAttributes.class);
  }

  @Override
  public String name() {
    return "PrivateKeyAttributes";
  }
}
