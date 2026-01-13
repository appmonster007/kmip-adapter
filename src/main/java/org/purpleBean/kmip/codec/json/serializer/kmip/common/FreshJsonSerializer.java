package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Fresh;

public class FreshJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Fresh, Boolean> {

    public FreshJsonSerializer() {
        super(Fresh::getValue);
    }
}