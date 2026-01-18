package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.BatchOrderOption;

public class BatchOrderOptionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<BatchOrderOption, Boolean> {

    public BatchOrderOptionTtlvDeserializer() {
        super(BatchOrderOption.kmipTag, BatchOrderOption.encodingType, Boolean.class, value -> BatchOrderOption.builder().value(value).build());
    }
}