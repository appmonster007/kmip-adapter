package org.purplebean.kmip.codec.json.deserializer.model.core.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestBatchItem;

/**
 * JSON deserializer for {@link SimpleRequestBatchItem}.
 */
public class SimpleRequestBatchItemJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SimpleRequestBatchItem,
        SimpleRequestBatchItem.SimpleRequestBatchItemBuilder> {

  /**
   * Constructs a new {@link SimpleRequestBatchItemJsonDeserializer}.
   */
  public SimpleRequestBatchItemJsonDeserializer() {
    super(SimpleRequestBatchItem.kmipTag, SimpleRequestBatchItem.encodingType);
  }

  @Override
  protected SimpleRequestBatchItem.SimpleRequestBatchItemBuilder createBuilder() {
    return SimpleRequestBatchItem.builder();
  }

  @Override
  protected void setValue(SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    // This structure is a wrapper, the logic is in the parent deserializer
    builder.requestPayloadStructure(ctxt.readValue(p, RequestPayloadStructure.class));
  }

  @Override
  protected SimpleRequestBatchItem build(
      SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder) {
    return builder.build();
  }
}