package org.purpleBean.kmip.codec.xml.model.core;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.NameType;
import org.purpleBean.kmip.model.core.TtlvDataType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Set;

@DisplayName("TtlvDataType Xml Serialization Tests")
class TtlvDataTypeXmlTest extends AbstractXmlSerializationTestSuite<TtlvDataType> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<TtlvDataType> type() {
        return TtlvDataType.class;
    }

    @Override
    protected TtlvDataType createDefault() {
        return TtlvDataType.builder()
                .kmipTag(KmipTag.register(0x540123, "0x540123", Set.of(KmipSpec.V1_2)).inst())
                .encodingType(EncodingType.TEXT_STRING)
                .value("test-value")
                .build();
    }

    @Override
    protected TtlvDataType createVariant() {
        return TtlvDataType.structureOf(
                KmipTag.register(0x540124, "0x540124", Set.of(KmipSpec.V1_2)),
                TtlvDataType.textStringOf(
                        KmipTag.register(0x540125, "0x540125", Set.of(KmipSpec.V1_2)),
                        "test-value-a"),
                TtlvDataType.integerOf(
                        KmipTag.register(0x540126, "0x540126", Set.of(KmipSpec.V1_2)),
                        100),
                TtlvDataType.longIntegerOf(
                        KmipTag.register(0x540127, "0x540127", Set.of(KmipSpec.V1_2)),
                        1000L),
                TtlvDataType.bigIntegerOf(
                        KmipTag.register(0x540128, "0x540128", Set.of(KmipSpec.V1_2)),
                        BigInteger.valueOf(10000)),
                TtlvDataType.enumerationOf(
                        KmipTag.Standard.NAME_TYPE,
                        NameType.Standard.UNINTERPRETED_TEXT_STRING),
                TtlvDataType.booleanOf(
                        KmipTag.register(0x540130, "0x540130", Set.of(KmipSpec.V1_2)),
                        true),
                TtlvDataType.byteStringOf(
                        KmipTag.register(0x540131, "0x540131", Set.of(KmipSpec.V1_2)),
                        ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03})),
                TtlvDataType.dateTimeOf(
                        KmipTag.register(0x540132, "0x540132", Set.of(KmipSpec.V1_2)),
                        OffsetDateTime.now()),
                TtlvDataType.intervalOf(
                        KmipTag.register(0x540133, "0x540133", Set.of(KmipSpec.V1_2)),
                        3600)
        );
    }
}
