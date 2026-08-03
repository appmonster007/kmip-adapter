package org.purpleBean.kmip.codec.ttlv.deserializer.api.request;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestMessageStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestMessage;

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
