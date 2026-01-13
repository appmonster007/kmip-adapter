package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ProfileName;

public class ProfileNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProfileName, String> {

    public ProfileNameXmlDeserializer() {
        super(ProfileName.kmipTag, ProfileName.encodingType, String.class, value -> new ProfileName(ProfileName.fromName(value)));
    }
}