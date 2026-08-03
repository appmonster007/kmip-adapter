package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.ResultMessage;

public class ResultMessageBenchmarkSubject extends KmipBenchmarkSubject<ResultMessage> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public ResultMessageBenchmarkSubject() throws Exception {
    ResultMessage resultMessage = ResultMessage
        .builder()
        .value("Success")
        .build();
    initialize(resultMessage, ResultMessage.class);
  }

  @Override
  public String name() {
    return "ResultMessage";
  }

}