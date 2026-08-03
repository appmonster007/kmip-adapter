package org.purplebean.kmip.codec.json.deserializer.api.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestHeaderStructure;
import org.purplebean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestHeader;

public class RequestHeaderStructureJsonDeserializer
    extends KmipDataTypeJsonDeserializer<RequestHeaderStructure> {

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