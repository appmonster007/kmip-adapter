package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.structure.ValidationInformation;
import org.purpleBean.kmip.model.v2_1.type.ValidationVersionMajor;
import org.purpleBean.kmip.model.v2_1.type.ValidationLevel;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidationInformation Ttlv Serialization Tests")
class ValidationInformationTtlvTest extends AbstractTtlvSerializationTestSuite<ValidationInformation> {

    @Override
    public Class<ValidationInformation> type() {
        return ValidationInformation.class;
    }

    @Override
    public ValidationInformation createDefault() {
        return ValidationInformation.of(
                ValidationAuthorityType.Standard.UNSPECIFIED.inst(),
                ValidationVersionMajor.of(1),
                ValidationType.Standard.UNSPECIFIED.inst(),
                ValidationLevel.of(1));
    }

    @Override
    public ValidationInformation createVariant() {
        return ValidationInformation.of(
                ValidationAuthorityType.Standard.UNSPECIFIED.inst(),
                ValidationVersionMajor.of(1),
                ValidationType.Standard.UNSPECIFIED.inst(),
                ValidationLevel.of(2));
    }
}