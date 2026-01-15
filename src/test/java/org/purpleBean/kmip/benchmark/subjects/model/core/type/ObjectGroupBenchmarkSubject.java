package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.ObjectGroup;

public class ObjectGroupBenchmarkSubject extends KmipBenchmarkSubject<ObjectGroup> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public ObjectGroupBenchmarkSubject() throws Exception {
        ObjectGroup objectGroup = ObjectGroup.builder().value("test").build();
        initialize(objectGroup, ObjectGroup.class);
    }

    @Override
    public String name() {
        return "ObjectGroup";
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
