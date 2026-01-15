package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ContactInformation;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ContactInformation XML Serialization Tests")
class ContactInformationXmlTest extends AbstractXmlSerializationTestSuite<ContactInformation> {

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
