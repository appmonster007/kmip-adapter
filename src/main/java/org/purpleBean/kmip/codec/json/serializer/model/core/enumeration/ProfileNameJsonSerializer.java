package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.ProfileName;

public class ProfileNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ProfileName, String> {

    public ProfileNameJsonSerializer() {
        super(ProfileName::getDescription);
    }
}