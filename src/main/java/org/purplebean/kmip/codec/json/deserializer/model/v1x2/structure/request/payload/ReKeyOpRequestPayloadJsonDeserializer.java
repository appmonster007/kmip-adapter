package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.Offset;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.ReKeyOpRequestPayload;

public class ReKeyOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ReKeyOpRequestPayload,
        ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder> {

  public ReKeyOpRequestPayloadJsonDeserializer() {
    super(ReKeyOpRequestPayload.kmipTag, ReKeyOpRequestPayload.encodingType);
  }

  @Override
  protected ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder createBuilder() {
    return ReKeyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.OFFSET -> builder.offset(ctxt.readValue(p, Offset.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ReKeyOpRequestPayload build(
      ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}