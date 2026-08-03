package org.purpleBean.kmip.codec.json.model.v2x1.structure;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.DefaultsInformation;
import org.purpleBean.kmip.model.v2x1.structure.ObjectDefaults;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DefaultsInformation Json Serialization Tests")
class DefaultsInformationJsonTest extends AbstractJsonSerializationTestSuite<DefaultsInformation> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<DefaultsInformation> type() {
    return DefaultsInformation.class;
  }

  @Override
  public DefaultsInformation createDefault() {
    return DefaultsInformation.of(List.of(
        ObjectDefaults
            .builder()
            .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
            .attributes(Attributes.of(Collections.emptyList()))
            .build()
    ));
  }

  @Override
  public DefaultsInformation createVariant() {
    return DefaultsInformation.of(List.of(
        ObjectDefaults
            .builder()
            .objectType(ObjectType.Standard.CERTIFICATE.inst())
            .attributes(Attributes.of(Collections.emptyList()))
            .build()
    ));
  }
}
