package org.purplebean.kmip.codec.xml.deserializer.api.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestMessageStructure;
import org.purplebean.kmip.codec.xml.deserializer.api.KmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;

/**
 * XML deserializer for {@link RequestMessageStructure}.
 */
public class RequestMessageStructureXmlDeserializer
    extends KmipDataTypeXmlDeserializer<RequestMessageStructure> {

  @Override
  public RequestMessageStructure deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    TokenBuffer buffer = new TokenBuffer(p, ctxt);
    buffer.copyCurrentStructure(p);

    KmipSpec previous = KmipContext.getSpec();
    try {
      KmipContext.clear();
      JsonParser replay = buffer.asParser();
      replay.nextToken();
      ctxt.setAttribute("tag", "RequestMessage");
      RequestMessageStructure requestMessage = super.deserialize(replay, ctxt);
      ProtocolVersion protocolVersion = requestMessage
          .getRequestHeader()
          .getProtocolVersion();

      KmipSpec spec = KmipSpec.fromValue(protocolVersion);
      KmipContext.setSpec(spec);
      JsonParser original = buffer.asParser();
      original.nextToken();
      ctxt.setAttribute("tag", "RequestMessage");
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
    return RequestMessageStructure.getClassFromRegistry();
  }
}
