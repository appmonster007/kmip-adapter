package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ContactInformation;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("ContactInformation XML Serialization Tests")
class ContactInformationXmlTest extends AbstractXmlSerializationSuite<ContactInformation> {

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
        return ContactInformation.builder().value("test-2").build();
    }
}
