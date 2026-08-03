package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.AsynchronousRequest;
import org.purplebean.kmip.model.v2x1.structure.response.payload.QueryAsynchronousRequestsOpResponsePayload;

/**
 * JSON deserializer for {@link QueryAsynchronousRequestsOpResponsePayload}.
 */
public class QueryAsynchronousRequestsOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<QueryAsynchronousRequestsOpResponsePayload,
        QueryAsynchronousRequestsOpResponsePayload.QueryAsynchronousRequestsOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link QueryAsynchronousRequestsOpResponsePayloadJsonDeserializer}.
   */
  public QueryAsynchronousRequestsOpResponsePayloadJsonDeserializer() {
    super(QueryAsynchronousRequestsOpResponsePayload.kmipTag,
        QueryAsynchronousRequestsOpResponsePayload.encodingType);
  }

  @Override
  protected QueryAsynchronousRequestsOpResponsePayload.QueryAsynchronousRequestsOpResponsePayloadBuilder
      createBuilder() {
    return QueryAsynchronousRequestsOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      QueryAsynchronousRequestsOpResponsePayload.QueryAsynchronousRequestsOpResponsePayloadBuilder builder,
      String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ASYNCHRONOUS_REQUEST ->
          builder.asynchronousRequest(ctxt.readValue(p, AsynchronousRequest.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected QueryAsynchronousRequestsOpResponsePayload build(
      QueryAsynchronousRequestsOpResponsePayload.QueryAsynchronousRequestsOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}