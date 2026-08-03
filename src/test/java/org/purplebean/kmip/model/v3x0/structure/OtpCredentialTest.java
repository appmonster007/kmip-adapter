package org.purplebean.kmip.model.v3x0.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v3x0.enumeration.OtpAlgorithm;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("OtpCredential Domain Tests")
class OtpCredentialTest extends AbstractKmipStructureTestSuite<OtpCredential> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<OtpCredential> type() {
    return OtpCredential.class;
  }

  @Override
  protected OtpCredential createDefault() {
    return OtpCredential
        .builder()
        .otpAlgorithm(OtpAlgorithm.of(OtpAlgorithm.Standard.TOTP))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  public int expectedMinComponentCount() {
    return 1;
  }

  @Override
  public void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(1);
    assertThat(values.get(0)).isInstanceOf(OtpAlgorithm.class);
  }
}