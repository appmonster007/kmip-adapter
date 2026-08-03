package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.ArchiveOpResponsePayload;

public class ArchiveOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ArchiveOpResponsePayload,
        ArchiveOpResponsePayload.ArchiveOpResponsePayloadBuilder> {

  public ArchiveOpResponsePayloadXmlDeserializer() {
    super(ArchiveOpResponsePayload.kmipTag, ArchiveOpResponsePayload.encodingType);
  }

  @Override
  protected ArchiveOpResponsePayload.ArchiveOpResponsePayloadBuilder createBuilder() {
    return ArchiveOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ArchiveOpResponsePayload.ArchiveOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);

    if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
      builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ArchiveOpResponsePayload build(
      ArchiveOpResponsePayload.ArchiveOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
