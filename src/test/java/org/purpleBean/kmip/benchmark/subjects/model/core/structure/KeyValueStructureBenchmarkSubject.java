package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import java.util.List;
import lombok.Getter;
import org.purpleBean.kmip.api.KeyMaterial;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.CustomAttribute;
import org.purpleBean.kmip.model.core.structure.KeyValueStructure;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.KeyMaterialByteString;

public class KeyValueStructureBenchmarkSubject extends KmipBenchmarkSubject<KeyValueStructure> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public KeyValueStructureBenchmarkSubject() throws Exception {
    KeyMaterial keyMaterial = KeyMaterialByteString.of(new byte[] {0x01, 0x02, 0x03});
    KmipAttribute attribute =
        CustomAttribute.of("x-test-attribute", AttributeValue.ofTextString("test-value"));
    KeyValueStructure keyValueStructure = KeyValueStructure
        .builder()
        .keyMaterial(keyMaterial)
        .attributes(List.of(attribute))
        .build();
    initialize(keyValueStructure, KeyValueStructure.class);
  }

  @Override
  public String name() {
    return "KeyValueStructure";
  }

}