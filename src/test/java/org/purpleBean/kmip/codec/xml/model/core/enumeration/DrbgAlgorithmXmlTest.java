package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.DrbgAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DrbgAlgorithm XML Serialization")
class DrbgAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<DrbgAlgorithm> {
    @Override
    public Class<DrbgAlgorithm> type() {
        return DrbgAlgorithm.class;
    }

    @Override
    public DrbgAlgorithm createDefault() {
        return DrbgAlgorithm.Standard.UNSPECIFIED.inst();
    }

    @Override
    public DrbgAlgorithm createVariant() {
        return DrbgAlgorithm.Standard.DUAL_EC.inst();
    }
}
