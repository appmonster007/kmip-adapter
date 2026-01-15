package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.VendorIdentification;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("VendorIdentification TTLV Serialization Tests")
class VendorIdentificationTtlvTest extends AbstractTtlvSerializationTestSuite<VendorIdentification> {

    @Override
    protected Class<VendorIdentification> type() {
        return VendorIdentification.class;
    }

    @Override
    protected VendorIdentification createDefault() {
        return VendorIdentification.builder().value("test-vendor").build();
    }

    @Override
    protected VendorIdentification createVariant() {
        return VendorIdentification.builder().value("another-vendor").build();
    }
}