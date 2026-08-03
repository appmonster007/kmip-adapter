package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.Digest;
import org.purplebean.kmip.model.core.type.DigestValue;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Digest Json Serialization Tests")
class DigestJsonTest extends AbstractJsonSerializationTestSuite<Digest> {

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
