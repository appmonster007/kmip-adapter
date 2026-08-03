package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.MacData;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.MacVerifyOpRequestPayload;

public class MacVerifyOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<MacVerifyOpRequestPayload,
        MacVerifyOpRequestPayload.MacVerifyOpRequestPayloadBuilder> {

  public MacVerifyOpRequestPayloadXmlDeserializer() {
    super(MacVerifyOpRequestPayload.kmipTag, MacVerifyOpRequestPayload.encodingType);
  }

  @Override
  protected MacVerifyOpRequestPayload.MacVerifyOpRequestPayloadBuilder createBuilder() {
    return MacVerifyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(MacVerifyOpRequestPayload.MacVerifyOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
      case KmipTag.Standard.MAC_DATA -> builder.macData(ctxt.readValue(p, MacData.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected MacVerifyOpRequestPayload build(
      MacVerifyOpRequestPayload.MacVerifyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}