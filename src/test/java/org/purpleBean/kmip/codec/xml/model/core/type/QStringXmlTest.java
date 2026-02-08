package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.QString;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("QString XML Serialization Tests")
class QStringXmlTest extends AbstractXmlSerializationTestSuite<QString> {

    @Override
    public Class<QString> type() {
        return QString.class;
    }

    @Override
    public QString createDefault() {
        return QString.of("test-qstring".getBytes());
    }

    @Override
    public QString createVariant() {
        return QString.of("another-qstring".getBytes());
    }
}