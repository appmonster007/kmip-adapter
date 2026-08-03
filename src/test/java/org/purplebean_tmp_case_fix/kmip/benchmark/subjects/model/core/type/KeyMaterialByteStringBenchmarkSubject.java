package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.KeyMaterialByteString;

public class KeyMaterialByteStringBenchmarkSubject
    extends KmipBenchmarkSubject<KeyMaterialByteString> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public KeyMaterialByteStringBenchmarkSubject() throws Exception {
    KeyMaterialByteString keyMaterialByteString =
        KeyMaterialByteString.of(new byte[] {0x01, 0x02, 0x03});
    initialize(keyMaterialByteString, KeyMaterialByteString.class);
  }

  @Override
  public String name() {
    return "KeyMaterialByteString";
  }

}