package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.BatchOrderOption;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("BatchOrderOption XML Serialization Tests")
class BatchOrderOptionXmlTest extends AbstractXmlSerializationTestSuite<BatchOrderOption> {

    @Override
    protected Class<BatchOrderOption> type() {
        return BatchOrderOption.class;
    }

    @Override
    protected BatchOrderOption createDefault() {
        return BatchOrderOption.builder().value(true).build();
    }

    @Override
    protected BatchOrderOption createVariant() {
        return BatchOrderOption.builder().value(false).build();
    }
}