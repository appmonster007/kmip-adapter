package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

public class ObjectTypeBenchmarkSubject extends KmipBenchmarkSubject<ObjectType> {

    public ObjectTypeBenchmarkSubject() throws Exception {
        ObjectType objectType = ObjectType.Standard.CERTIFICATE.inst();
        initialize(objectType, ObjectType.class);
    }

    @Override
    public String name() {
        return "ObjectType";
    }

}
