package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.QString;

public class QStringBenchmarkSubject extends KmipBenchmarkSubject<QString> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public QStringBenchmarkSubject() throws Exception {
    QString qString = QString.of("test-qstring".getBytes());
    initialize(qString, QString.class);
  }

  @Override
  public String name() {
    return "QString";
  }

}