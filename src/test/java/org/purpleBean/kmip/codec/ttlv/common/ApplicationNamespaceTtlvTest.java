package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ApplicationNamespace;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("ApplicationNamespace TTLV Serialization Tests")
class ApplicationNamespaceTtlvTest extends AbstractTtlvSerializationSuite<ApplicationNamespace> {

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