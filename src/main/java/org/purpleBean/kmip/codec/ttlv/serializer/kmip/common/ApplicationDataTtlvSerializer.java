package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ApplicationData;

public class ApplicationDataTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ApplicationData, String> {

    public ApplicationDataTtlvSerializer() {
        super(ApplicationData::getValue);
    }
}