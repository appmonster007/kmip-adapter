package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.structure.AsynchronousRequest;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.QueryAsynchronousRequestsOpResponsePayload;

public class QueryAsynchronousRequestsOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<QueryAsynchronousRequestsOpResponsePayload,
        QueryAsynchronousRequestsOpResponsePayload.QueryAsynchronousRequestsOpResponsePayloadBuilder> {

  public QueryAsynchronousRequestsOpResponsePayloadXmlDeserializer() {
    super(QueryAsynchronousRequestsOpResponsePayload.kmipTag,
        QueryAsynchronousRequestsOpResponsePayload.encodingType);
  }

  @Override
  protected QueryAsynchronousRequestsOpResponsePayload.QueryAsynchronousRequestsOpResponsePayloadBuilder createBuilder() {
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