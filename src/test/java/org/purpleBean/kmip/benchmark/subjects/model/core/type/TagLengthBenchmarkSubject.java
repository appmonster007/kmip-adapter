package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.TagLength;

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

}