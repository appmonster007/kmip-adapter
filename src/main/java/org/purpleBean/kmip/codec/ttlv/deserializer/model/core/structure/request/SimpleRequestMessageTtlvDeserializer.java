package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure.request;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestBatchItemStructure;
import org.purplebean.kmip.api.request.RequestHeaderStructure;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestMessage;

public class SimpleRequestMessageTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SimpleRequestMessage,
        SimpleRequestMessage.SimpleRequestMessageBuilder> {

  public SimpleRequestMessageTtlvDeserializer() {
    super(SimpleRequestMessage.kmipTag, SimpleRequestMessage.encodingType);
  }

  @Override
  protected SimpleRequestMessage.SimpleRequestMessageBuilder createBuilder() {
    return SimpleRequestMessage.builder();
  }

  @Override
  protected void setValue(SimpleRequestMessage.SimpleRequestMessageBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.REQUEST_HEADER ->
          builder.requestHeader(mapper.readValue(p, RequestHeaderStructure.class));
      case KmipTag.Standard.BATCH_ITEM -> {
        try {
          builder.requestBatchItem(mapper.readValue(p, RequestBatchItemStructure.class));
          builder.requestBatchItemError(null);
        } catch (Exception e) {
          builder.requestBatchItem(null);
          builder.requestBatchItemError(e);
        }
      }
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SimpleRequestMessage build(SimpleRequestMessage.SimpleRequestMessageBuilder builder) {
    return builder.build();
  }
}
