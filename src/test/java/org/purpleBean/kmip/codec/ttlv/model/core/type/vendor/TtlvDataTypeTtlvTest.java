package org.purpleBean.kmip.codec.ttlv.model.core.type.vendor;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.type.vendor.TtlvDataType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.util.Set;

@DisplayName("TtlvDataType Ttlv Serialization Tests")
class TtlvDataTypeTtlvTest extends AbstractTtlvSerializationTestSuite<TtlvDataType> {

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
        return TtlvDataType.builder()
                .kmipTag(KmipTag.Standard.ATTRIBUTE_NAME.inst())
                .encodingType(EncodingType.TEXT_STRING)
                .value("variant-value")
                .build();
    }
}
