package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ProcessingStage;

public class ProcessingStageBenchmarkSubject extends KmipBenchmarkSubject<ProcessingStage> {

    public ProcessingStageBenchmarkSubject() throws Exception {
        ProcessingStage processingStage = ProcessingStage.Standard.SUBMITTED.inst();
        initialize(processingStage, ProcessingStage.class);
    }

    @Override
    public String name() {
        return "ProcessingStage";
    }

}
