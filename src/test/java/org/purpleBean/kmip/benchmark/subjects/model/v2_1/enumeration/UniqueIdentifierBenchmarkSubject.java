package org.purpleBean.kmip.benchmark.subjects.model.v2_1.enumeration;

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
import org.purpleBean.kmip.model.v2_1.enumeration.UniqueIdentifier;

public class UniqueIdentifierBenchmarkSubject extends KmipBenchmarkSubject<UniqueIdentifier> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1; // TODO: Adjust if needed

    public UniqueIdentifierBenchmarkSubject() throws Exception {
        UniqueIdentifier subject = UniqueIdentifier.Standard.values()[0].inst();  // TODO: Create a default instance
        initialize(subject, UniqueIdentifier.class);
    }

    @Override
    public String name() {
        return "UniqueIdentifier";
    }
}