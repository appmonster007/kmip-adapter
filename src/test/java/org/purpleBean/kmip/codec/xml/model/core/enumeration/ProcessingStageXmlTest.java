package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ProcessingStage;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProcessingStage XML Serialization")
class ProcessingStageXmlTest extends AbstractXmlSerializationTestSuite<ProcessingStage> {
    @Override
    public Class<ProcessingStage> type() {
        return ProcessingStage.class;
    }

    @Override
    public ProcessingStage createDefault() {
        return ProcessingStage.Standard.SUBMITTED.inst();
    }

    @Override
    public ProcessingStage createVariant() {
        return ProcessingStage.Standard.IN_PROCESS.inst();
    }
}
