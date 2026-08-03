package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;

public class StorageStatusMaskJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<StorageStatusMask,
        StorageStatusMask.StorageStatusMaskBuilder> {

  public StorageStatusMaskJsonDeserializer() {
    super(StorageStatusMask.kmipTag, StorageStatusMask.encodingType);
  }

  @Override
  protected StorageStatusMask.StorageStatusMaskBuilder createBuilder() {
    return StorageStatusMask.builder();
  }

  @Override
  protected void setValue(StorageStatusMask.StorageStatusMaskBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected StorageStatusMask build(StorageStatusMask.StorageStatusMaskBuilder builder) {
    return builder.build();
  }
}
