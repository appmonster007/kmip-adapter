package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ProfileName;

public class ProfileNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ProfileName, String> {

    public ProfileNameJsonSerializer() {
        super(ProfileName::getDescription);
    }
}