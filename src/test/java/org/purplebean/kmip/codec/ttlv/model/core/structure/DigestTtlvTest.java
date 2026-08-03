package org.purplebean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.Digest;
import org.purplebean.kmip.model.core.type.DigestValue;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Digest Ttlv Serialization Tests")
class DigestTtlvTest extends AbstractTtlvSerializationTestSuite<Digest> {

  @Override
  public Class<Digest> type() {
    return Digest.class;
  }

  @Override
  public Digest createDefault() {
    return Digest
        .builder()
        .hashingAlgorithm(HashingAlgorithm.Standard.SHA_256.inst())
        .digestValue(DigestValue.of(new byte[0]))
        .keyFormatType(KeyFormatType.Standard.PKCS_1.inst())
        .build();
  }
}
