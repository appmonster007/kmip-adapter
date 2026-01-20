package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ProfileName;

public class ProfileNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProfileName, Integer> {

    public ProfileNameTtlvDeserializer() {
        super(ProfileName.kmipTag, ProfileName.encodingType, Integer.class, value -> ProfileName.fromValue(value).inst());
    }
}