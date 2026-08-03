package org.purplebean.kmip.codec.xml.model.core.structure;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.TtlvDataType;
import org.purplebean.kmip.model.core.enumeration.NameType;
import org.purplebean.kmip.model.core.structure.VendorExtension;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("VendorExtension Xml Serialization Tests")
class VendorExtensionXmlTest extends AbstractXmlSerializationTestSuite<VendorExtension> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<VendorExtension> type() {
    return VendorExtension.class;
  }

  @Override
  public VendorExtension createDefault() {
    return VendorExtension
        .builder()
        .ttlvDataType(TtlvDataType.ofTextString(
            KmipTag.Standard.UNIQUE_IDENTIFIER,
            "test-value"
        ))
        .build();
  }

  @Override
  public VendorExtension createVariant() {
    return VendorExtension
        .builder()
        .ttlvDataType(TtlvDataType.ofStructure(
            KmipTag.register(0x540124, "0x540124", Set.of(KmipSpec.V1_2)),
            TtlvDataType.ofTextString(
                KmipTag.register(0x540125, "0x540125", Set.of(KmipSpec.V1_2)),
                "test-value-a"),
            TtlvDataType.ofInteger(
                KmipTag.register(0x540126, "0x540126", Set.of(KmipSpec.V1_2)),
                100),
            TtlvDataType.ofLongInteger(
                KmipTag.register(0x540127, "0x540127", Set.of(KmipSpec.V1_2)),
                1000L),
            TtlvDataType.ofBigInteger(
                KmipTag.register(0x540128, "0x540128", Set.of(KmipSpec.V1_2)),
                BigInteger.valueOf(10000)),
            TtlvDataType.ofEnumeration(
                KmipTag.Standard.NAME_TYPE,
                NameType.Standard.UNINTERPRETED_TEXT_STRING),
            TtlvDataType.ofBoolean(
                KmipTag.register(0x540130, "0x540130", Set.of(KmipSpec.V1_2)),
                true),
            TtlvDataType.ofByteString(
                KmipTag.register(0x540131, "0x540131", Set.of(KmipSpec.V1_2)),
                ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03})),
            TtlvDataType.ofDateTime(
                KmipTag.register(0x540132, "0x540132", Set.of(KmipSpec.V1_2)),
                OffsetDateTime.now()),
            TtlvDataType.ofInterval(
                KmipTag.register(0x540133, "0x540133", Set.of(KmipSpec.V1_2)),
                3600)
        ))
        .build();
  }
}
