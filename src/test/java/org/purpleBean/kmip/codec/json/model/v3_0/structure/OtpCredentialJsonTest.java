package org.purpleBean.kmip.codec.json.model.v3_0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.enumeration.OtpAlgorithm;
import org.purpleBean.kmip.model.v3_0.structure.OtpCredential;
import org.purpleBean.kmip.model.v3_0.type.OtpCounter;
import org.purpleBean.kmip.model.v3_0.type.OtpDigits;
import org.purpleBean.kmip.model.v3_0.type.OtpInterval;
import org.purpleBean.kmip.model.v3_0.type.OtpSerial;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("OtpCredential Json Serialization Tests")
class OtpCredentialJsonTest extends AbstractJsonSerializationTestSuite<OtpCredential> {

  @Override
  public Class<OtpCredential> type() {
    return OtpCredential.class;
  }

  @Override
  public OtpCredential createDefault() {
    return OtpCredential
        .builder()
        .otpAlgorithm(OtpAlgorithm.of(OtpAlgorithm.Standard.TOTP))
        .build();
  }

  @Override
  public OtpCredential createVariant() {
    return OtpCredential
        .builder()
        .otpAlgorithm(OtpAlgorithm.of(OtpAlgorithm.Standard.HOTP))
        .otpSerial(OtpSerial.of("SN-12345"))
        .otpInterval(OtpInterval.of(30))
        .otpDigits(OtpDigits.of(6))
        .otpCounter(OtpCounter.of(42))
        .build();
  }
}