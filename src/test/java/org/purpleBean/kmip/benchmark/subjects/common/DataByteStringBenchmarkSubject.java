package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.DataByteString;

import java.nio.ByteBuffer;

public class DataByteStringBenchmarkSubject extends KmipBenchmarkSubject<DataByteString> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public DataByteStringBenchmarkSubject() throws Exception {
        byte[] data = "test data".getBytes();
        DataByteString dataByteString = DataByteString.of(ByteBuffer.wrap(data));
        initialize(dataByteString, DataByteString.class);
    }

    @Override
    public String name() {
        return "DataByteString";
    }

    @Override
    public void setup() {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
