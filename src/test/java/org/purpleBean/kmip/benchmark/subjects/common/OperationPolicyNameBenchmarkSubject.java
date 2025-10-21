package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.OperationPolicyName;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class OperationPolicyNameBenchmarkSubject extends KmipBenchmarkSubject<OperationPolicyName> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public OperationPolicyNameBenchmarkSubject() throws Exception {
        OperationPolicyName operationPolicyName = OperationPolicyName.builder().value("test").build();
        initialize(operationPolicyName, OperationPolicyName.class);
    }

    @Override
    public String name() {
        return "OperationPolicyName";
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
