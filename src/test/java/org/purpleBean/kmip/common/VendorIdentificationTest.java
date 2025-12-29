package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("VendorIdentification Domain Tests")
class VendorIdentificationTest extends AbstractKmipDataTypeSuite<VendorIdentification> {

    @Override
    protected Class<VendorIdentification> type() {
        return VendorIdentification.class;
    }

    @Override
    protected VendorIdentification createDefault() {
        return VendorIdentification.builder().value("test-vendor").build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}