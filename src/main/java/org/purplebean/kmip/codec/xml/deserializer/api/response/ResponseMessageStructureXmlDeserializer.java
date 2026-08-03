package org.purplebean.kmip.codec.xml.deserializer.api.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.response.ResponseMessageStructure;
import org.purplebean.kmip.codec.xml.deserializer.api.KmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;

/**
 * XML deserializer for {@link ResponseMessageStructure}.
 */
public class ResponseMessageStructureXmlDeserializer
    extends KmipDataTypeXmlDeserializer<ResponseMessageStructure> {

  @Override
  public ResponseMessageStructure deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    TokenBuffer buffer = new TokenBuffer(p, ctxt);
    buffer.copyCurrentStructure(p);

    KmipSpec previous = KmipContext.getSpec();
    try {
      KmipContext.clear();
      JsonParser replay = buffer.asParser();
      replay.nextToken();
      ctxt.setAttribute("tag", "ResponseMessage");
      ResponseMessageStructure responseMessage = super.deserialize(replay, ctxt);
      ProtocolVersion protocolVersion = responseMessage
          .getResponseHeader()
          .getProtocolVersion();

      KmipSpec spec = KmipSpec.fromValue(protocolVersion);
      KmipContext.setSpec(spec);
      JsonParser original = buffer.asParser();
      original.nextToken();
      ctxt.setAttribute("tag", "ResponseMessage");
      return super.deserialize(original, ctxt);
    } finally {
      if (previous != null) {
        KmipContext.setSpec(previous);
      } else {
        KmipContext.clear();
      }
    }
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            DeserializationContext ctxt) {
    return ResponseMessageStructure.getClassFromRegistry();
  }
}
