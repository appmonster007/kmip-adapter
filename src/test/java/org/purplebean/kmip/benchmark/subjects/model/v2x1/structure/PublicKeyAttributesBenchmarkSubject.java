package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import java.util.Collections;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.PublicKeyAttributes;

/**
 * Benchmark subject for {@link PublicKeyAttributes}.
 */
public class PublicKeyAttributesBenchmarkSubject extends KmipBenchmarkSubject<PublicKeyAttributes> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link PublicKeyAttributesBenchmarkSubject}.
   */
  public PublicKeyAttributesBenchmarkSubject() throws Exception {
    PublicKeyAttributes subject = PublicKeyAttributes.of(Collections.emptyList());
    initialize(subject, PublicKeyAttributes.class);
  }

  @Override
  public String name() {
    return "PublicKeyAttributes";
  }
}
