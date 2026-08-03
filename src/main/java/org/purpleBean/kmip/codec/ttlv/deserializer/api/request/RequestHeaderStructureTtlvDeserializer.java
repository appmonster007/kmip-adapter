package org.purpleBean.kmip.codec.ttlv.deserializer.api.request;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestHeaderStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestHeader;

public class RequestHeaderStructureTtlvDeserializer
    extends KmipDataTypeTtlvDeserializer<RequestHeaderStructure> {

  @Override
  public RequestHeaderStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper)
      throws IOException {
    return super.deserialize(ttlvBuffer, mapper);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            TtlvMapper mapper) {
    Class<? extends RequestHeaderStructure> headerClass =
        RequestHeaderStructure.getClassFromRegistry();
    if (headerClass == null && KmipContext
        .getSpec()
        .equals(KmipSpec.UnknownVersion)) {
      headerClass = SimpleRequestHeader.class;
    }
    return headerClass;
  }
}