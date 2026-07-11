package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.Attributes;
import org.purpleBean.kmip.model.core.structure.ObjectDefaults;

import java.util.Collections;

public class ObjectDefaultsBenchmarkSubject extends KmipBenchmarkSubject<ObjectDefaults> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public ObjectDefaultsBenchmarkSubject() throws Exception {
        ObjectDefaults subject = ObjectDefaults.of(
                ObjectType.Standard.SYMMETRIC_KEY.inst(),
                Attributes.of(Collections.emptyList())
        );
        initialize(subject, ObjectDefaults.class);
    }

    @Override
    public String name() {
        return "ObjectDefaults";
    }
}
