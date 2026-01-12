package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ApplicationData;

public class ApplicationDataTtlvSerializer extends AbstractKmipTtlvSerializer<ApplicationData, String> {

    public ApplicationDataTtlvSerializer() {
        super(ApplicationData::getValue);
    }
}