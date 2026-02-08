package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.IterationCount;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("IterationCount XML Serialization Tests")
class IterationCountXmlTest extends AbstractXmlSerializationTestSuite<IterationCount> {

    @Override
    public Class<IterationCount> type() {
        return IterationCount.class;
    }

    @Override
    public IterationCount createDefault() {
        return IterationCount.builder().value(1000).build();
    }

    @Override
    public IterationCount createVariant() {
        return IterationCount.builder().value(2000).build();
    }
}