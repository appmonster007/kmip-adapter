package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ProcessingStage;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("ProcessingStage XML Serialization")
class ProcessingStageXmlTest extends AbstractXmlSerializationSuite<ProcessingStage> {
    @Override
    protected Class<ProcessingStage> type() {
        return ProcessingStage.class;
    }

    @Override
    protected ProcessingStage createDefault() {
        return new ProcessingStage(ProcessingStage.Standard.SUBMITTED);
    }

    @Override
    protected ProcessingStage createVariant() {
        return new ProcessingStage(ProcessingStage.Standard.IN_PROCESS);
    }
}
