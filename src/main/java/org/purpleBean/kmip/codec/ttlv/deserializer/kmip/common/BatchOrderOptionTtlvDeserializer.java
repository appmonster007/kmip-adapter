package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.BatchOrderOption;

public class BatchOrderOptionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<BatchOrderOption, Boolean> {

    public BatchOrderOptionTtlvDeserializer() {
        super(BatchOrderOption.kmipTag, BatchOrderOption.encodingType, Boolean.class, value -> BatchOrderOption.builder().value(value).build());
    }
}