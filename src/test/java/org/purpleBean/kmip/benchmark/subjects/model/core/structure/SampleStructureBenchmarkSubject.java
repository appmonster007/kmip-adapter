package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.structure.SampleStructure;
import org.purpleBean.kmip.model.core.type.ActivationDate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class SampleStructureBenchmarkSubject extends KmipBenchmarkSubject<SampleStructure> {

    public SampleStructureBenchmarkSubject() throws Exception {
        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        ActivationDate activationDate = ActivationDate.builder().value(fixed).build();
        State state = State.Standard.ACTIVE.inst();
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
