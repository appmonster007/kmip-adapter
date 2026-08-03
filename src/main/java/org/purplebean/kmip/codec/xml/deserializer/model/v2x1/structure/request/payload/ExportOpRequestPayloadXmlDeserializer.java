package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ExportOpRequestPayload;

public class ExportOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ExportOpRequestPayload,
        ExportOpRequestPayload.ExportOpRequestPayloadBuilder> {

  public ExportOpRequestPayloadXmlDeserializer() {
    super(ExportOpRequestPayload.kmipTag, ExportOpRequestPayload.encodingType);
  }

  @Override
  protected ExportOpRequestPayload.ExportOpRequestPayloadBuilder createBuilder() {
    return ExportOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ExportOpRequestPayload.ExportOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.KEY_WRAPPING_SPECIFICATION ->
          builder.keyWrappingSpecification(ctxt.readValue(p, KeyWrappingSpecification.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ExportOpRequestPayload build(
      ExportOpRequestPayload.ExportOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}