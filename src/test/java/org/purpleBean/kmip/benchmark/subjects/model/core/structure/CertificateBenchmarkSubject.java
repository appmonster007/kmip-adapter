package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;
import org.purpleBean.kmip.model.core.structure.Certificate;
import org.purpleBean.kmip.model.core.type.CertificateValue;

public class CertificateBenchmarkSubject extends KmipBenchmarkSubject<Certificate> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public CertificateBenchmarkSubject() throws Exception {
        Certificate subject = Certificate.builder()
                .certificateType(CertificateType.Standard.X_509.inst())
                .certificateValue(CertificateValue.of(new byte[0]))
                .build();
        initialize(subject, Certificate.class);
    }

    @Override
    public String name() {
        return "Certificate";
    }

}