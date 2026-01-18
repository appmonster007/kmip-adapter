package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ObjectClass;

public class ObjectClassBenchmarkSubject extends KmipBenchmarkSubject<ObjectClass> {

    public ObjectClassBenchmarkSubject() throws Exception {
        ObjectClass objectClass = ObjectClass.Standard.USER.inst();
        initialize(objectClass, ObjectClass.class);
    }

    @Override
    public String name() {
        return "ObjectClass";
    }

}
