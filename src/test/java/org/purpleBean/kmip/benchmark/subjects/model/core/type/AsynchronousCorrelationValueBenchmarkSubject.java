package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;

public class AsynchronousCorrelationValueBenchmarkSubject extends KmipBenchmarkSubject<AsynchronousCorrelationValue> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public AsynchronousCorrelationValueBenchmarkSubject() throws Exception {
        AsynchronousCorrelationValue asynchronousCorrelationValue = AsynchronousCorrelationValue.of(new byte[]{0x01, 0x02, 0x03});
        initialize(asynchronousCorrelationValue, AsynchronousCorrelationValue.class);
    }

    @Override
    public String name() {
        return "AsynchronousCorrelationValue";
    }

}