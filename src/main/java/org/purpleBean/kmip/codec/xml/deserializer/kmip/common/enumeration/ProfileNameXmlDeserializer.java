package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ProfileName;

public class ProfileNameXmlDeserializer extends AbstractKmipXmlDeserializer<ProfileName, String> {

    public ProfileNameXmlDeserializer() {
        super(ProfileName.kmipTag, ProfileName.encodingType, String.class, value -> new ProfileName(ProfileName.fromName(value)));
    }
}