package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.response;

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
import org.purpleBean.kmip.model.v2_1.structure.response.ResponseMessage;

public class ResponseMessageBenchmarkSubject extends KmipBenchmarkSubject<ResponseMessage> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public ResponseMessageBenchmarkSubject() throws Exception {
        ResponseMessage subject = ResponseMessage.builder().build();
        initialize(subject, ResponseMessage.class);
    }

    @Override
    public String name() {
        return "ResponseMessage";
    }
}