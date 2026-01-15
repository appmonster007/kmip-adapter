package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ProcessingStage;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProcessingStage XML Serialization")
class ProcessingStageXmlTest extends AbstractXmlSerializationTestSuite<ProcessingStage> {
    @Override
    protected Class<ProcessingStage> type() {
        return ProcessingStage.class;
    }

    @Override
    protected ProcessingStage createDefault() {
        return ProcessingStage.Standard.SUBMITTED.inst();
    }

    @Override
    protected ProcessingStage createVariant() {
        return ProcessingStage.Standard.IN_PROCESS.inst();
    }
}
