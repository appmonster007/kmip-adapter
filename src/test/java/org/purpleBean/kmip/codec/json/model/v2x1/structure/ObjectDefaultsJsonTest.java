package org.purplebean.kmip.codec.json.model.v2x1.structure;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.ObjectDefaults;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ObjectDefaults Json Serialization Tests")
class ObjectDefaultsJsonTest extends AbstractJsonSerializationTestSuite<ObjectDefaults> {

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
