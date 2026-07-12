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
import org.purpleBean.kmip.model.v2_1.structure.ObjectTypes;

public class ObjectTypesBenchmarkSubject extends KmipBenchmarkSubject<ObjectTypes> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public ObjectTypesBenchmarkSubject() throws Exception {
        ObjectTypes subject = ObjectTypes.of(java.util.List.of());
        initialize(subject, ObjectTypes.class);
    }

    @Override
    public String name() {
        return "ObjectTypes";
    }
}