package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.BatchOrderOption;

public class BatchOrderOptionTtlvSerializer extends AbstractKmipTtlvSerializer<BatchOrderOption, Boolean> {

    public BatchOrderOptionTtlvSerializer() {
        super(BatchOrderOption::getValue);
    }
}