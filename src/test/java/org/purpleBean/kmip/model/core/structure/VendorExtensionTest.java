package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.type.vendor.TtlvDataType;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("VendorExtension Domain Tests")
class VendorExtensionTest extends AbstractKmipStructureTestSuite<VendorExtension> {

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
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 1;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSize(1);
        assertThat(values.get(0)).isInstanceOf(TtlvDataType.class);
    }
}
