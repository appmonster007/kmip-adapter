package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.CertificateLength;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class CertificateLengthBenchmarkSubject extends KmipBenchmarkSubject<CertificateLength> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public CertificateLengthBenchmarkSubject() throws Exception {
        Integer value = 10;
        CertificateLength certificateLength = CertificateLength.builder().value(value).build();
        initialize(certificateLength, CertificateLength.class);
    }

    @Override
    public String name() {
        return "CertificateLength";
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
