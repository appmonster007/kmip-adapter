package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ObjectGroup;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ObjectGroupBenchmarkSubject extends KmipBenchmarkSubject<ObjectGroup> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

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
