package org.purplebean.kmip.model.v2x1.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.enumeration.EndpointRole;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("SetEndpointRoleOpRequestPayload Domain Tests")
class SetEndpointRoleOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<SetEndpointRoleOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<SetEndpointRoleOpRequestPayload> type() {
    return SetEndpointRoleOpRequestPayload.class;
  }

  @Override
  protected SetEndpointRoleOpRequestPayload createDefault() {
    return SetEndpointRoleOpRequestPayload
        .builder()
        .endpointRole(EndpointRole.Standard.CLIENT.inst())
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