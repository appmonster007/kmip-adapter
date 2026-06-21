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
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ExportOpResponsePayload;

public class ExportOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<ExportOpResponsePayload> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public ExportOpResponsePayloadBenchmarkSubject() throws Exception {
        ExportOpResponsePayload subject = ExportOpResponsePayload.builder().build();  // TODO: Create a default instance
        initialize(subject, ExportOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "ExportOpResponsePayload";
    }
}