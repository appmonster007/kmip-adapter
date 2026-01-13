package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ContactInformation;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ContactInformation TTLV Serialization Tests")
class ContactInformationTtlvTest extends AbstractTtlvSerializationTestSuite<ContactInformation> {

    @Override
    protected Class<ContactInformation> type() {
        return ContactInformation.class;
    }

    @Override
    protected ContactInformation createDefault() {
        return ContactInformation.builder().value("test").build();
    }

    @Override
    protected ContactInformation createVariant() {
        // TODO: Update with different values to test variations
        return ContactInformation.builder().value("test-2").build();
    }
}
