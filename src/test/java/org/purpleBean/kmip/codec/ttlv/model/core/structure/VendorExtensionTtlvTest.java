package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.structure.VendorExtension;
import org.purpleBean.kmip.model.core.type.vendor.TtlvDataType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("VendorExtension Ttlv Serialization Tests")
class VendorExtensionTtlvTest extends AbstractTtlvSerializationTestSuite<VendorExtension> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<VendorExtension> type() {
        return VendorExtension.class;
    }

    @Override
    protected VendorExtension createDefault() {
        return VendorExtension.builder()
                .ttlvDataType(TtlvDataType.builder()
                        .kmipTag(KmipTag.Standard.UNIQUE_IDENTIFIER.inst())
                        .encodingType(EncodingType.TEXT_STRING)
                        .value("test-value")
                        .build())
                .build();
    }

    @Override
    protected VendorExtension createVariant() {
        return VendorExtension.builder()
                .ttlvDataType(TtlvDataType.builder()
                        .kmipTag(KmipTag.Standard.ATTRIBUTE_NAME.inst())
                        .encodingType(EncodingType.TEXT_STRING)
                        .value("variant-value")
                        .build())
                .build();
    }
}
