package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.VendorIdentification;

/**
 * Benchmark subject for {@link VendorIdentification}.
 */
public class VendorIdentificationBenchmarkSubject
    extends KmipBenchmarkSubject<VendorIdentification> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link VendorIdentificationBenchmarkSubject}.
   */
  public VendorIdentificationBenchmarkSubject() throws Exception {
    VendorIdentification vendorIdentification = VendorIdentification
        .builder()
        .value("test-vendor")
        .build();
    initialize(vendorIdentification, VendorIdentification.class);
  }

  @Override
  public String name() {
    return "VendorIdentification";
  }

}