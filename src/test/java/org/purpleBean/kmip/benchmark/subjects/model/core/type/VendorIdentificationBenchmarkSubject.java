package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.VendorIdentification;

public class VendorIdentificationBenchmarkSubject
    extends KmipBenchmarkSubject<VendorIdentification> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

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