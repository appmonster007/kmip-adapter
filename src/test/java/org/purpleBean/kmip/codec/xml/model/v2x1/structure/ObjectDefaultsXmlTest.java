package org.purpleBean.kmip.codec.xml.model.v2x1.structure;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.ObjectDefaults;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ObjectDefaults Xml Serialization Tests")
class ObjectDefaultsXmlTest extends AbstractXmlSerializationTestSuite<ObjectDefaults> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<ObjectDefaults> type() {
    return ObjectDefaults.class;
  }

  @Override
  public ObjectDefaults createDefault() {
    return ObjectDefaults
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .attributes(Attributes.of(Collections.emptyList()))
        .build();
  }

  @Override
  public ObjectDefaults createVariant() {
    return ObjectDefaults
        .builder()
        .objectType(ObjectType.Standard.CERTIFICATE.inst())
        .attributes(Attributes.of(Collections.emptyList()))
        .build();
  }
}
