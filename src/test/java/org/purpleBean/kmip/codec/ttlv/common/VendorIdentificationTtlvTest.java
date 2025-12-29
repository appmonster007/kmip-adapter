package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.VendorIdentification;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("VendorIdentification TTLV Serialization Tests")
class VendorIdentificationTtlvTest extends AbstractTtlvSerializationSuite<VendorIdentification> {

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