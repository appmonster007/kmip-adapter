package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DataByteString;

public class DataByteStringBenchmarkSubject extends KmipBenchmarkSubject<DataByteString> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public DataByteStringBenchmarkSubject() throws Exception {
    byte[] data = "test data".getBytes();
    DataByteString dataByteString = DataByteString.of(ByteBuffer.wrap(data));
    initialize(dataByteString, DataByteString.class);
  }

  @Override
  public String name() {
    return "DataByteString";
  }

}
