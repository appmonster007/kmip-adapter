package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.AsynchronousCorrelationValues;
import org.purplebean.kmip.model.v2x1.structure.Operations;
import org.purplebean.kmip.model.v2x1.structure.request.payload.QueryAsynchronousRequestsOpRequestPayload;

/**
 * JSON deserializer for {@link QueryAsynchronousRequestsOpRequestPayload}.
 */
public class QueryAsynchronousRequestsOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<QueryAsynchronousRequestsOpRequestPayload,
        QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link QueryAsynchronousRequestsOpRequestPayloadJsonDeserializer}.
   */
  public QueryAsynchronousRequestsOpRequestPayloadJsonDeserializer() {
    super(QueryAsynchronousRequestsOpRequestPayload.kmipTag,
        QueryAsynchronousRequestsOpRequestPayload.encodingType);
  }

  @Override
  protected QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder
      createBuilder() {
    return QueryAsynchronousRequestsOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder builder,
      String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUES ->
          builder.asynchronousCorrelationValues(
              ctxt.readValue(p, AsynchronousCorrelationValues.class));
      case KmipTag.Standard.OPERATIONS -> builder.operations(ctxt.readValue(p, Operations.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected QueryAsynchronousRequestsOpRequestPayload build(
      QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}