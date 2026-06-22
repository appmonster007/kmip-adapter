package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.link;

import lombok.Getter;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3_0.structure.link.Pkcs12CertificateLink;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class Pkcs12CertificateLinkBenchmarkSubject extends KmipBenchmarkSubject<Pkcs12CertificateLink> {

    @Getter
    private KmipSpec spec = KmipSpec.V3_0;

    public Pkcs12CertificateLinkBenchmarkSubject() throws Exception {
        Pkcs12CertificateLink subject = Pkcs12CertificateLink.of(UniqueIdentifier.of("test-id"));
        initialize(subject, Pkcs12CertificateLink.class);
    }

    @Override
    public String name() {
        return "Pkcs12CertificateLink";
    }
}