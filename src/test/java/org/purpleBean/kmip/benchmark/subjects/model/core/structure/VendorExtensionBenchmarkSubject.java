package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.TtlvDataType;
import org.purpleBean.kmip.model.core.structure.VendorExtension;

public class VendorExtensionBenchmarkSubject extends KmipBenchmarkSubject<VendorExtension> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public VendorExtensionBenchmarkSubject() throws Exception {
    VendorExtension subject = VendorExtension
        .builder()
        .ttlvDataType(TtlvDataType
            .builder()
            .kmipTag(KmipTag.Standard.UNIQUE_IDENTIFIER.inst())
            .encodingType(EncodingType.TEXT_STRING)
            .value("test-value")
            .build())
        .build();
    initialize(subject, VendorExtension.class);
  }

  @Override
  public String name() {
    return "VendorExtension";
  }
}
