package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.DefaultsInformation;
import org.purpleBean.kmip.model.v2x1.structure.ObjectDefaults;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.SetDefaultsOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SetDefaultsOpRequestPayload Ttlv Serialization Tests")
class SetDefaultsOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<SetDefaultsOpRequestPayload> {

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