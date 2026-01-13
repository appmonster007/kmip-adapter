package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValueInteger;
import org.purpleBean.kmip.common.structure.AttributeValueStructure;

import java.util.List;

public class AttributeValueStructureBenchmarkSubject extends KmipBenchmarkSubject<AttributeValueStructure> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueStructureBenchmarkSubject() throws Exception {
        AttributeValueStructure attributeValueStructure = AttributeValueStructure.of(List.of(AttributeValueInteger.of(123)));
        initialize(attributeValueStructure, AttributeValueStructure.class);
    }

    @Override
    public String name() {
        return "AttributeValueStructure";
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
