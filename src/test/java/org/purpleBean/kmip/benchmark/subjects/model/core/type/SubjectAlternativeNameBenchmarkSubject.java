package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;

public class SubjectAlternativeNameBenchmarkSubject extends KmipBenchmarkSubject<SubjectAlternativeName> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public SubjectAlternativeNameBenchmarkSubject() throws Exception {
        SubjectAlternativeName subjectAlternativeName = SubjectAlternativeName.of(new byte[]{0x01, 0x02, 0x03});
        initialize(subjectAlternativeName, SubjectAlternativeName.class);
    }

    @Override
    public String name() {
        return "SubjectAlternativeName";
    }

}