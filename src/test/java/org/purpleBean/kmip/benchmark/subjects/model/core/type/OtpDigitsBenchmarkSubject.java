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
import org.purpleBean.kmip.model.core.type.OtpDigits;

public class OtpDigitsBenchmarkSubject extends KmipBenchmarkSubject<OtpDigits> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public OtpDigitsBenchmarkSubject() throws Exception {
        OtpDigits subject = OtpDigits.of(1);  // TODO: Create a default instance
        initialize(subject, OtpDigits.class);
    }

    @Override
    public String name() {
        return "OtpDigits";
    }
}