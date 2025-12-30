package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValue;

import java.util.List;

public class AttributeValueStructureBenchmarkSubject extends KmipBenchmarkSubject<AttributeValue.Structure> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueStructureBenchmarkSubject() throws Exception {
        AttributeValue.Structure attributeValueStructure = AttributeValue.Structure.of(List.of(AttributeValue.Integer.of(123)));
        initialize(attributeValueStructure, AttributeValue.Structure.class);
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
