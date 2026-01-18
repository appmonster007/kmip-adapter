package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.Operation;

public class OperationBenchmarkSubject extends KmipBenchmarkSubject<Operation> {

    public OperationBenchmarkSubject() throws Exception {
        Operation operation = Operation.Standard.CREATE.inst();
        initialize(operation, Operation.class);
    }

    @Override
    public String name() {
        return "Operation";
    }

}
