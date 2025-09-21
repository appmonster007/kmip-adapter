package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ProcessingStage;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("ProcessingStage TTLV Serialization")
class ProcessingStageTtlvTest extends AbstractTtlvSerializationSuite<ProcessingStage> {
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
