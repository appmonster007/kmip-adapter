package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class SampleStructureBenchmarkSubject extends KmipBenchmarkSubject<SampleStructure> {

    public SampleStructureBenchmarkSubject() throws Exception {
        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        ActivationDate activationDate = ActivationDate.builder().value(fixed).build();
        State state = new State(State.Standard.ACTIVE);
        SampleStructure sampleStructure = SampleStructure.builder()
                .activationDate(activationDate)
                .state(state)
                .build();
        initialize(sampleStructure, SampleStructure.class);
    }

    @Override
    public String name() {
        return "SampleStructure";
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
