package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.BatchOrderOption;

public class BatchOrderOptionXmlSerializer extends AbstractKmipDataTypeXmlSerializer<BatchOrderOption, Boolean> {

    public BatchOrderOptionXmlSerializer() {
        super(BatchOrderOption::getValue);
    }
}