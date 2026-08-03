package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.ProcessingStage;

public class ProcessingStageTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProcessingStage, ProcessingStage.ProcessingStageBuilder> {

  public ProcessingStageTtlvDeserializer() {
    super(ProcessingStage.kmipTag, ProcessingStage.encodingType);
  }

  @Override
  protected ProcessingStage.ProcessingStageBuilder createBuilder() {
    return ProcessingStage.builder();
  }

  @Override
  protected void setValue(ProcessingStage.ProcessingStageBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(ProcessingStage.fromValue(value));
  }

  @Override
  protected ProcessingStage build(ProcessingStage.ProcessingStageBuilder builder) {
    return builder.build();
  }
}
