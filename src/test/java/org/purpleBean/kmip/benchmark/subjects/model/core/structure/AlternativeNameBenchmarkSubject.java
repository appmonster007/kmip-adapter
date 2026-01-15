package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;
import org.purpleBean.kmip.model.core.structure.AlternativeName;

public class AlternativeNameBenchmarkSubject extends KmipBenchmarkSubject<AlternativeName> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public AlternativeNameBenchmarkSubject() throws Exception {
        AlternativeName alternativename = AlternativeName.builder()
                .alternativeNameValue(AlternativeNameValue.of("SomeAliasName"))
                .alternativeNameType(AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING.inst())
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