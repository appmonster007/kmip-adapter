package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.AttributeValueStructure;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;

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

}
