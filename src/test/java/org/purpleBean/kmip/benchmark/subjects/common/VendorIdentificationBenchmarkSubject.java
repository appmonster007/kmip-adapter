package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.VendorIdentification;

public class VendorIdentificationBenchmarkSubject extends KmipBenchmarkSubject<VendorIdentification> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public VendorIdentificationBenchmarkSubject() throws Exception {
        VendorIdentification vendorIdentification = VendorIdentification.builder().value("test-vendor").build();
        initialize(vendorIdentification, VendorIdentification.class);
    }

    @Override
    public String name() {
        return "VendorIdentification";
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