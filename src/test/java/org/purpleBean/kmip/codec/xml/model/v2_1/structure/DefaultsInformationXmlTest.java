package org.purpleBean.kmip.codec.xml.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v2_1.structure.DefaultsInformation;
import org.purpleBean.kmip.model.v2_1.structure.ObjectDefaults;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.util.Collections;
import java.util.List;

@DisplayName("DefaultsInformation Xml Serialization Tests")
class DefaultsInformationXmlTest extends AbstractXmlSerializationTestSuite<DefaultsInformation> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<DefaultsInformation> type() {
        return DefaultsInformation.class;
    }

    @Override
    public DefaultsInformation createDefault() {
        return DefaultsInformation.of(List.of(
                ObjectDefaults.of(
                        ObjectType.Standard.SYMMETRIC_KEY.inst(),
                        Attributes.of(Collections.emptyList())
                )
        ));
    }

    @Override
    public DefaultsInformation createVariant() {
        return DefaultsInformation.of(List.of(
                ObjectDefaults.of(
                        ObjectType.Standard.CERTIFICATE.inst(),
                        Attributes.of(Collections.emptyList())
                )
        ));
    }
}
