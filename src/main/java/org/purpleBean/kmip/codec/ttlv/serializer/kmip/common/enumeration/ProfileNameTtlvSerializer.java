package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ProfileName;

public class ProfileNameTtlvSerializer extends AbstractKmipTtlvSerializer<ProfileName, Integer> {

    public ProfileNameTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}