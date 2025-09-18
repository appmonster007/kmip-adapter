package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("KeyRoleType XML Serialization")
class KeyRoleTypeXmlTest extends AbstractXmlSerializationSuite<KeyRoleType> {
    @Override
    protected Class<KeyRoleType> type() {
        return KeyRoleType.class;
    }

    @Override
    protected KeyRoleType createDefault() {
        return new KeyRoleType(KeyRoleType.Standard.PLACEHOLDER_1);
    }

    @Override
    protected KeyRoleType createVariant() {
        return new KeyRoleType(KeyRoleType.Standard.PLACEHOLDER_2);
    }
}
