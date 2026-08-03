package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v2_1.structure.DefaultsInformation;
import org.purpleBean.kmip.model.v2_1.structure.ObjectDefaults;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("SetDefaultsOpRequestPayload Domain Tests")
class SetDefaultsOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<SetDefaultsOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<SetDefaultsOpRequestPayload> type() {
    return SetDefaultsOpRequestPayload.class;
  }

  @Override
  protected SetDefaultsOpRequestPayload createDefault() {
    return SetDefaultsOpRequestPayload
        .builder()
        .defaultsInformation(DefaultsInformation.of(List.of(ObjectDefaults
            .builder()
            .objectType(ObjectType.Standard.CERTIFICATE.inst())
            .attributes(Attributes.of(Collections.emptyList()))
            .build())))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  public int expectedMinComponentCount() {
    // TODO: Set the expected minimum number of components
    return 0;
  }

  @Override
  public void validateComponents(List<KmipDataType> values) {
    // TODO: Validate the components of the structure
    // assertThat(values).hasSize(0);
  }
}