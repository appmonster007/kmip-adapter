package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.StorageStatusMask;

/**
 * Benchmark subject for {@link StorageStatusMask}.
 */
public class StorageStatusMaskBenchmarkSubject extends KmipBenchmarkSubject<StorageStatusMask> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link StorageStatusMaskBenchmarkSubject}.
   */
  public StorageStatusMaskBenchmarkSubject() throws Exception {
    StorageStatusMask storageStatusMask = StorageStatusMask
        .builder()
        .value(1)
        .build();
    initialize(storageStatusMask, StorageStatusMask.class);
  }

  @Override
  public String name() {
    return "StorageStatusMask";
  }

}