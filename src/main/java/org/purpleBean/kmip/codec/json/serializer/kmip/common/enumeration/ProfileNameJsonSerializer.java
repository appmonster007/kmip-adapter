package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ProfileName;

public class ProfileNameJsonSerializer extends AbstractKmipJsonSerializer<ProfileName, String> {

    public ProfileNameJsonSerializer() {
        super(ProfileName::getDescription);
    }
}