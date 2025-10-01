package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.NameValue;
import org.purpleBean.kmip.common.enumeration.NameType;
import org.purpleBean.kmip.common.structure.Name;

public class NameBenchmarkSubject extends KmipBenchmarkSubject<Name> {

    public NameBenchmarkSubject() throws Exception {
        Name name = Name.builder()
                .nameValue(NameValue.of("some-name"))
                .nameType(new NameType(NameType.Standard.UNINTERPRETED_TEXT_STRING))
                .build();
        initialize(name, Name.class);
    }

    @Override
    public String name() {
        return "Name";
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
