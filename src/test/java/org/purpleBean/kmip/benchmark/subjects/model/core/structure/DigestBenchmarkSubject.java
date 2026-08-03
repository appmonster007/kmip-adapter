package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.Digest;
import org.purplebean.kmip.model.core.type.DigestValue;

public class DigestBenchmarkSubject extends KmipBenchmarkSubject<Digest> {

  public DigestBenchmarkSubject() throws Exception {
    Digest digest = Digest
        .builder()
        .hashingAlgorithm(HashingAlgorithm.Standard.SHA_256.inst())
        .digestValue(DigestValue.of(new byte[0]))
        .keyFormatType(KeyFormatType.Standard.PKCS_1.inst())
        .build();
    initialize(digest, Digest.class);
  }

  @Override
  public String name() {
    return "Digest";
  }

}
