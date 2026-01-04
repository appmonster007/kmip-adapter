package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("ApplicationNamespace Domain Tests")
class ApplicationNamespaceTest extends AbstractKmipDataTypeSuite<ApplicationNamespace> {

    @Override
    protected Class<ApplicationNamespace> type() {
        return ApplicationNamespace.class;
    }

    @Override
    protected ApplicationNamespace createDefault() {
        return ApplicationNamespace.builder().value("test-namespace").build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}