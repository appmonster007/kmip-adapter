package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.BatchOrderOption;

public class BatchOrderOptionJsonSerializer extends AbstractKmipDataTypeJsonSerializer<BatchOrderOption, Boolean> {

    public BatchOrderOptionJsonSerializer() {
        super(BatchOrderOption::getValue);
    }
}