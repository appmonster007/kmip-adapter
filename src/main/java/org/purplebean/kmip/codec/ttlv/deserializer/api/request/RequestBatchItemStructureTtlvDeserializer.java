package org.purplebean.kmip.codec.ttlv.deserializer.api.request;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestBatchItemStructure;
import org.purplebean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestBatchItem;

/**
 * TTLV deserializer for {@link RequestBatchItemStructure}.
 */
public class RequestBatchItemStructureTtlvDeserializer
    extends KmipDataTypeTtlvDeserializer<RequestBatchItemStructure> {

  @Override
  public RequestBatchItemStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper)
      throws IOException {
    return super.deserialize(ttlvBuffer, mapper);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            TtlvMapper mapper) {
    Class<? extends RequestBatchItemStructure> batchItemClass =
        RequestBatchItemStructure.getClassFromRegistry();
    if (batchItemClass == null && KmipContext
        .getSpec()
        .equals(KmipSpec.UnknownVersion)) {
      batchItemClass = SimpleRequestBatchItem.class;
    }
    return batchItemClass;
  }
}