package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.BatchOrderOption;

public class BatchOrderOptionTtlvDeserializer extends AbstractKmipTtlvDeserializer<BatchOrderOption, Boolean> {

    public BatchOrderOptionTtlvDeserializer() {
        super(BatchOrderOption.kmipTag, BatchOrderOption.encodingType, Boolean.class, value -> BatchOrderOption.builder().value(value).build());
    }
}