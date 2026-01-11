package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ProfileName;

public class ProfileNameJsonDeserializer extends AbstractKmipJsonDeserializer<ProfileName, String> {

    public ProfileNameJsonDeserializer() {
        super(ProfileName.kmipTag, ProfileName.encodingType, String.class, value -> new ProfileName(ProfileName.fromName(value)));
    }
}