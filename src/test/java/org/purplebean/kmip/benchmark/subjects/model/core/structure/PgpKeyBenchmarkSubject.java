package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.PgpKey;
import org.purplebean.kmip.model.core.type.PgpKeyVersion;

public class PgpKeyBenchmarkSubject extends KmipBenchmarkSubject<PgpKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public PgpKeyBenchmarkSubject() throws Exception {
    PgpKey subject = PgpKey
        .builder()
        .pgpKeyVersion(PgpKeyVersion.of(4))
        .keyBlock(KeyBlock
            .builder()
            .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
            .build())
        .build();
    initialize(subject, PgpKey.class);
  }

  @Override
  public String name() {
    return "PgpKey";
  }

}