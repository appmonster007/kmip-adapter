package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.TtlvDataType;
import org.purplebean.kmip.model.core.structure.VendorExtension;

/**
 * Benchmark subject for {@link VendorExtension}.
 */
public class VendorExtensionBenchmarkSubject extends KmipBenchmarkSubject<VendorExtension> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link VendorExtensionBenchmarkSubject}.
   */
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
