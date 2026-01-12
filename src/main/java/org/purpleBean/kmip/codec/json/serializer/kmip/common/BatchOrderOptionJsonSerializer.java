package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.BatchOrderOption;

public class BatchOrderOptionJsonSerializer extends AbstractKmipJsonSerializer<BatchOrderOption, Boolean> {

    public BatchOrderOptionJsonSerializer() {
        super(BatchOrderOption::getValue);
    }
}