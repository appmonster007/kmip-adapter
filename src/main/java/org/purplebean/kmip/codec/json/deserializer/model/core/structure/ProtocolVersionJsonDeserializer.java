package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;

/**
 * JSON deserializer for {@link ProtocolVersion}.
 */
public class ProtocolVersionJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ProtocolVersion, ProtocolVersion.ProtocolVersionBuilder> {

  /**
   * Constructs a new {@link ProtocolVersionJsonDeserializer}.
   */
  public ProtocolVersionJsonDeserializer() {
    super(ProtocolVersion.kmipTag, ProtocolVersion.encodingType);
  }

  @Override
  protected ProtocolVersion.ProtocolVersionBuilder createBuilder() {
    return ProtocolVersion.builder();
  }

  @Override
  protected void setValue(ProtocolVersion.ProtocolVersionBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PROTOCOL_VERSION_MAJOR ->
          builder.protocolVersionMajor(ctxt.readValue(p, ProtocolVersionMajor.class));
      case KmipTag.Standard.PROTOCOL_VERSION_MINOR ->
          builder.protocolVersionMinor(ctxt.readValue(p, ProtocolVersionMinor.class));
      default -> ctxt.reportWrongTokenException(ProtocolVersion.class, p.currentToken(),
          "Unexpected field " + p.currentName());
    }
  }

  @Override
  protected ProtocolVersion build(ProtocolVersion.ProtocolVersionBuilder builder) {
    return builder.build();
  }
}