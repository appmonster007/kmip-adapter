package org.purplebean.kmip.codec.json.deserializer.api.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.response.ResponseBatchItemStructure;
import org.purplebean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseBatchItem;

/**
 * JSON deserializer for {@link ResponseBatchItemStructure}.
 */
public class ResponseBatchItemStructureJsonDeserializer
    extends KmipDataTypeJsonDeserializer<ResponseBatchItemStructure> {

  @Override
  public ResponseBatchItemStructure deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    return super.deserialize(p, ctxt);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            DeserializationContext ctxt) {
    Class<? extends ResponseBatchItemStructure> batchItemClass =
        ResponseBatchItemStructure.getClassFromRegistry();
    if (batchItemClass == null && KmipContext
        .getSpec()
        .equals(KmipSpec.UnknownVersion)) {
      batchItemClass = SimpleResponseBatchItem.class;
    }
    return batchItemClass;
  }
}
