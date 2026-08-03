package org.purplebean.kmip.codec.ttlv.model.core.enumeration;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.OpaqueDataType;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("OpaqueDataType TTLV Serialization")
class OpaqueDataTypeTtlvTest extends AbstractTtlvSerializationTestSuite<OpaqueDataType> {
  @Override
  public Class<OpaqueDataType> type() {
    return OpaqueDataType.class;
  }

  @Override
  public OpaqueDataType createDefault() {
    return OpaqueDataType
        .register(0x80000000, "Custom", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }

  @Override
  public OpaqueDataType createVariant() {
    return OpaqueDataType
        .register(0x80000001, "Custom2", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }
}
