package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.ApplicationData;

public class ApplicationDataJsonSerializer extends AbstractKmipJsonSerializer<ApplicationData, String> {

    public ApplicationDataJsonSerializer() {
        super(ApplicationData::getValue);
    }
}