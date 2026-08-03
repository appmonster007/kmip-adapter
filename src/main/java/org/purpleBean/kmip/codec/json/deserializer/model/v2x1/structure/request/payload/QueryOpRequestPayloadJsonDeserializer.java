package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.QueryFunction;
import org.purplebean.kmip.model.v2x1.structure.ObjectGroups;
import org.purplebean.kmip.model.v2x1.structure.request.payload.QueryOpRequestPayload;

public class QueryOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<QueryOpRequestPayload,
        QueryOpRequestPayload.QueryOpRequestPayloadBuilder> {

  public QueryOpRequestPayloadJsonDeserializer() {
    super(QueryOpRequestPayload.kmipTag, QueryOpRequestPayload.encodingType);
  }

  @Override
  protected QueryOpRequestPayload.QueryOpRequestPayloadBuilder createBuilder() {
    return QueryOpRequestPayload.builder();
  }

  @Override
  protected void setValue(QueryOpRequestPayload.QueryOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.QUERY_FUNCTION ->
          builder.queryFunction(ctxt.readValue(p, QueryFunction.class));
      case KmipTag.Standard.OBJECT_GROUPS ->
          builder.objectGroups(ctxt.readValue(p, ObjectGroups.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected QueryOpRequestPayload build(
      QueryOpRequestPayload.QueryOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}