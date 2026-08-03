package org.purplebean.kmip.codec.ttlv.deserializer.api.request;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestMessageStructure;
import org.purplebean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestMessage;

public class RequestMessageStructureTtlvDeserializer
    extends KmipDataTypeTtlvDeserializer<RequestMessageStructure> {

  @Override
  public RequestMessageStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper)
      throws IOException {
    KmipSpec previous = KmipContext.getSpec();
    try {
      KmipContext.clear();
      SimpleRequestMessage simpleRequestMessage =
          mapper.readValue(ttlvBuffer, SimpleRequestMessage.class);
      ttlvBuffer.rewind();
      ProtocolVersion protocolVersion = simpleRequestMessage
          .getRequestHeader()
          .getProtocolVersion();

      KmipSpec spec = KmipSpec.fromValue(protocolVersion);
      KmipContext.setSpec(spec);
      return super.deserialize(ttlvBuffer, mapper);
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
                                                            TtlvMapper mapper) {
    return RequestMessageStructure.getClassFromRegistry();
  }
}
