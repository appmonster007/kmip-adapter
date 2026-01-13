package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ProfileName;

public class ProfileNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ProfileName, Integer> {

    public ProfileNameTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}