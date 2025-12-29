package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.VendorIdentification;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("VendorIdentification XML Serialization Tests")
class VendorIdentificationXmlTest extends AbstractXmlSerializationSuite<VendorIdentification> {

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