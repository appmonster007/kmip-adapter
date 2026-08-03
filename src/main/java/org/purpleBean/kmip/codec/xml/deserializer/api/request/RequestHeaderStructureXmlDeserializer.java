package org.purpleBean.kmip.codec.xml.deserializer.api.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestHeaderStructure;
import org.purpleBean.kmip.codec.xml.deserializer.api.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestHeader;

public class RequestHeaderStructureXmlDeserializer
    extends KmipDataTypeXmlDeserializer<RequestHeaderStructure> {

  @Override
  public RequestHeaderStructure deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    return super.deserialize(p, ctxt);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            DeserializationContext ctxt) {
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