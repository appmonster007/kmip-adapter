package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.ProfileName;

public class ProfileNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ProfileName, String> {

    public ProfileNameXmlSerializer() {
        super(ProfileName::getDescription);
    }
}