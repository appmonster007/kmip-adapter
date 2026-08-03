package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.ArchiveDate;

public class ArchiveDateBenchmarkSubject extends KmipBenchmarkSubject<ArchiveDate> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public ArchiveDateBenchmarkSubject() throws Exception {
    var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    ArchiveDate archiveDate = ArchiveDate
        .builder()
        .value(fixed)
        .build();
    initialize(archiveDate, ArchiveDate.class);
  }

  @Override
  public String name() {
    return "ArchiveDate";
  }

}
