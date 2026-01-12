package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.ProfileName;

public class ProfileNameTtlvDeserializer extends AbstractKmipTtlvDeserializer<ProfileName, Integer> {

    public ProfileNameTtlvDeserializer() {
        super(ProfileName.kmipTag, ProfileName.encodingType, Integer.class, value -> new ProfileName(ProfileName.fromValue(value)));
    }
}