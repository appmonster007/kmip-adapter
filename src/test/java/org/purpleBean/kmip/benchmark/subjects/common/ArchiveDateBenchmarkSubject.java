package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ArchiveDate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ArchiveDateBenchmarkSubject extends KmipBenchmarkSubject<ArchiveDate> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public ArchiveDateBenchmarkSubject() throws Exception {
        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        ArchiveDate archiveDate = ArchiveDate.builder().value(fixed).build();
        initialize(archiveDate, ArchiveDate.class);
    }

    @Override
    public String name() {
        return "ArchiveDate";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
