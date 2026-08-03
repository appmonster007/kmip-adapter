package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure.request;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestBatchItem;

public class SimpleRequestBatchItemTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SimpleRequestBatchItem,
        SimpleRequestBatchItem.SimpleRequestBatchItemBuilder> {

  public SimpleRequestBatchItemTtlvDeserializer() {
    super(SimpleRequestBatchItem.kmipTag, SimpleRequestBatchItem.encodingType);
  }

  @Override
  protected SimpleRequestBatchItem.SimpleRequestBatchItemBuilder createBuilder() {
    return SimpleRequestBatchItem.builder();
  }

  @Override
  protected void setValue(SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    // This structure is a wrapper, the logic is in the parent deserializer
    builder.requestPayloadStructure(mapper.readValue(p, RequestPayloadStructure.class));
  }

  @Override
  protected SimpleRequestBatchItem build(
      SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder) {
    return builder.build();
  }
}
