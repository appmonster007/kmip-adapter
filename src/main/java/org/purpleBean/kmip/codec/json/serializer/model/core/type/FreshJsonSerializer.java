package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.Fresh;

public class FreshJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Fresh, Boolean> {

    public FreshJsonSerializer() {
        super(Fresh::getValue);
    }
}