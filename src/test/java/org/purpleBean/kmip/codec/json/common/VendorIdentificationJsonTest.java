package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.VendorIdentification;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("VendorIdentification JSON Serialization Tests")
class VendorIdentificationJsonTest extends AbstractJsonSerializationSuite<VendorIdentification> {

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