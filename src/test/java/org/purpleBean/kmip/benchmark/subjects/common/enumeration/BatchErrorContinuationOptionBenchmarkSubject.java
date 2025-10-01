package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionBenchmarkSubject extends KmipBenchmarkSubject<BatchErrorContinuationOption> {

    public BatchErrorContinuationOptionBenchmarkSubject() throws Exception {
        BatchErrorContinuationOption batchErrorContinuationOption = new BatchErrorContinuationOption(BatchErrorContinuationOption.Standard.CONTINUE);
        initialize(batchErrorContinuationOption, BatchErrorContinuationOption.class);
    }

    @Override
    public String name() {
        return "BatchErrorContinuationOption";
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
