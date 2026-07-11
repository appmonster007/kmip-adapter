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
import org.purpleBean.kmip.model.core.type.Pkcs11ReturnCode;

public class Pkcs11ReturnCodeBenchmarkSubject extends KmipBenchmarkSubject<Pkcs11ReturnCode> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public Pkcs11ReturnCodeBenchmarkSubject() throws Exception {
        Pkcs11ReturnCode subject = Pkcs11ReturnCode.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
        initialize(subject, Pkcs11ReturnCode.class);
    }

    @Override
    public String name() {
        return "Pkcs11ReturnCode";
    }
}