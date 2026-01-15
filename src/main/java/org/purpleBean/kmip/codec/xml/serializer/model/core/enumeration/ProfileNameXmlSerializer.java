package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.ProfileName;

public class ProfileNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ProfileName, String> {

    public ProfileNameXmlSerializer() {
        super(ProfileName::getDescription);
    }
}