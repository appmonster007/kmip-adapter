package org.purpleBean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;
import org.purpleBean.kmip.model.core.type.CertificateValue;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("Certificate Domain Tests")
class CertificateTest extends AbstractKmipStructureTestSuite<Certificate> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<Certificate> type() {
    return Certificate.class;
  }

  @Override
  protected Certificate createDefault() {
    return Certificate
        .builder()
        .certificateType(CertificateType.Standard.X_509.inst())
        .certificateValue(CertificateValue.of(new byte[0]))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 2;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(2);
    assertThat(values.get(0)).isInstanceOf(CertificateType.class);
    assertThat(values.get(1)).isInstanceOf(CertificateValue.class);
  }
}