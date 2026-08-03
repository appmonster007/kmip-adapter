package org.purpleBean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.core.type.Username;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("UsernameAndPassword Domain Tests")
class UsernameAndPasswordTest extends AbstractKmipStructureTestSuite<UsernameAndPassword> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<UsernameAndPassword> type() {
    return UsernameAndPassword.class;
  }

  @Override
  protected UsernameAndPassword createDefault() {
    return UsernameAndPassword
        .builder()
        .username(Username.of("test-user"))
        .password(Password.of("test-password"))
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
    assertThat(values.get(0)).isInstanceOf(Username.class);
    assertThat(values.get(1)).isInstanceOf(Password.class);
  }
}