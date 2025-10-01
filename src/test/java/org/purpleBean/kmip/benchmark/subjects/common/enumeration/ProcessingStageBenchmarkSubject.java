package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.ProcessingStage;

public class ProcessingStageBenchmarkSubject extends KmipBenchmarkSubject<ProcessingStage> {

    public ProcessingStageBenchmarkSubject() throws Exception {
        ProcessingStage processingStage = new ProcessingStage(ProcessingStage.Standard.SUBMITTED);
        initialize(processingStage, ProcessingStage.class);
    }

    @Override
    public String name() {
        return "ProcessingStage";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
