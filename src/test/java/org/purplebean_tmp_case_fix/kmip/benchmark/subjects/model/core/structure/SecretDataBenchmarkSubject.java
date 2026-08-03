package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.enumeration.SecretDataType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.SecretData;

public class SecretDataBenchmarkSubject extends KmipBenchmarkSubject<SecretData> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public SecretDataBenchmarkSubject() throws Exception {
    SecretData subject = SecretData
        .builder()
        .secretDataType(SecretDataType.Standard.PASSWORD.inst())
        .keyBlock(KeyBlock
            .builder()
            .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
            .build())
        .build();
    initialize(subject, SecretData.class);
  }

  @Override
  public String name() {
    return "SecretData";
  }

}