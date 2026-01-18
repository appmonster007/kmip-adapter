package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ProfileName;

public class ProfileNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProfileName, String> {

    public ProfileNameXmlDeserializer() {
        super(ProfileName.kmipTag, ProfileName.encodingType, String.class, value -> new ProfileName(ProfileName.fromName(value)));
    }
}