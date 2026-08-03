package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.DefaultsInformation;
import org.purplebean.kmip.model.v2x1.structure.ObjectDefaults;
import org.purplebean.kmip.model.v2x1.structure.request.payload.SetDefaultsOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SetDefaultsOpRequestPayload Json Serialization Tests")
class SetDefaultsOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<SetDefaultsOpRequestPayload> {

  @Override
  public Class<SetDefaultsOpRequestPayload> type() {
    return SetDefaultsOpRequestPayload.class;
  }

  @Override
  public SetDefaultsOpRequestPayload createDefault() {
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
  public SetDefaultsOpRequestPayload createVariant() {
    return SetDefaultsOpRequestPayload
        .builder()
        .defaultsInformation(DefaultsInformation.of(List.of(ObjectDefaults
            .builder()
            .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
            .attributes(Attributes.of(Collections.emptyList()))
            .build())))
        .build();
  }
}