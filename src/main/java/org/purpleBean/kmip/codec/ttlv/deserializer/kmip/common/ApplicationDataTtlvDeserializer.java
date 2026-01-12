package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.ApplicationData;

public class ApplicationDataTtlvDeserializer extends AbstractKmipTtlvDeserializer<ApplicationData, String> {

    public ApplicationDataTtlvDeserializer() {
        super(ApplicationData.kmipTag, ApplicationData.encodingType, String.class, value -> ApplicationData.builder().value(value).build());
    }
}