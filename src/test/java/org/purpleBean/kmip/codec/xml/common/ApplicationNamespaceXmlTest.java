package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ApplicationNamespace;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ApplicationNamespace XML Serialization Tests")
class ApplicationNamespaceXmlTest extends AbstractXmlSerializationTestSuite<ApplicationNamespace> {

    @Override
    protected Class<ApplicationNamespace> type() {
        return ApplicationNamespace.class;
    }

    @Override
    protected ApplicationNamespace createDefault() {
        return ApplicationNamespace.builder().value("test-namespace").build();
    }

    @Override
    protected ApplicationNamespace createVariant() {
        return ApplicationNamespace.builder().value("another-namespace").build();
    }
}