package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.ActivationDate;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.structure.KeyMaterialStructure;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class KeyMaterialStructureBenchmarkSubject extends KmipBenchmarkSubject<KeyMaterialStructure> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public KeyMaterialStructureBenchmarkSubject() throws Exception {
        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        ActivationDate activationDate = ActivationDate.builder().value(fixed).build();
        State state = State.Standard.ACTIVE.inst();
        KeyMaterialStructure keyMaterialStructure = KeyMaterialStructure.of(List.of(activationDate, state));
        initialize(keyMaterialStructure, KeyMaterialStructure.class);
    }

    @Override
    public String name() {
        return "KeyMaterialStructure";
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