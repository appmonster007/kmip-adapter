package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.ObjectType;

public class ObjectTypeBenchmarkSubject extends KmipBenchmarkSubject<ObjectType> {

    public ObjectTypeBenchmarkSubject() throws Exception {
        ObjectType objectType = new ObjectType(ObjectType.Standard.CERTIFICATE);
        initialize(objectType, ObjectType.class);
    }

    @Override
    public String name() {
        return "ObjectType";
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
