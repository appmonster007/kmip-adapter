package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.KeyValueLocationValue;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.common.structure.KeyValueLocation;

public class KeyValueLocationBenchmarkSubject extends KmipBenchmarkSubject<KeyValueLocation> {

    public KeyValueLocationBenchmarkSubject() throws Exception {
        KeyValueLocation keyvaluelocation = KeyValueLocation.builder()
                .keyValueLocationType(KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING.inst())
                .keyValueLocationValue(KeyValueLocationValue.builder().value("test").build())
                .build();
        initialize(keyvaluelocation, KeyValueLocation.class);
    }

    @Override
    public String name() {
        return "KeyValueLocation";
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