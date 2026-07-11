package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

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
import org.purpleBean.kmip.model.core.enumeration.Pkcs11Function;

public class Pkcs11FunctionBenchmarkSubject extends KmipBenchmarkSubject<Pkcs11Function> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public Pkcs11FunctionBenchmarkSubject() throws Exception {
        Pkcs11Function subject = Pkcs11Function.Standard.values()[0].inst();
        initialize(subject, Pkcs11Function.class);
    }

    @Override
    public String name() {
        return "Pkcs11Function";
    }
}