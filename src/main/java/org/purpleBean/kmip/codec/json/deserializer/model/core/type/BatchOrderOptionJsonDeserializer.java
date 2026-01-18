package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.BatchOrderOption;

public class BatchOrderOptionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<BatchOrderOption, Boolean> {

    public BatchOrderOptionJsonDeserializer() {
        super(BatchOrderOption.kmipTag, BatchOrderOption.encodingType, Boolean.class, value -> BatchOrderOption.builder().value(value).build());
    }
}