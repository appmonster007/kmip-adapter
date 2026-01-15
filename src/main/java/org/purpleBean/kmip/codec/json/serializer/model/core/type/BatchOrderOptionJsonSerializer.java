package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.BatchOrderOption;

public class BatchOrderOptionJsonSerializer extends AbstractKmipDataTypeJsonSerializer<BatchOrderOption, Boolean> {

    public BatchOrderOptionJsonSerializer() {
        super(BatchOrderOption::getValue);
    }
}