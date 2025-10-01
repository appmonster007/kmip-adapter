package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;

public class KeyRoleTypeBenchmarkSubject extends KmipBenchmarkSubject<KeyRoleType> {

    public KeyRoleTypeBenchmarkSubject() throws Exception {
        KeyRoleType keyRoleType = new KeyRoleType(KeyRoleType.Standard.BDK);
        initialize(keyRoleType, KeyRoleType.class);
    }

    @Override
    public String name() {
        return "KeyRoleType";
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
