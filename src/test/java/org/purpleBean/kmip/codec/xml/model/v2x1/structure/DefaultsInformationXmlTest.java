package org.purplebean.kmip.codec.xml.model.v2x1.structure;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.DefaultsInformation;
import org.purplebean.kmip.model.v2x1.structure.ObjectDefaults;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DefaultsInformation Xml Serialization Tests")
class DefaultsInformationXmlTest extends AbstractXmlSerializationTestSuite<DefaultsInformation> {

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
