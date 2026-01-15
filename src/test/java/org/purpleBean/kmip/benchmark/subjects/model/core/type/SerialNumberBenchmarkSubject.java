package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.SerialNumber;

public class SerialNumberBenchmarkSubject extends KmipBenchmarkSubject<SerialNumber> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public SerialNumberBenchmarkSubject() throws Exception {
        SerialNumber serialNumber = SerialNumber.builder().value("12345").build();
        initialize(serialNumber, SerialNumber.class);
    }

    @Override
    public String name() {
        return "SerialNumber";
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