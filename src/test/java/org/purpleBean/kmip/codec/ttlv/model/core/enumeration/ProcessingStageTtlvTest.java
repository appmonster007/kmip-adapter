package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ProcessingStage;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ProcessingStage TTLV Serialization")
class ProcessingStageTtlvTest extends AbstractTtlvSerializationTestSuite<ProcessingStage> {
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
