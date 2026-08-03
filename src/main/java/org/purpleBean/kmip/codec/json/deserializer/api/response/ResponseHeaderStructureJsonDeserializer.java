package org.purpleBean.kmip.codec.json.deserializer.api.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.response.ResponseHeaderStructure;
import org.purpleBean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseHeader;

public class ResponseHeaderStructureJsonDeserializer
    extends KmipDataTypeJsonDeserializer<ResponseHeaderStructure> {

  @Override
  public ResponseHeaderStructure deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    return super.deserialize(p, ctxt);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            DeserializationContext ctxt) {
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
