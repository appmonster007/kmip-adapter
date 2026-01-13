package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.BatchOrderOption;

public class BatchOrderOptionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<BatchOrderOption, Boolean> {

    public BatchOrderOptionJsonDeserializer() {
        super(BatchOrderOption.kmipTag, BatchOrderOption.encodingType, Boolean.class, value -> BatchOrderOption.builder().value(value).build());
    }
}