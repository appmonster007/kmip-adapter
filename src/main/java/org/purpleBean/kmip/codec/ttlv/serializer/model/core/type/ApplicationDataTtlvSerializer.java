package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.ApplicationData;

public class ApplicationDataTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ApplicationData, String> {

    public ApplicationDataTtlvSerializer() {
        super(ApplicationData::getValue);
    }
}