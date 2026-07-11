package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.Pkcs11Interface;

public class Pkcs11InterfaceBenchmarkSubject extends KmipBenchmarkSubject<Pkcs11Interface> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public Pkcs11InterfaceBenchmarkSubject() throws Exception {
        Pkcs11Interface subject = Pkcs11Interface.of("default-string");
        initialize(subject, Pkcs11Interface.class);
    }

    @Override
    public String name() {
        return "Pkcs11Interface";
    }
}