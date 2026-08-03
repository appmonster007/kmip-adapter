package org.purpleBean.kmip.model.v2_1.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2_1.enumeration.EndpointRole;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("SetEndpointRoleOpResponsePayload Domain Tests")
class SetEndpointRoleOpResponsePayloadTest
    extends AbstractKmipStructureTestSuite<SetEndpointRoleOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<SetEndpointRoleOpResponsePayload> type() {
    return SetEndpointRoleOpResponsePayload.class;
  }

  @Override
  protected SetEndpointRoleOpResponsePayload createDefault() {
    return SetEndpointRoleOpResponsePayload
        .builder()
        .endpointRole(EndpointRole.Standard.SERVER.inst())
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
    assertThat(values.get(0)).isInstanceOf(EndpointRole.class);
  }
}