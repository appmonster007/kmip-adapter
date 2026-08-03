package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.BlockCipherMode;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.KeyRoleType;
import org.purpleBean.kmip.model.core.enumeration.PaddingMethod;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.CounterLength;
import org.purpleBean.kmip.model.core.type.FixedFieldLength;
import org.purpleBean.kmip.model.core.type.InitialCounterValue;
import org.purpleBean.kmip.model.core.type.InvocationFieldLength;
import org.purpleBean.kmip.model.core.type.IvLength;
import org.purpleBean.kmip.model.core.type.RandomIv;
import org.purpleBean.kmip.model.core.type.TagLength;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CryptographicParameters Xml Serialization Tests")
class CryptographicParametersXmlTest
    extends AbstractXmlSerializationTestSuite<CryptographicParameters> {

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