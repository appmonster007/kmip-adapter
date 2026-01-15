package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.ProfileName;

public class ProfileNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ProfileName, Integer> {

    public ProfileNameTtlvSerializer() {
        super(ProfileName::getValue);
    }
}