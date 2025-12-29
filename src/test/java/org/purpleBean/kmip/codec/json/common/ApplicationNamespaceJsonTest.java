package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ApplicationNamespace;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("ApplicationNamespace JSON Serialization Tests")
class ApplicationNamespaceJsonTest extends AbstractJsonSerializationSuite<ApplicationNamespace> {

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