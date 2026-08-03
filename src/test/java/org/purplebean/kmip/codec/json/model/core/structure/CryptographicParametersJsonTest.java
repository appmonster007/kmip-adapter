package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.BlockCipherMode;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.KeyRoleType;
import org.purplebean.kmip.model.core.enumeration.PaddingMethod;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.CounterLength;
import org.purplebean.kmip.model.core.type.FixedFieldLength;
import org.purplebean.kmip.model.core.type.InitialCounterValue;
import org.purplebean.kmip.model.core.type.InvocationFieldLength;
import org.purplebean.kmip.model.core.type.IvLength;
import org.purplebean.kmip.model.core.type.RandomIv;
import org.purplebean.kmip.model.core.type.TagLength;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CryptographicParameters Json Serialization Tests")
class CryptographicParametersJsonTest
    extends AbstractJsonSerializationTestSuite<CryptographicParameters> {

  @Override
  public Class<CryptographicParameters> type() {
    return CryptographicParameters.class;
  }

  @Override
  public CryptographicParameters createDefault() {
    return CryptographicParameters
        .builder()
        .blockCipherMode(BlockCipherMode.Standard.CBC.inst())
        .paddingMethod(PaddingMethod.Standard.PKCS5.inst())
        .hashingAlgorithm(HashingAlgorithm.Standard.SHA_256.inst())
        .keyRoleType(KeyRoleType.Standard.KEK.inst())
        .digitalSignatureAlgorithm(
            DigitalSignatureAlgorithm.Standard.SHA_1_WITH_RSA_ENCRYPTION.inst())
        .cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst())
        .randomIv(RandomIv.of(true))
        .ivLength(IvLength.of(128))
        .tagLength(TagLength.of(128))
        .fixedFieldLength(FixedFieldLength.of(128))
        .invocationFieldLength(InvocationFieldLength.of(128))
        .counterLength(CounterLength.of(128))
        .initialCounterValue(InitialCounterValue.of(1))
        .build();
  }
}