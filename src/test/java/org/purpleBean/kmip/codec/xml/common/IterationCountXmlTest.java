package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.IterationCount;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("IterationCount XML Serialization Tests")
class IterationCountXmlTest extends AbstractXmlSerializationSuite<IterationCount> {

    @Override
    protected Class<IterationCount> type() {
        return IterationCount.class;
    }

    @Override
    protected IterationCount createDefault() {
        return IterationCount.builder().value(1000).build();
    }

    @Override
    protected IterationCount createVariant() {
        return IterationCount.builder().value(2000).build();
    }
}