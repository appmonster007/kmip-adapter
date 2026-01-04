package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.SubjectAlternativeName;

public class SubjectAlternativeNameBenchmarkSubject extends KmipBenchmarkSubject<SubjectAlternativeName> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public SubjectAlternativeNameBenchmarkSubject() throws Exception {
        SubjectAlternativeName subjectAlternativeName = SubjectAlternativeName.of(new byte[]{0x01, 0x02, 0x03});
        initialize(subjectAlternativeName, SubjectAlternativeName.class);
    }

    @Override
    public String name() {
        return "SubjectAlternativeName";
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