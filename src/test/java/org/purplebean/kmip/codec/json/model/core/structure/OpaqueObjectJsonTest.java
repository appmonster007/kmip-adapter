package org.purplebean.kmip.codec.json.model.core.structure;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.OpaqueDataType;
import org.purplebean.kmip.model.core.structure.OpaqueObject;
import org.purplebean.kmip.model.core.type.OpaqueDataValue;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("OpaqueObject Json Serialization Tests")
class OpaqueObjectJsonTest extends AbstractJsonSerializationTestSuite<OpaqueObject> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<OpaqueObject> type() {
    return OpaqueObject.class;
  }

  @Override
  public OpaqueObject createDefault() {
    return OpaqueObject
        .builder()
        .opaqueDataType(OpaqueDataType
            .register(0x8000CDEF, "Custom", Set.of(KmipSpec.UnknownVersion))
            .inst())
        .opaqueDataValue(OpaqueDataValue.of(new byte[0]))
        .build();
  }

  @Override
  public OpaqueObject createVariant() {
    return OpaqueObject
        .builder()
        .opaqueDataType(OpaqueDataType
            .register(0x8000CDF0, "Custom-2", Set.of(KmipSpec.UnknownVersion))
            .inst())
        .opaqueDataValue(OpaqueDataValue.of(new byte[1]))
        .build();
  }
}