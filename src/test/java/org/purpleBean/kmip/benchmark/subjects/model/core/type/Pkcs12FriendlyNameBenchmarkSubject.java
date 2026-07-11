package org.purpleBean.kmip.benchmark.subjects.model.core.type;

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
import org.purpleBean.kmip.model.core.type.Pkcs12FriendlyName;

public class Pkcs12FriendlyNameBenchmarkSubject extends KmipBenchmarkSubject<Pkcs12FriendlyName> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public Pkcs12FriendlyNameBenchmarkSubject() throws Exception {
        Pkcs12FriendlyName subject = Pkcs12FriendlyName.of("default-string");
        initialize(subject, Pkcs12FriendlyName.class);
    }

    @Override
    public String name() {
        return "Pkcs12FriendlyName";
    }
}