package org.purpleBean.kmip.benchmark.subjects.model.core.type.vendor;

import lombok.Getter;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.vendor.TtlvDataType;

import java.util.Set;

public class TtlvDataTypeBenchmarkSubject extends KmipBenchmarkSubject<TtlvDataType> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TtlvDataTypeBenchmarkSubject() throws Exception {
        var extension = KmipTag.register(0x540123, "0x540123", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
        TtlvDataType subject = TtlvDataType.builder()
                .kmipTag(extension.inst())
                .encodingType(EncodingType.TEXT_STRING)
                .value("test-value")
                .build();
        initialize(subject, TtlvDataType.class);
    }

    @Override
    public String name() {
        return "TtlvDataType";
    }
}
