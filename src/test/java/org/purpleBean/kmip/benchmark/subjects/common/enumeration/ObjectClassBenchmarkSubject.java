package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.ObjectClass;

public class ObjectClassBenchmarkSubject extends KmipBenchmarkSubject<ObjectClass> {

    public ObjectClassBenchmarkSubject() throws Exception {
        ObjectClass objectClass = new ObjectClass(ObjectClass.Standard.USER);
        initialize(objectClass, ObjectClass.class);
    }

    @Override
    public String name() {
        return "ObjectClass";
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
