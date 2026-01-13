package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AlternativeNameValue;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;
import org.purpleBean.kmip.common.structure.AlternativeName;

public class AlternativeNameBenchmarkSubject extends KmipBenchmarkSubject<AlternativeName> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public AlternativeNameBenchmarkSubject() throws Exception {
        AlternativeName alternativename = AlternativeName.builder()
                .alternativeNameValue(AlternativeNameValue.of("SomeAliasName"))
                .alternativeNameType(new AlternativeNameType(AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING))
                .build();
        initialize(alternativename, AlternativeName.class);
    }

    @Override
    public String name() {
        return "AlternativeName";
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