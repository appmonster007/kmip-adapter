package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.TagLength;

public class TagLengthBenchmarkSubject extends KmipBenchmarkSubject<TagLength> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TagLengthBenchmarkSubject() throws Exception {
        TagLength tagLength = TagLength.of(128);
        initialize(tagLength, TagLength.class);
    }

    @Override
    public String name() {
        return "TagLength";
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