package org.purpleBean.kmip.codec.xml.deserializer.api.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestBatchItemStructure;
import org.purpleBean.kmip.codec.xml.deserializer.api.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestBatchItem;

public class RequestBatchItemStructureXmlDeserializer
    extends KmipDataTypeXmlDeserializer<RequestBatchItemStructure> {

  @Override
  public RequestBatchItemStructure deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    return super.deserialize(p, ctxt);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            DeserializationContext ctxt) {
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