package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.CertificateValue;

public class CertificateValueBenchmarkSubject extends KmipBenchmarkSubject<CertificateValue> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public CertificateValueBenchmarkSubject() throws Exception {
        CertificateValue certificateValue = CertificateValue.of(new byte[]{0x01, 0x02, 0x03});
        initialize(certificateValue, CertificateValue.class);
    }

    @Override
    public String name() {
        return "CertificateValue";
    }

}