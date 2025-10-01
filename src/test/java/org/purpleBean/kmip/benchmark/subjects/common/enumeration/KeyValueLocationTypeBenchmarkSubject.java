package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeBenchmarkSubject extends KmipBenchmarkSubject<KeyValueLocationType> {

    public KeyValueLocationTypeBenchmarkSubject() throws Exception {
        KeyValueLocationType keyValueLocationType = new KeyValueLocationType(KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING);
        initialize(keyValueLocationType, KeyValueLocationType.class);
    }

    @Override
    public String name() {
        return "KeyValueLocationType";
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
