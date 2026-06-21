package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

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
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ImportOpRequestPayload;

public class ImportOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<ImportOpRequestPayload> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public ImportOpRequestPayloadBenchmarkSubject() throws Exception {
        ImportOpRequestPayload subject = ImportOpRequestPayload.builder().build();  // TODO: Create a default instance
        initialize(subject, ImportOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "ImportOpRequestPayload";
    }
}