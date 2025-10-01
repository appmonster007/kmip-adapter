package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.Operation;

public class OperationBenchmarkSubject extends KmipBenchmarkSubject<Operation> {

    public OperationBenchmarkSubject() throws Exception {
        Operation operation = new Operation(Operation.Standard.CREATE);
        initialize(operation, Operation.class);
    }

    @Override
    public String name() {
        return "Operation";
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
