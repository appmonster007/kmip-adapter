package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.DataLength;

public class DataLengthBenchmarkSubject extends KmipBenchmarkSubject<DataLength> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public DataLengthBenchmarkSubject() throws Exception {
        DataLength dataLength = DataLength.of(128);
        initialize(dataLength, DataLength.class);
    }

    @Override
    public String name() {
        return "DataLength";
    }

}
