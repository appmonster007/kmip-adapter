package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("DrbgAlgorithm XML Serialization")
class DrbgAlgorithmXmlTest extends AbstractXmlSerializationSuite<DrbgAlgorithm> {
    @Override
    protected Class<DrbgAlgorithm> type() {
        return DrbgAlgorithm.class;
    }

    @Override
    protected DrbgAlgorithm createDefault() {
        return new DrbgAlgorithm(DrbgAlgorithm.Standard.UNSPECIFIED);
    }

    @Override
    protected DrbgAlgorithm createVariant() {
        return new DrbgAlgorithm(DrbgAlgorithm.Standard.DUAL_EC);
    }
}
