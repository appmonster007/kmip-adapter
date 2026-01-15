package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;

import java.util.Set;

public class OpaqueDataTypeBenchmarkSubject extends KmipBenchmarkSubject<OpaqueDataType> {

    public OpaqueDataTypeBenchmarkSubject() throws Exception {
        OpaqueDataType opaqueDataType = new OpaqueDataType(OpaqueDataType.register(0x80000000, "Custom", Set.of(KmipSpec.UnknownVersion)));
        initialize(opaqueDataType, OpaqueDataType.class);
    }

    @Override
    public String name() {
        return "OpaqueDataType";
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
