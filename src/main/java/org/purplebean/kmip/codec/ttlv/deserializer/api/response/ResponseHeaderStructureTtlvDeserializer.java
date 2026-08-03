package org.purplebean.kmip.codec.ttlv.deserializer.api.response;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.response.ResponseHeaderStructure;
import org.purplebean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseHeader;

/**
 * TTLV deserializer for {@link ResponseHeaderStructure}.
 */
public class ResponseHeaderStructureTtlvDeserializer
    extends KmipDataTypeTtlvDeserializer<ResponseHeaderStructure> {

  @Override
  public ResponseHeaderStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper)
      throws IOException {
    return super.deserialize(ttlvBuffer, mapper);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            TtlvMapper mapper) {
    Class<? extends ResponseHeaderStructure> headerClass =
        ResponseHeaderStructure.getClassFromRegistry();
    if (headerClass == null && KmipContext
        .getSpec()
        .equals(KmipSpec.UnknownVersion)) {
      headerClass = SimpleResponseHeader.class;
    }
    return headerClass;
  }
}
