package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.Username;

/**
 * Benchmark subject for {@link Username}.
 */
public class UsernameBenchmarkSubject extends KmipBenchmarkSubject<Username> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link UsernameBenchmarkSubject}.
   */
  public UsernameBenchmarkSubject() throws Exception {
    Username username = Username
        .builder()
        .value("test-user")
        .build();
    initialize(username, Username.class);
  }

  @Override
  public String name() {
    return "Username";
  }

}