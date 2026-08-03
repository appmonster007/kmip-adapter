package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.ImportOpResponsePayload;

public class ImportOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ImportOpResponsePayload,
        ImportOpResponsePayload.ImportOpResponsePayloadBuilder> {

  public ImportOpResponsePayloadXmlDeserializer() {
    super(ImportOpResponsePayload.kmipTag, ImportOpResponsePayload.encodingType);
  }

  @Override
  protected ImportOpResponsePayload.ImportOpResponsePayloadBuilder createBuilder() {
    return ImportOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ImportOpResponsePayload.ImportOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ImportOpResponsePayload build(
      ImportOpResponsePayload.ImportOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}