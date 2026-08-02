package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.enumeration.RotateNameType;
import org.purpleBean.kmip.model.v2_1.structure.RotateName;
import org.purpleBean.kmip.model.v2_1.type.RotateNameValue;

public class RotateNameBenchmarkSubject extends KmipBenchmarkSubject<RotateName> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public RotateNameBenchmarkSubject() throws Exception {
        RotateName subject = RotateName.of(
                RotateNameValue.of("default"),
                RotateNameType.Standard.UNINTERPRETED_TEXT_STRING.inst());
        initialize(subject, RotateName.class);
    }

    @Override
    public String name() {
        return "RotateName";
    }
}