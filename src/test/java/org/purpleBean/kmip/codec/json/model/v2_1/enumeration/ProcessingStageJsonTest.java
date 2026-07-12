package org.purpleBean.kmip.codec.json.model.v2_1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.enumeration.ProcessingStage;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ProcessingStage JSON Serialization")
class ProcessingStageJsonTest extends AbstractJsonSerializationTestSuite<ProcessingStage> {
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
