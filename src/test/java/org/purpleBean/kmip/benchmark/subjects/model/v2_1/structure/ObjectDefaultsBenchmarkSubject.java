package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v2_1.structure.ObjectDefaults;

import java.util.Collections;

public class ObjectDefaultsBenchmarkSubject extends KmipBenchmarkSubject<ObjectDefaults> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public ObjectDefaultsBenchmarkSubject() throws Exception {
        ObjectDefaults subject = ObjectDefaults.builder()
                .objectType(ObjectType.Standard.CERTIFICATE.inst())
                .attributes(Attributes.of(Collections.emptyList()))
                .build();
        initialize(subject, ObjectDefaults.class);
    }

    @Override
    public String name() {
        return "ObjectDefaults";
    }
}
