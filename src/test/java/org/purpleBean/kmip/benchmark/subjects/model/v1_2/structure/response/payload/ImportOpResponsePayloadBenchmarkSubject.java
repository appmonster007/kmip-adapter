package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.response.payload;

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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ImportOpResponsePayload;

public class ImportOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<ImportOpResponsePayload> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public ImportOpResponsePayloadBenchmarkSubject() throws Exception {
        ImportOpResponsePayload subject = ImportOpResponsePayload.of(UniqueIdentifier.builder().value("test-uid-1").build());
        initialize(subject, ImportOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "ImportOpResponsePayload";
    }
}