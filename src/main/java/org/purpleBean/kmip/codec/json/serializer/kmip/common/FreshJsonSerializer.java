package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.Fresh;

public class FreshJsonSerializer extends AbstractKmipJsonSerializer<Fresh, Boolean> {

    public FreshJsonSerializer() {
        super(Fresh::getValue);
    }
}