package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.ProtectionLevel;

public class ProtectionLevelBenchmarkSubject extends KmipBenchmarkSubject<ProtectionLevel> {

    public ProtectionLevelBenchmarkSubject() throws Exception {
        ProtectionLevel protectionLevel = new ProtectionLevel(ProtectionLevel.Standard.HIGH);
        initialize(protectionLevel, ProtectionLevel.class);
    }

    @Override
    public String name() {
        return "ProtectionLevel";
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
