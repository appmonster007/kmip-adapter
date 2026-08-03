package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.MediaIdentifier;

public class MediaIdentifierBenchmarkSubject extends KmipBenchmarkSubject<MediaIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public MediaIdentifierBenchmarkSubject() throws Exception {
    MediaIdentifier mediaIdentifier = MediaIdentifier
        .builder()
        .value("test-media-id")
        .build();
    initialize(mediaIdentifier, MediaIdentifier.class);
  }

  @Override
  public String name() {
    return "MediaIdentifier";
  }

}