package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;

public class SubjectDistinguishedNameBenchmarkSubject extends KmipBenchmarkSubject<SubjectDistinguishedName> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public SubjectDistinguishedNameBenchmarkSubject() throws Exception {
        SubjectDistinguishedName subjectDistinguishedName = SubjectDistinguishedName.of(new byte[]{0x01, 0x02, 0x03});
        initialize(subjectDistinguishedName, SubjectDistinguishedName.class);
    }

    @Override
    public String name() {
        return "SubjectDistinguishedName";
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